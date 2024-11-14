package miu.asd.adsmanagement.model;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "patients")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    private ObjectId accountId;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String password;
    private LocalDate dob;

    @DBRef(lazy = true)
    private Address address;

    public Patient(String firstName, String lastName, String phoneNumber, String email, String password, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }
}
