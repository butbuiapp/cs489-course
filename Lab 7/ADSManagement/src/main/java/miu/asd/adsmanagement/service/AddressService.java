package miu.asd.adsmanagement.service;

import miu.asd.adsmanagement.dto.request.AddressResquestDto;
import miu.asd.adsmanagement.dto.response.AddressResponseDto;

import java.util.List;

public interface AddressService {
    AddressResponseDto createAddress(AddressResquestDto address);
    List<AddressResponseDto> getAddresses();
}
