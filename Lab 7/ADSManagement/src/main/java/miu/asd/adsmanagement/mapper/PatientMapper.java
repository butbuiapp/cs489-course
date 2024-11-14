package miu.asd.adsmanagement.mapper;

import miu.asd.adsmanagement.dto.request.PatientRequestDto;
import miu.asd.adsmanagement.dto.response.PatientResponseDto;
import miu.asd.adsmanagement.model.Patient;

public class PatientMapper {

    public static PatientResponseDto entityToDto(Patient patient) {
        return PatientResponseDto.builder()
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .phoneNumber(patient.getPhoneNumber())
                .email(patient.getEmail())
                .address(patient.getAddress() != null ? AddressMapper.entityToDto(patient.getAddress()) : null)
                .build();
    }

    public static Patient dtoToEntity(PatientRequestDto patientRequestDto) {
        return Patient.builder()
                .firstName(patientRequestDto.getFirstName())
                .lastName(patientRequestDto.getLastName())
                .phoneNumber(patientRequestDto.getPhoneNumber())
                .email(patientRequestDto.getEmail())
                .dob(patientRequestDto.getDob())
                .password(patientRequestDto.getPassword())
                .build();
    }
}
