package com.doc.lab2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.doc.lab2.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "candidates", schema = "candidates")
public class Candidate implements EntityInterface {
    private Long id;
    private String surname;
    private String name;
    private String jobFunction;
    private Vacancy vacancyByCandidates;
    Set<InterviewResult> interviewResults = new HashSet<>();

    @Transient
    private Long relatedVacancyId;

    public Candidate(String surname, String name, String jobFunction, Vacancy vacancyByCandidates) {

        this.surname = surname;
        this.name = name;
        this.jobFunction = jobFunction;
       this.vacancyByCandidates = vacancyByCandidates;
    }

    public Candidate(Long id, String surname, String name, String jobFunction) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.jobFunction = jobFunction;
    }

    public Candidate(Long id, String surname, String name, String jobFunction, Long vacancyId) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.jobFunction = jobFunction;
        this.relatedVacancyId = vacancyId;
    }

    public Candidate() {
    }

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = true, length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    @Column(name = "job_function", nullable = true, length = 50)
    public String getJobFunction() {
        return jobFunction;
    }

    public void setJobFunction(String jobFunction) {
        this.jobFunction = jobFunction;
    }

    @ManyToOne
    @JoinColumn(name = "vacancy_id",  referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Vacancy getVacancyByCandidates() {
        return vacancyByCandidates;
    }

    public void setVacancyByCandidates(Vacancy vacancyByCandidates) {
        this.vacancyByCandidates = vacancyByCandidates;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "candidate_interview_results",
            joinColumns = { @JoinColumn(name = "candidate_id", referencedColumnName = "id", nullable = false) },
            inverseJoinColumns = { @JoinColumn(name = "interview_result_id", referencedColumnName = "id", nullable = false), }
    )
    @JsonIgnore
    public Set<InterviewResult> getInterviewResults() {
        return interviewResults;
    }

    public void setInterviewResults(Set<InterviewResult> interviewResults) {
        this.interviewResults = interviewResults;
    }

    @Transient
    public Long getRelatedVacancyId() {
        return relatedVacancyId;
    }

    public void setRelatedVacancyId(Long vacancyId) {
        this.relatedVacancyId = vacancyId;
    }

    @Override
    public String toString() {
        return "Candidates{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", jobFunction='" + jobFunction + '\'' +
                '}';
    }

    public String toStringJoinTable(){
        return "Candidates{" +
                "id=" + id +
//                " projects=" + projects +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate that = (Candidate) o;
        return id == that.id &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(name, that.name) &&
                Objects.equals(jobFunction, that.jobFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, jobFunction);
    }


    public String toJsonObject() {
        return "{" +
                "\"id\":" + id + "," +
                "\"name\":" + "\"" + name + "\"," +
                "\"surname\":" + "\"" + surname + "\"," +
                "\"jobFunction\":" + "\"" + jobFunction + "\"" +
        "}";
    }
}
