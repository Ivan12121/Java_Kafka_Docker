package com.bakaev.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private int age;
    private int clazz;

    public Students() {

    }

    public Students(String fullName, int age, int clazz) {
        this.fullName = fullName;
        this.age = age;
        this.clazz = clazz;
    }

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private final List<SchoolSubjects> schoolSubjects = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public List<SchoolSubjects> getSchoolSubjects() {
        return schoolSubjects;
    }
}
