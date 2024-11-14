package miu.asd.adsmanagement.service.impl;

import lombok.RequiredArgsConstructor;
import miu.asd.adsmanagement.dto.request.AddressResquestDto;
import miu.asd.adsmanagement.dto.request.PatientRequestDto;
import miu.asd.adsmanagement.dto.response.PatientResponseDto;
import miu.asd.adsmanagement.exception.PatientNotFoundException;
import miu.asd.adsmanagement.mapper.AddressMapper;
import miu.asd.adsmanagement.mapper.PatientMapper;
import miu.asd.adsmanagement.model.Address;
import miu.asd.adsmanagement.model.Patient;
import miu.asd.adsmanagement.repository.AddressRepository;
import miu.asd.adsmanagement.repository.PatientRepository;
import miu.asd.adsmanagement.service.PatientService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll(Sort.by("lastName")).stream().map(
                PatientMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto) {
        AddressResquestDto addressResquestDto = patientRequestDto.getAddressResquestDto();
        Address address = AddressMapper.dtoToEntity(addressResquestDto);
        // save address
        Address createdAddress = addressRepository.save(address);

        Patient patient = PatientMapper.dtoToEntity(patientRequestDto);
        patient.setAddress(createdAddress);
        // save patient
        Patient createdPatient = patientRepository.save(patient);
        return PatientMapper.entityToDto(createdPatient);
    }

    @Override
    public void updatePatient(ObjectId id, PatientRequestDto patientRequestDto) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found.")
        );
        patient.setFirstName(patientRequestDto.getFirstName());
        patient.setLastName(patientRequestDto.getLastName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setPhoneNumber(patientRequestDto.getPhoneNumber());
        patient.setPassword(patientRequestDto.getPassword());
        patient.setDob(patientRequestDto.getDob());
        patientRepository.save(patient);
    }

    @Override
    public void deletePatient(ObjectId id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found.")
        );
        patientRepository.deleteById(id);
    }

    @Override
    public PatientResponseDto getPatientById(ObjectId id) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found.")
        );
        return PatientMapper.entityToDto(patient);
    }

    @Override
    public List<PatientResponseDto> searchPatient(String searchString) {
        return patientRepository.findAllByFirstNameContainingOrLastNameContaining(searchString, searchString)
                .stream().map(PatientMapper::entityToDto).collect(Collectors.toList());
    }
}
