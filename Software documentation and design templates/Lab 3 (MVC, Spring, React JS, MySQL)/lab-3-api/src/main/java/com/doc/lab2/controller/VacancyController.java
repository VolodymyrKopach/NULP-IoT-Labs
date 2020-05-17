package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.service.VacancyService;
import com.doc.lab2.DTO.impl.VacancyDTO;
import com.doc.lab2.domain.Vacancy;

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
public class VacancyController {
    @Autowired
    VacancyService vacancyService;

    @GetMapping(value = "/api/vacancy")
    public ResponseEntity<Set<VacancyDTO>> getAllVacancy() throws NoSuchCandidateException, NoSuchInterviewResultException, NoSuchVacancyException {
        List<Vacancy> vacancies = vacancyService.getAllVacancies();
        Link link = linkTo(methodOn(VacancyController.class).getAllVacancy()).withSelfRel();

        Set<VacancyDTO> vacancyDTOS = new HashSet<>();
        for (Vacancy entity : vacancies) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            VacancyDTO dto = new VacancyDTO(entity, selfLink);
            vacancyDTOS.add(dto);
        }

        return new ResponseEntity<>(vacancyDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/api/vacancy/{vacancy_id}")
    public ResponseEntity<VacancyDTO> getVacancy(@PathVariable Long vacancy_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        Vacancy vacancy = vacancyService.getVacancy(vacancy_id);
        Link link = linkTo(methodOn(VacancyController.class).getVacancy(vacancy_id)).withSelfRel();
        System.out.println(vacancy);
        VacancyDTO vacancyDTO = new VacancyDTO(vacancy, link);

        return new ResponseEntity<>(vacancyDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/vacancy/{vacancy_id}")
    public  ResponseEntity<VacancyDTO> addVacancy(@RequestBody Vacancy newVacancy) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        vacancyService.createVacancy(newVacancy);
        Link link = linkTo(methodOn(VacancyController.class).getVacancy(newVacancy.getId())).withSelfRel();

        VacancyDTO vacancyDTO = new VacancyDTO(newVacancy, link);

        return new ResponseEntity<>(vacancyDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/vacancy/{vacancy_id}")
    public  ResponseEntity<VacancyDTO> updateVacancy(@RequestBody Vacancy uVacancy, @PathVariable Long vacancy_id) throws NoSuchVacancyException, NoSuchCandidateException, NoSuchInterviewResultException {
        vacancyService.updateVacancy(uVacancy, vacancy_id);
        Vacancy vacancy = vacancyService.getVacancy(vacancy_id);
        Link link = linkTo(methodOn(VacancyController.class).getVacancy(vacancy_id)).withSelfRel();

        VacancyDTO vacancyDTO = new VacancyDTO(vacancy, link);

        return new ResponseEntity<>(vacancyDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/vacancy/{vacancy_id}")
    public  ResponseEntity deleteVacancy(@PathVariable Long vacancy_id) throws NoSuchVacancyException, ExistsPersonsForCityException, ExistsCandidatesForVacancyException {
        vacancyService.deleteVacancy(vacancy_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
