package com.doc.lab2.controller;

import com.doc.lab2.exceptions.ExistsCandidatesForInterviewResultException;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.DTO.impl.InterviewResultDTO;
import com.doc.lab2.domain.InterviewResult;
import com.doc.lab2.service.InterviewResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class InterviewResultController {
    @Autowired
    InterviewResultService interviewResultService;

    @Autowired
    CandidateController candidateController;

    @GetMapping(value = "/api/interview_result/{interview_result_id}")
    public ResponseEntity<InterviewResultDTO> getInterviewResult(@PathVariable Long interview_result_id) throws NoSuchInterviewResultException, NoSuchCandidateException {
        InterviewResult interviewResult = interviewResultService.getInterviewResult(interview_result_id);
        Link link = linkTo(methodOn(InterviewResultController.class).getInterviewResult(interview_result_id)).withSelfRel();

        InterviewResultDTO interviewResultDTO = new InterviewResultDTO(interviewResult, link);

        return new ResponseEntity<>(interviewResultDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/interview_result")
    public ResponseEntity<Set<InterviewResultDTO>> getAllInterviewResults() throws NoSuchInterviewResultException, NoSuchCandidateException {
        List<InterviewResult> interviewResults = interviewResultService.getAllInterviewResults();
        Link link = linkTo(methodOn(InterviewResultController.class).getAllInterviewResults()).withSelfRel();

        Set<InterviewResultDTO> interviewResultDTOS = new HashSet<>();
        for (InterviewResult entity : interviewResults) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            InterviewResultDTO dto = new InterviewResultDTO(entity, selfLink);
            interviewResultDTOS.add(dto);
        }

        return new ResponseEntity<>(interviewResultDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/api/interview_result")
    public ResponseEntity<InterviewResultDTO> addInterviewResult(@RequestBody InterviewResult newInterviewResult) throws NoSuchInterviewResultException, NoSuchCandidateException {
        interviewResultService.createInterviewResult(newInterviewResult);
        Link link = linkTo(methodOn(InterviewResultController.class).getInterviewResult(newInterviewResult.getId())).withSelfRel();

        InterviewResultDTO interviewResultDTO = new InterviewResultDTO(newInterviewResult, link);

        return new ResponseEntity<>(interviewResultDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/interview_result/{interview_result_id}")
    public ResponseEntity<InterviewResultDTO> updateInterviewResult(@RequestBody InterviewResult uInterviewResult, @PathVariable Long interview_result_id) throws NoSuchInterviewResultException, NoSuchCandidateException {
        interviewResultService.updateInterviewResult(uInterviewResult, interview_result_id);
        InterviewResult interviewResult = interviewResultService.getInterviewResult(interview_result_id);
        Link link = linkTo(methodOn(InterviewResultController.class).getInterviewResult(interview_result_id)).withSelfRel();

        InterviewResultDTO interviewResultDTO = new InterviewResultDTO(interviewResult, link);

        return new ResponseEntity<>(interviewResultDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/interview_result/{interview_result_id}")
    public  ResponseEntity deleteInterviewResult(@PathVariable Long interview_result_id) throws ExistsCandidatesForInterviewResultException, NoSuchInterviewResultException {
        interviewResultService.deleteInterviewResult(interview_result_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
