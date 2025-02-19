package com.example.HibernateMapping.manyToMany.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "projects")
    private Set<Programmer> Programmers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Programmer> getProgrammers() {
        return Programmers;
    }

    public void setProgrammers(Set<Programmer> programmers) {
        Programmers = programmers;
    }
}
