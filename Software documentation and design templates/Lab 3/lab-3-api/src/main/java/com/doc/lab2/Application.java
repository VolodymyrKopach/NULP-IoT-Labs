package com.doc.lab2;

import com.doc.lab2.db_controller.CSVController;
import com.doc.lab2.db_controller.DBController;
import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.InterviewResult;
import com.doc.lab2.domain.Vacancy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        CSVController csvController = new CSVController();
        DBController dbController = new DBController();
        try {
            for (Vacancy vacancy: csvController.getVacanciesFromCSV()) {
                dbController.saveVacancy(vacancy);
            }

            for (Candidate candidate: csvController.getCandidateFromCSV()) {
                dbController.saveCandidate(candidate);
            }

            for (InterviewResult interviewResult: csvController.getInterviewResultsFromCSV()) {
                dbController.saveInterviewResult(interviewResult);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}