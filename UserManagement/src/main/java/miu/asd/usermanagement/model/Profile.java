package miu.asd.usermanagement.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String bio;
    private String location;
    private LocalDate dob;

    @OneToOne(mappedBy = "profile")
    private User user;
}
