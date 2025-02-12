package com.example.employee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
public class Employee { 
    
    @TableGenerator(name="employee_gen", table="id_gen", pkColumnName="gen_name", valueColumnName="gen_val", allocationSize=1)
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE, generator="employee_gen")
    private long id;
    private String name;
    
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
}
