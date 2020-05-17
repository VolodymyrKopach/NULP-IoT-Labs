package com.doc.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.doc.lab2.DTO.EntityInterface;

import javax.persistence.*;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "vacancies", schema = "candidates")
public class Vacancy implements EntityInterface {
    private Long id;
    private String name;
    private String description;
    private Integer salary;

    public Vacancy(Long id, String name, String description, Integer salary) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.salary = salary;
    }

    private Set<Candidate> candidatesByVacancy;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vacancy(Long id) {
        this.id = id;
    }

    public Vacancy() {
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String address) {
        this.description = address;
    }

    @Basic
    @Column(name = "salary", nullable = true)
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @OneToMany(
            mappedBy = "vacancyByCandidates",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<Candidate> getCandidates() {
        return candidatesByVacancy;
    }
    public void setCandidates(Set<Candidate> candidatesByVacancy)
    {
        this.candidatesByVacancy = candidatesByVacancy;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy that = (Vacancy) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(salary, that.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, salary);
    }
    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String toJsonObject() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":" + "\"" + name + "\"," +
                "\"description\":" + "\"" + description + "\"," +
                "\"salary\":" + salary +
                "}";
    }
}
