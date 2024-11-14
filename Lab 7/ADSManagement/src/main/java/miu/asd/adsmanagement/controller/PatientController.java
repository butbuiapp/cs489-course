package miu.asd.adsmanagement.controller;

import lombok.RequiredArgsConstructor;
import miu.asd.adsmanagement.dto.request.PatientRequestDto;
import miu.asd.adsmanagement.service.PatientService;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adsweb/api/v1/patient")
public class PatientController {
    private final PatientService patientService;

    @GetMapping("list")
    public ResponseEntity<?> getPatients() {

        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PostMapping("register")
    public ResponseEntity<?> createPatient(@RequestBody PatientRequestDto patientRequestDto) {
        patientService.createPatient(patientRequestDto);
        return ResponseEntity.ok("Patient created successfully.");
    }

    @GetMapping("get/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable ObjectId id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable ObjectId id,
                                           @RequestBody PatientRequestDto patientRequestDto) {
        patientService.updatePatient(id, patientRequestDto);
        return ResponseEntity.ok("Patient updated successfully.");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable ObjectId id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully.");
    }

    @GetMapping("search/{searchString}")
    public ResponseEntity<?> searchPatient(@PathVariable String searchString) {
        return ResponseEntity.ok(patientService.searchPatient(searchString));
    }
}
