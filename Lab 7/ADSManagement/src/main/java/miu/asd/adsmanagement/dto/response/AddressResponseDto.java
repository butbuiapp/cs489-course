package miu.asd.adsmanagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {
    private String street;
    private String city;
    private String state;
    private String zip;

    private PatientResponseDto patient;
}
