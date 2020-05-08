package com.doc.lab2.DTO.impl;

import com.doc.lab2.controller.CandidateController;
import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.Vacancy;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;


public class VacancyDTO extends DTO<Vacancy> {
    public VacancyDTO(Vacancy vacancy, Link link) throws NoSuchVacancyException, NoSuchInterviewResultException, NoSuchCandidateException {
        super(vacancy, link);
        add(ControllerLinkBuilder.linkTo(methodOn(CandidateController.class).getCandidatesByVacanciesID(getEntity().getId())).withRel("candidates"));
    }

    public Long getVacancyId() {
        return getEntity().getId();
    }

    public String getVacancyName() {
        return getEntity().getName();
    }

    public Integer getVacancySalary() {
        return getEntity().getSalary();
    }

    public String getVacancyDescription() {
        return getEntity().getDescription();
    }

    public Set<Candidate> getCandidates() {
        return getEntity().getCandidates();
    }
}
