package com.doc.lab2.controller;

import com.doc.lab2.exceptions.*;
import com.doc.lab2.DTO.impl.MessageDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchVacancyException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such vacancy not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchInterviewResultException.class)
    ResponseEntity<MessageDTO> handleNoSushPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such candidate not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchCandidateException.class)
    ResponseEntity<MessageDTO> handleNoSushBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such interview result not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsCandidatesForVacancyException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are candidates for this vacancies"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsCandidatesForInterviewResultException.class)
    ResponseEntity<MessageDTO> handleExistsBooksForPersonException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are interview results for this candidate"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsInterviewResultForCandidatesException.class)
    ResponseEntity<MessageDTO> handleExistsPersonsForBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are candidates for this interview results"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsCandidateInInterviewResultException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsBookInPersonExceptionException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The candidate already contain this interview result"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CandidateAbsentException.class)
    ResponseEntity<MessageDTO> handleBookAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this interview result is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InterviewResultHasNotCandidateException.class)
    ResponseEntity<MessageDTO> handlePersonHasNotBookException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The candidate hasn't this interview result"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}
