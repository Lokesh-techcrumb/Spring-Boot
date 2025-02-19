package com.example.HibernateMapping.manyToMany.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Programmer {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int salary;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "programmers_projects", joinColumns = @JoinColumn(name = "programmer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"))
    private Set<Project> projects;

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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
}
