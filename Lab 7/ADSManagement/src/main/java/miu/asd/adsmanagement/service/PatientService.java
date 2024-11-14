package miu.asd.adsmanagement.service;

import miu.asd.adsmanagement.dto.request.PatientRequestDto;
import miu.asd.adsmanagement.dto.response.PatientResponseDto;
import org.bson.types.ObjectId;

import java.util.List;

public interface PatientService {
    List<PatientResponseDto> getAllPatients();
    PatientResponseDto createPatient(PatientRequestDto patientRequestDto);
    PatientResponseDto getPatientById(ObjectId id);
    void updatePatient(ObjectId id, PatientRequestDto patientRequestDto);
    void deletePatient(ObjectId id);
    List<PatientResponseDto> searchPatient(String searchString);
}
