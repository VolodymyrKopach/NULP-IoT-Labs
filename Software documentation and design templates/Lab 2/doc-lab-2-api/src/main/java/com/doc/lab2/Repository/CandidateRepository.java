package com.doc.lab2.Repository;

import com.doc.lab2.domain.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
