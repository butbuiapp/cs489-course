package miu.asd.adsmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private AddressResponseDto address;
}
