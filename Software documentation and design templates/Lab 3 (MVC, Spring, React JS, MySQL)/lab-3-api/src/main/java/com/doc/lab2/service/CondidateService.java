package com.doc.lab2.service;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.domain.Vacancy;
import com.doc.lab2.domain.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class CondidateService {
    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    @Autowired
    com.doc.lab2.Repository.VacancyRepository VacancyRepository;

    @Autowired
    com.doc.lab2.Repository.InterviewResultRepository InterviewResultRepository;

    public Set<Candidate> getCandidateByVacancyId(Long class_id) throws NoSuchVacancyException {
//        Organization Organization = diagnosisRepository.findOne(diagnosis_id);//1.5.9
        Vacancy Vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (Vacancy == null) throw new NoSuchVacancyException();
        return Vacancy.getCandidates();
    }

    public Candidate getCandidate(Long Canidate_id) throws NoSuchCandidateException {
        Candidate Candidate = CandidateRepository.findById(Canidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        return Candidate;
    }

    public List<Candidate> getAllCandidates() {
        return CandidateRepository.findAll();
    }

    @Transactional
    public void createCandidate(Candidate Candidate, Long Vacancy_id) throws NoSuchVacancyException {
        if (Vacancy_id > 0) {
            Vacancy Vacancy = VacancyRepository.findById(Vacancy_id).get();//2.0.0.M7
            if (Vacancy == null) throw new NoSuchVacancyException();
            Candidate.setVacancyByCandidates(Vacancy);
        }
        CandidateRepository.save(Candidate);
    }

    @Transactional
    public void updateCandidate(Candidate uCandidate, Long Candidate_id, Long class_id) throws NoSuchVacancyException, NoSuchCandidateException {
        Vacancy Vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (class_id > 0) {
            if (Vacancy == null) throw new NoSuchVacancyException();
        }
        Candidate Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        //update
        Candidate.setSurname(uCandidate.getSurname());
        Candidate.setName(uCandidate.getName());
        Candidate.setJobFunction(uCandidate.getJobFunction());
        if (class_id > 0) Candidate.setVacancyByCandidates(Vacancy);
        else Candidate.setVacancyByCandidates(null);
        CandidateRepository.save(Candidate);
    }

    @Transactional
    public void deleteCandidate(Long Candidate_id) throws NoSuchCandidateException, ExistsInterviewResultForCandidatesException {
        Candidate Candidate = CandidateRepository.findById(Candidate_id).get();//2.0.0.M7
        if (Candidate == null) throw new NoSuchCandidateException();
        if (Candidate.getInterviewResults().size() != 0) throw new ExistsInterviewResultForCandidatesException();
        CandidateRepository.delete(Candidate);
    }
}
