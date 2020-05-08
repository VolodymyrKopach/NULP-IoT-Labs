package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.DTO.impl.CandidateDTO;
import com.doc.lab2.domain.Candidate;
import com.doc.lab2.service.CondidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CandidateController {
    @Autowired
    CondidateService CandidateService;

    // get Candidate by class id
    @GetMapping(value = "/api/candidate/vacancy/{vacancy_id}")
    public ResponseEntity<List<CandidateDTO>> getCandidatesByVacanciesID(@PathVariable Long vacancy_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        Set<Candidate> candidateSet = CandidateService.getCandidateByVacancyId(vacancy_id);

        Link link = linkTo(methodOn(CandidateController.class).getAllCandidates()).withSelfRel();

        List<CandidateDTO> candidatesDTO = new ArrayList<>();
        for (Candidate entity : candidateSet) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CandidateDTO dto = new CandidateDTO(entity, selfLink);
            candidatesDTO.add(dto);
        }

        return new ResponseEntity<>(candidatesDTO, HttpStatus.OK);
    }

    // get Candidate
    @GetMapping(value = "/api/candidate/{candidate_id}")
    public ResponseEntity<CandidateDTO> getCandidate(@PathVariable Long candidate_id) throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        Candidate candidate = CandidateService.getCandidate(candidate_id);
        Link link = linkTo(methodOn(CandidateController.class).getCandidate(candidate_id)).withSelfRel();

        CandidateDTO candidateDTO = new CandidateDTO(candidate, link);

        return new ResponseEntity<>(candidateDTO, HttpStatus.OK);
    }

    // get all Candidates
    @GetMapping(value = "/api/candidate")
    public ResponseEntity<Set<CandidateDTO>> getAllCandidates() throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        List<Candidate> allCandidates = CandidateService.getAllCandidates();
        Link link = linkTo(methodOn(CandidateController.class).getAllCandidates()).withSelfRel();

        Set<CandidateDTO> candidateDTOS = new HashSet<>();
        for (Candidate entity : allCandidates) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            CandidateDTO dto = new CandidateDTO(entity, selfLink);
            candidateDTOS.add(dto);
        }

        return new ResponseEntity<>(candidateDTOS, HttpStatus.OK);
    }

    // add Candidate
    @PostMapping(value = "/api/candidate/vacancy/{vacancy_id}")
    public ResponseEntity<CandidateDTO> addCandidate(@RequestBody Candidate newCandidate, @PathVariable Long vacancy_id)
            throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {

        CandidateService.createCandidate(newCandidate, vacancy_id);
        Link link = linkTo(methodOn(CandidateController.class).getCandidate(newCandidate.getId())).withSelfRel();

        CandidateDTO candidateDTO = new CandidateDTO(newCandidate, link);

        return new ResponseEntity<>(candidateDTO, HttpStatus.CREATED);
    }

    //update Candidate
    @PutMapping(value = "/api/candidate/{candidate_id}/vacancy/{vacancy_id}")
    public ResponseEntity<CandidateDTO> updateCandidate(@RequestBody Candidate uCandidate,
                                                        @PathVariable Long candidate_id, @PathVariable Long vacancy_id)
            throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        CandidateService.updateCandidate(uCandidate, candidate_id, vacancy_id);
        Candidate candidate = CandidateService.getCandidate(candidate_id);
        Link link = linkTo(methodOn(CandidateController.class).getCandidate(candidate_id)).withSelfRel();

        CandidateDTO candidateDTO = new CandidateDTO(candidate, link);

        return new ResponseEntity<>(candidateDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/candidate/{candidate_id}")
    public ResponseEntity deleteCandidate(@PathVariable Long candidate_id) throws NoSuchCandidateException, ExistsInterviewResultForCandidatesException, ExistsInterviewResultForCandidatesException {
        CandidateService.deleteCandidate(candidate_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

