package com.codewithmosh.store.users;

import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String email = null;

        id = user.getId();
        name = user.getName();
        email = user.getEmail();

        UserDto userDto = new UserDto( id, name, email );

        return userDto;
    }

    @Override
    public User toEntity(RegisterUserRequest registerUserRequest) {
        if ( registerUserRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( registerUserRequest.getName() );
        user.email( registerUserRequest.getEmail() );
        user.password( registerUserRequest.getPassword() );

        return user.build();
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest, User user) {
        if ( updateUserRequest == null ) {
            return;
        }

        user.setName( updateUserRequest.getName() );
        user.setEmail( updateUserRequest.getEmail() );
    }
}

