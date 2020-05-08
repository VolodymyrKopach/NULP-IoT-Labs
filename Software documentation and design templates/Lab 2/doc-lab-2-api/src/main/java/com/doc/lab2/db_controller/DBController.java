package com.doc.lab2.db_controller;
import com.doc.lab2.domain.Candidate;
import com.doc.lab2.domain.InterviewResult;
import com.doc.lab2.domain.Vacancy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DBController {

    public void saveVacancy(Vacancy vacancy){
        try {
            URL url = new URL("http://localhost:8080/api/vacancy/%7Bvacancy_id%7D");
            addObjectToDb(vacancy.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCandidate(Candidate candidate){

        try {
            URL url = new URL("http://localhost:8080/api/candidate/vacancy/" + candidate.getRelatedVacancyId());
            addObjectToDb(candidate.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInterviewResult(InterviewResult interviewResult){
        try {
            URL url = new URL("http://localhost:8080/api/interview_result");
            addObjectToDb(interviewResult.toJsonObject(), url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addObjectToDb(String jsonObject, URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");

        con.setDoOutput(true);

        try(OutputStream os = con.getOutputStream()){
            byte[] input = jsonObject.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        int code = con.getResponseCode();
        System.out.println(code);

        try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
}
