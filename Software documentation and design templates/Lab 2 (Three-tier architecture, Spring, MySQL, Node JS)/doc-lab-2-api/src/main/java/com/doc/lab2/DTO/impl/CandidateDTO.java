package com.doc.lab2.DTO.impl;

import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.Vacancy;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CandidateDTO extends DTO<Candidate> {
    public CandidateDTO(Candidate candidate, Link link) throws NoSuchInterviewResultException, NoSuchVacancyException, NoSuchCandidateException {
        super(candidate, link);
//        add(linkTo(methodOn(ProjectController.class).getProjectByArtistId(getEntity().getId())).withRel("projects"));
    }

    public Long getCandidateId() {
        return getEntity().getId();
    }

    public String getCandidateName() {
        return getEntity().getName();
    }

    public String getCandidateSurname() {
        return getEntity().getSurname();
    }

    public String getCandidateJobFunction() {
        return getEntity().getJobFunction();
    }

    public Vacancy getVacancies() {
        return getEntity().getVacancyByCandidates();
    }


}
