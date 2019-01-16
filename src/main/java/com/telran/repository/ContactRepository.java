package com.telran.repository;

import com.telran.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContactRepository extends MongoRepository<Contact, String> {

    List<Contact> findAllByFirstNameAndLastNameAndEmailAndPhoneNumber(
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    );
}
