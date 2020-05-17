package com.doc.lab2.service;

import com.doc.lab2.exceptions.ExistsCandidatesForInterviewResultException;
import com.doc.lab2.exceptions.NoSuchCandidateException;
import com.doc.lab2.exceptions.NoSuchInterviewResultException;
import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.InterviewResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class InterviewResultService {
    @Autowired
    com.doc.lab2.Repository.InterviewResultRepository InterviewResultRepository;

    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    public Set<InterviewResult> getInterviewResultsByCandidateId(Long Candidate_id) throws NoSuchCandidateException {
        Candidate Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        return Candidate.getInterviewResults();
    }

    public InterviewResult getInterviewResult(Long InterviewResult_id) throws NoSuchInterviewResultException {
        InterviewResult interviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7
        if (interviewResult == null) throw new NoSuchInterviewResultException();
        return interviewResult;
    }

    public List<InterviewResult> getAllInterviewResults() {
        return InterviewResultRepository.findAll();
    }

    @Transactional
    public void createInterviewResult(InterviewResult interviewResult) {
        InterviewResultRepository.save(interviewResult);
    }

    @Transactional
    public void updateInterviewResult(InterviewResult uInterviewResult, Long InterviewResult_id) throws NoSuchInterviewResultException {
        InterviewResult InterviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7
        if (InterviewResult == null) throw new NoSuchInterviewResultException();
        //update
        InterviewResult.setResult(uInterviewResult.getResult());
    }

    @Transactional
    public void deleteInterviewResult(Long InterviewResult_id) throws NoSuchInterviewResultException, ExistsCandidatesForInterviewResultException {
        InterviewResult interviewResult = InterviewResultRepository.findById(InterviewResult_id).get();//2.0.0.M7

        if (interviewResult == null) throw new NoSuchInterviewResultException();
        InterviewResultRepository.delete(interviewResult);
    }
}
