package com.telran.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Document(collection = "contact_book")
public class Contact {

    //new Contact()                -> document
    //List<Contact> (all contacts) -> collection
    //MongoId pool [0123456789 abcdef] | *&kjnaskjbui@naf -> exception
    @Id
    private String id;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    @Field(value = "phone_number")
    private String phoneNumber;

    private String email; //klasd8*O*7y7as676%$$%^@*HH**asda

    private LocalDate dateOfBirth;

//    @DBRef
//    private WhatsApp whatsApp;

    //findByWhatsApp_id
}
