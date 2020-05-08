package com.doc.lab2.domain;

import com.doc.lab2.DTO.EntityInterface;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "interview_results", schema = "candidates")
public class InterviewResult implements EntityInterface {
    private Long id;
    private String result;
    private Long candidateId;

    public InterviewResult() { }

    public InterviewResult(Long id, String result, Long candidateId) {
        this.id = id;
        this.result = result;
        this.candidateId = candidateId;
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
    @Column(name = "candidate_id", nullable = false)
    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    @Basic
    @Column(name = "result", nullable = false, length = 50)
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterviewResult that = (InterviewResult) o;
        return id == that.id &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, result);
    }
    @Override
    public String toString(){
        return "Id= " + id + ", Result= " + result;
    }

    public String toJsonObject() {
        return "{" +
                "\"id\":" + id + "," +
                "\"candidateId\":" + id + "," +
                "\"result\":" + "\"" + result + "\"" +
                "}";
    }
}
