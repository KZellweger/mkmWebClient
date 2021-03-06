package ch.skaldenmagic.cardmarket.autopricing.domain.service;

import ch.skaldenmagic.cardmarket.autopricing.domain.mapper.AccountMapper;
import ch.skaldenmagic.cardmarket.autopricing.domain.mapper.dtos.AccountDto;
import ch.skaldenmagic.cardmarket.autopricing.domain.service.exceptions.MkmAPIException;
import de.cardmarket4j.entity.Account;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for current Mkm Account
 *
 * @author Kevin Zellweger
 * @Date 01.11.20
 */
@Service
public class AccountService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

  @Autowired
  private AccountMapper accountMapper;

  @Autowired
  private MkmService mkmService;

  public AccountDto getAccount() {
    try {
      Account account = mkmService.getCardMarket().getAccountService().getAccount();
      AccountDto dto = accountMapper.mkmToDto(account);
      return dto;
    } catch (IOException e) {
      throw new MkmAPIException(de.cardmarket4j.service.AccountService.class, "getAccount()");
    }
  }

  public Map<String, Integer> getRequestCounter() {
    Map<String, Integer> counter = new HashMap<>();
    counter.put("USED", mkmService.getCardMarket().getRequestCount());
    counter.put("LIMIT", mkmService.getCardMarket().getRequestLimit());
    return counter;
  }
}
