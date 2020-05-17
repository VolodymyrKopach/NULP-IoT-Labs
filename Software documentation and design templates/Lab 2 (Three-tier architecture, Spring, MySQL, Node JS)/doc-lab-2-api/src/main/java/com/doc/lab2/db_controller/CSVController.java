package com.doc.lab2.db_controller;

import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.InterviewResult;
import com.doc.lab2.domain.Vacancy;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVController {
    public List<Vacancy> getVacanciesFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<Vacancy> vacanciesArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            boolean toFind = true;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(line.equals("##")) {toFind = false;}

                if (toFind){
                    String[] vacancies = line.split(cvsSplitBy);

                    vacanciesArray.add(new Vacancy(Long.parseLong(vacancies[0]), vacancies[1], vacancies[2],
                            Integer.parseInt(vacancies[3])));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return vacanciesArray;
    }

    public List<Candidate> getCandidateFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<Candidate> candidatesArray = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            boolean toFind = false;
            while ((line = br.readLine()) != null) {
                if(line.equals("###")) {toFind = false;}

                if(toFind){
                    String[] vacancies = line.split(cvsSplitBy);

                    Candidate newCandidate = new Candidate(Long.parseLong(vacancies[0]), vacancies[1], vacancies[2],
                            vacancies[3], Long.parseLong(vacancies[4]));

                    candidatesArray.add(newCandidate);
                }

                if(line.equals("##")) {toFind = true;}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return candidatesArray;
    }

    public List<InterviewResult> getInterviewResultsFromCSV() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csv_files/data.csv");

        String line = "";
        String cvsSplitBy = ",";

        List<InterviewResult> interviewResults = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            boolean toFind = false;
            while ((line = br.readLine()) != null) {
                if(toFind){
                    String[] vacancies = line.split(cvsSplitBy);

                    InterviewResult newInterviewResult = new InterviewResult(Long.parseLong(vacancies[0]), vacancies[1],
                            Long.parseLong(vacancies[2]));

                    interviewResults.add(newInterviewResult);
                }

                if(line.equals("###")) {toFind = true;}
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return interviewResults;
    }
}
