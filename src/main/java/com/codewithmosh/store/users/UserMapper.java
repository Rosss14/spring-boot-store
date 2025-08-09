package com.codewithmosh.store.users;

import org.mapstruct.*;

//@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest registerUserRequest);
    void update(UpdateUserRequest updateUserRequest,@MappingTarget User user);
}
