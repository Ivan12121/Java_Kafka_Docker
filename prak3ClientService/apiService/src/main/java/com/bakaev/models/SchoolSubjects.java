package com.bakaev.models;

import javax.persistence.*;

@Entity
public class SchoolSubjects {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int grade;
    private long id_stud;

    public SchoolSubjects(String name, int grade, long id_stud) {
        this.name = name;
        this.grade = grade;
        this.id_stud = id_stud;
    }

    public SchoolSubjects() {

    }

    @ManyToOne(optional=false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_student")
    private Students student;

    public long getId_stud() {
        return id_stud;
    }

    public void setId_stud(long id_stud) {
        this.id_stud = id_stud;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }
}
