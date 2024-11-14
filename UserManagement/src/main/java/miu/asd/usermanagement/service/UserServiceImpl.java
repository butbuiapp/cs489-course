package miu.asd.usermanagement.service;

import lombok.RequiredArgsConstructor;
import miu.asd.usermanagement.dto.ProfileResponseDto;
import miu.asd.usermanagement.dto.UserRequestDto;
import miu.asd.usermanagement.dto.UserResponseDto;
import miu.asd.usermanagement.model.Profile;
import miu.asd.usermanagement.model.User;
import miu.asd.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<UserResponseDto> createUser(UserRequestDto userRequestDTO) {
        //create user object by using the data from UserRequestDTO
        User newuser = new User();
        newuser.setUsername(userRequestDTO.username());
        newuser.setPassword(userRequestDTO.password());
        //create profile object from UserRequestDTO
        Profile profile = new Profile();
        profile.setBio(userRequestDTO.profileRequestDto().bio());
        profile.setLocation(userRequestDTO.profileRequestDto().location());
        profile.setDob(userRequestDTO.profileRequestDto().dob());
        newuser.setProfile(profile);
        //save the new user
        User savedUser = userRepository.save(newuser);
        //create userResponseDTO object
        ProfileResponseDto profileResponseDTO = new ProfileResponseDto(
                savedUser.getProfile().getBio(),
                savedUser.getProfile().getLocation());
        UserResponseDto userResponseDTO = new UserResponseDto(savedUser.getUsername(), profileResponseDTO);
        return Optional.of(userResponseDTO);
    }

    @Override
    public Optional<UserResponseDto> getUserByUsername(String username) {
        return Optional.empty();
    }
}
