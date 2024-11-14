package miu.asd.usermanagement.service;

import miu.asd.usermanagement.dto.UserRequestDto;
import miu.asd.usermanagement.dto.UserResponseDto;

import java.util.Optional;

public interface UserService {
    Optional<UserResponseDto> createUser(UserRequestDto user);

    Optional<UserResponseDto> getUserByUsername(String username);
}
