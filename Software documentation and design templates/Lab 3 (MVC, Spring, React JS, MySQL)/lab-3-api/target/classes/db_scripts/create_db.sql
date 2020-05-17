DROP DATABASE IF EXISTS RecruitingSuite;
create database RecruitingSuite;
use RecruitingSuite;
CREATE TABLE vacancies
(
  id int NOT NULL AUTO_INCREMENT ,
  name nvarchar(50) NULL,
  description nvarchar(50) NULL,
  salary int NULL,
  Primary key (id)
);


CREATE TABLE candidates
(
  id int NOT NULL AUTO_INCREMENT ,
  surname nvarchar(50) NULL,
  name nvarchar(50) NULL,
  job_function nvarchar(50) NULL,
  vacancy_id int NULL,
  Primary key (id),
  CONSTRAINT FK_candidates_vacancies FOREIGN KEY (vacancy_id)
  REFERENCES vacancies (id)
);


CREATE TABLE interview_results
(
  id int NOT NULL AUTO_INCREMENT ,
  result nvarchar(50) NOT NULL,
  candidate_id int NOT NULL,
  Primary key (id)
);


CREATE TABLE candidate_interview_results
(
  id int NOT NULL AUTO_INCREMENT ,
  candidate_id int NOT NULL,
  interview_result_id int NOT NULL,
  CONSTRAINT FK_candidates FOREIGN KEY (candidate_id)
  REFERENCES candidates(id),
  CONSTRAINT FK_interview_results FOREIGN KEY (interview_result_id)
  REFERENCES interview_results (id),
  Primary key (id)
);