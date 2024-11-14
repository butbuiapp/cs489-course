package miu.asd.adsmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.adsmanagement.dto.request.AddressResquestDto;
import miu.asd.adsmanagement.dto.response.AddressResponseDto;
import miu.asd.adsmanagement.dto.response.PatientResponseDto;
import miu.asd.adsmanagement.mapper.AddressMapper;
import miu.asd.adsmanagement.mapper.PatientMapper;
import miu.asd.adsmanagement.model.Address;
import miu.asd.adsmanagement.model.Patient;
import miu.asd.adsmanagement.repository.AddressRepository;
import miu.asd.adsmanagement.repository.PatientRepository;
import miu.asd.adsmanagement.service.AddressService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final PatientRepository patientRepository;

    @Override
    public AddressResponseDto createAddress(AddressResquestDto addressResquestDto) {
        Address address = AddressMapper.dtoToEntity(addressResquestDto);
        return AddressMapper.entityToDto(addressRepository.save(address));
    }

    @Override
    public List<AddressResponseDto> getAddresses() {
        List<Address> addresses = addressRepository.findAll(Sort.by("city"));
        List<AddressResponseDto> addressResponseDtos = new ArrayList<>();
        for (Address address : addresses) {
            Patient patient = patientRepository.findByAddressId(address.getId());
            if (patient != null) {
                PatientResponseDto patientResponseDto = PatientMapper.entityToDto(patient);

                AddressResponseDto addressResponseDto = AddressMapper.entityToDto(address);
                addressResponseDto.setPatient(patientResponseDto);
                addressResponseDtos.add(addressResponseDto);
            }
        }
        return addressResponseDtos;
    }
}
