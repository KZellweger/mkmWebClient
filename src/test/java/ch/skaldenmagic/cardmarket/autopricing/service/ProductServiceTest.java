package ch.skaldenmagic.cardmarket.autopricing.service;

import ch.skaldenmagic.cardmarket.autopricing.domain.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Kevin Zellweger
 * @Date 28.10.20
 */
@SpringBootTest
@EnableConfigurationProperties
public class ProductServiceTest {

    @Autowired
    ProductService service;

    @Test
    public void loadMkmProducts(){
      // Test not suitable because it does only work with productive accounts
//        service.loadMkmProductlist();
    }

}
