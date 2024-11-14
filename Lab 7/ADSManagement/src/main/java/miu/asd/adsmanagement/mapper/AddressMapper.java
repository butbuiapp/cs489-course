package miu.asd.adsmanagement.mapper;

import miu.asd.adsmanagement.dto.request.AddressResquestDto;
import miu.asd.adsmanagement.dto.response.AddressResponseDto;
import miu.asd.adsmanagement.model.Address;

public class AddressMapper {
    public static AddressResponseDto entityToDto(Address address) {
        return AddressResponseDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .build();
    }

    public static Address dtoToEntity(AddressResquestDto addressResquestDto) {
        return Address.builder()
                .street(addressResquestDto.getStreet())
                .city(addressResquestDto.getCity())
                .state(addressResquestDto.getState())
                .zip(addressResquestDto.getZip())
                .build();
    }
}
