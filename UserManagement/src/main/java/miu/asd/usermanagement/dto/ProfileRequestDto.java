package miu.asd.usermanagement.dto;

import java.time.LocalDate;

public record ProfileRequestDto(String bio, String location, LocalDate dob) {
}
