package com.telran.controller;

import com.telran.domain.ContactSearchRequest;
import com.telran.entity.Contact;
import com.telran.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/contacts")
    public Contact save(@RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @GetMapping("/contacts")
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @GetMapping("/contacts/search") //?firstName=aaa&email=bbb@com
    public List<Contact> searchContacts(
            @RequestParam(value = "firstName", required = false) String firstName, //"aaa"
            @RequestParam(value = "lastName", required = false) String lastName, //null
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber, //null
            @RequestParam(value = "email", required = false) String email /*bbb@com*/) {

        //single | mongoTemplate -> поисковик = query + entity.class
        //single | Query -> запрос

        //multiple | Criteria -> критерий поиска
        //multiple | Criteria -> критерий поиска
        //multiple | Criteria -> критерий поиска
        //multiple | Criteria -> критерий поиска
        //multiple | Criteria -> критерий поиска
        //multiple | Criteria -> критерий поиска

        Query query = new Query();
        if (firstName != null) {
            query.addCriteria(Criteria.where("firstName").is(firstName));
        }

        if (lastName != null) {
            query.addCriteria(Criteria.where("lastName").is(lastName));
        }

        if (email != null) {
            query.addCriteria(Criteria.where("email").is(email));
        }

        if (phoneNumber != null) {
            query.addCriteria(Criteria.where("phone_number").is(phoneNumber));
        }

        List<Contact> contacts = mongoTemplate.find(query, Contact.class);
        return contacts;

//        return contactRepository.findAllByFirstNameAndLastNameAndEmailAndPhoneNumber(firstName, lastName, phoneNumber, email);
    }

    @PostMapping("/contacts/search")
    public List<Contact> searchByDto(@RequestBody ContactSearchRequest contactSearchRequest) {
        Query query = new Query();

        if (contactSearchRequest.getFirstName() != null) {
            query.addCriteria(Criteria.where("firstName").regex(".*" + contactSearchRequest.getFirstName() + ".*", "i"));
        }

        if (contactSearchRequest.getLastName() != null) {
            query.addCriteria(Criteria.where("lastName").regex(".*" + contactSearchRequest.getLastName() + ".*", "i"));
        }

        return mongoTemplate.find(query, Contact.class);
    }

}
