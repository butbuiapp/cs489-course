package miu.asd.adsmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequestDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate dob;
    private AddressResquestDto addressResquestDto;
}
