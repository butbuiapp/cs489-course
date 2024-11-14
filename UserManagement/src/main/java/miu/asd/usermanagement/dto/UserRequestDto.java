package miu.asd.usermanagement.dto;

public record UserRequestDto(String username, String password, ProfileRequestDto profileRequestDto) {
}
