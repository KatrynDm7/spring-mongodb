package ru.habrahabr.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Document(collection = Contact.COLLECTION_NAME)
public class Contact implements Serializable {

    public static final String COLLECTION_NAME = "contacts";

    @Id
    private Long id;

    private String name;

    @Pattern(regexp = "^[0-9]{11}$", message = "Invalid phone number!")
    private String number;

    private String email;

    private String date;

    public Contact() {

    }

    public Contact(String name, String number, String email, String date) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}