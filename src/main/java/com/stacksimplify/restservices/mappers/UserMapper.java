package com.stacksimplify.restservices.mappers;

import com.stacksimplify.restservices.dtos.UserMsDto;
import com.stacksimplify.restservices.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // User To User DTO
    @Mappings({
    @Mapping(target="emailaddress", source="email"),
    @Mapping(target = "rolename", source="role")})
    UserMsDto userToUserDto(User user);

    // List<User> to List<UserMsDto>
    List<UserMsDto> userToUserDtos(List<User> users);

}

