package de.ait.project_KidVenture.services.mapping;

import de.ait.project_KidVenture.entity.User;
import de.ait.project_KidVenture.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMappingService {


    @Mapping(target = "password", ignore = true)
    UserDto mapEntityToDto(User entity);

    User mapDtoToEntity(UserDto dto);

}
