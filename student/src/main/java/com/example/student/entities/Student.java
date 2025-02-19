package com.example.student.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@Entity
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(name="fname")
    private String firstName;
    @Column(name="lname")
    private String lastName;
    private int score;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setScore(int score) {
        this.score = score;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", score=" + score +
//                '}';
//    }
}