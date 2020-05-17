package com.doc.lab2.service;

import com.doc.lab2.exceptions.ExistsCandidatesForVacancyException;
import com.doc.lab2.exceptions.NoSuchVacancyException;
import com.doc.lab2.domain.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VacancyService {
    @Autowired
    com.doc.lab2.Repository.VacancyRepository VacancyRepository;
    private boolean ascending;

    @Autowired
    com.doc.lab2.Repository.CandidateRepository CandidateRepository;

    public List<Vacancy> getAllVacancies() {
        return VacancyRepository.findAll();
    }

    public Vacancy getVacancy(Long class_id) throws NoSuchVacancyException {
        Vacancy vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        System.out.println(vacancy);
        if (vacancy == null) throw new NoSuchVacancyException();
        return vacancy;
    }

    @Transactional
    public void createVacancy(Vacancy vacancy) {
        VacancyRepository.save(vacancy);
    }

    @Transactional
    public void updateVacancy(Vacancy uVacancy, Long class_id) throws NoSuchVacancyException {
        Vacancy vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7

        if (vacancy == null) throw new NoSuchVacancyException();
        vacancy.setCandidates(uVacancy.getCandidates());
        VacancyRepository.save(uVacancy);
    }

    @Transactional
    public void deleteVacancy(Long class_id) throws NoSuchVacancyException, ExistsCandidatesForVacancyException {
        Vacancy vacancy = VacancyRepository.findById(class_id).get();//2.0.0.M7
        if (vacancy == null) throw new NoSuchVacancyException();
        if (vacancy.getCandidates().size() != 0) throw new ExistsCandidatesForVacancyException();
        VacancyRepository.delete(vacancy);
    }


}
