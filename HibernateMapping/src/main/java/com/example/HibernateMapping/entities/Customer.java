package com.example.HibernateMapping.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PhoneNumber> numbers;

    public Set<PhoneNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(Set<PhoneNumber> numbers) {
        this.numbers = numbers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber != null) {
            if(numbers == null) {
                numbers = new HashSet<>();
            }
            phoneNumber.setCustomer(this);
            numbers.add(phoneNumber);
        }
    }
}
