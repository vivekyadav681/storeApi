package com.spring.storeapi.mappers;


import com.spring.storeapi.dtos.RegisterUserRequest;
import com.spring.storeapi.dtos.UpdateUserRequest;
import com.spring.storeapi.dtos.UserDto;
import com.spring.storeapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
