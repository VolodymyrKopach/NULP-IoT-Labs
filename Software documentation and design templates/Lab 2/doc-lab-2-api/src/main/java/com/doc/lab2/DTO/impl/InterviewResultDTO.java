package com.doc.lab2.DTO.impl;

import com.doc.lab2.domain.InterviewResult;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.DTO;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class InterviewResultDTO extends DTO<InterviewResult> {
    public InterviewResultDTO(InterviewResult interviewResult, Link link) throws NoSuchInterviewResultException {
        super(interviewResult, link);
//        add(linkTo(methodOn(ArtistController.class).getArtistsByProjectId(getEntity().getId())).withRel("artists"));
    }

    public Long getInterviewResultId() {
        return getEntity().getId();
    }

    public String getName() {
        return getEntity().getResult();
    }


}
