package miu.asd.adsmanagement.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Getter
@Setter
@Builder
public class Address {
    @Id
    private ObjectId id;

    private String street;
    private String city;
    private String state;
    private String zip;

}
