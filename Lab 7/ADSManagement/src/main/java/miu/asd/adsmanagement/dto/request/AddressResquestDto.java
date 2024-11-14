package miu.asd.adsmanagement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressResquestDto {
    private String street;
    private String city;
    private String state;
    private String zip;
}
