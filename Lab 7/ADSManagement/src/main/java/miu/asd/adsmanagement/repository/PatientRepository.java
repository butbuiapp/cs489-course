package miu.asd.adsmanagement.repository;

import miu.asd.adsmanagement.model.Patient;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends MongoRepository<Patient, ObjectId> {
    List<Patient> findAllByFirstNameContainingOrLastNameContaining(String firstName, String lastName);

    Patient findByAddressId(ObjectId addressId);
}
