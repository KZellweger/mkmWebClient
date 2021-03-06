package ch.skaldenmagic.cardmarket.autopricing.domain.mapper;

import ch.skaldenmagic.cardmarket.autopricing.domain.mapper.dtos.UserDto;
import de.cardmarket4j.entity.User;
import org.mapstruct.Mapper;

/**
 * @author Kevin Zellweger
 * @Date 28.10.20
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDto mkmToDto(User user);


}
