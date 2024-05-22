DROP SCHEMA IF EXISTS web CASCADE;
CREATE SCHEMA web;
SET search_path TO web;

-- DROP TYPE IF EXISTS role_name CASCADE;
-- CREATE TYPE role_name AS ENUM('соискатель', 'студент', 'работодатель');

DROP DOMAIN IF EXISTS email;
DROP EXTENSION IF EXISTS citext;
CREATE EXTENSION citext;
CREATE DOMAIN email AS citext
  CHECK ( value ~ '^[a-zA-Z0-9.!#$%&''*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$' );

CREATE TABLE auth (
	mail email NOT NULL,
	pwd text NOT NULL,
    auth_id BIGSERIAL PRIMARY KEY,
    auth_role text NOT NULL
);

CREATE TABLE city (
    city_id SERIAL PRIMARY KEY,
    city_name text NOT NULL
);

CREATE TABLE edlevel (
    edlevel_id SERIAL PRIMARY KEY,
    edlevel_name text NOT NULL
);

CREATE TABLE applicant (
	auth_id BIGINT
		CONSTRAINT applicant_auth REFERENCES auth,
    applicant_id BIGSERIAL PRIMARY KEY,
    fullname text NOT NULL,
	contact_info text NOT NULL,
    age integer CONSTRAINT applicant_age_check CHECK(age >= 16) NOT NULL,
    city_id INT
		CONSTRAINT applicant_city REFERENCES city,
	status bool DEFAULT TRUE
);

CREATE TABLE experience (
	applicant_id BIGINT
		CONSTRAINT experience_applicant REFERENCES applicant,
    experience_id BIGSERIAL PRIMARY KEY,
    company text NOT NULL,
	"position" text NOT NULL,
	begin_date date NOT NULL,
	end_date date,
	salary BigInt 
		CONSTRAINT experience_salary_check CHECK(salary >= 0) NOT NULL
);

CREATE TABLE education (
	applicant_id BIGINT
		CONSTRAINT education_applicant REFERENCES applicant,
    education_id BIGSERIAL PRIMARY KEY,
	edlevel_id INT
		CONSTRAINT education_edlevel REFERENCES edlevel,
    institute text NOT NULL,
	faculty text NOT NULL,
	begin_date date NOT NULL,
	end_date date
);

CREATE TABLE resume (
    resume_id BIGSERIAL PRIMARY KEY,
	applicant_id BIGINT 
		CONSTRAINT resume_applicant REFERENCES applicant,
	desired_position text NOT NULL,
	desired_salary BIGINT
		CONSTRAINT resume_salary_check CHECK(desired_salary >= 0) NOT NULL
);

CREATE TABLE experience_resume (
	resume_id BIGINT
		CONSTRAINT resume_experience REFERENCES resume,
    experience_id BIGINT
		CONSTRAINT experience_resume REFERENCES experience,
	additional_info text DEFAULT ''
);

CREATE TABLE education_resume (
	resume_id BIGINT
		CONSTRAINT resume_education REFERENCES resume,
    education_id BIGINT
		CONSTRAINT education_resume REFERENCES education,
	additional_info text DEFAULT ''
);

CREATE TABLE company (
	auth_id BIGINT
		CONSTRAINT company_auth REFERENCES auth,
    company_id BIGSERIAL PRIMARY KEY,
    company_name text NOT NULL
);

CREATE TABLE vacancy (
    vacancy_id BIGSERIAL PRIMARY KEY,
	company_id BIGINT 
		CONSTRAINT vacancy_company REFERENCES company,
	vacancy_name text NOT NULL,
	salary BIGINT
		CONSTRAINT vacancy_salary_check CHECK(salary >= 0) NOT NULL,
	requirements text NOT NULL,
	additional_info text DEFAULT ''
);

CREATE TABLE applicant_company (
	applicant_id BIGINT
		CONSTRAINT applicant_company REFERENCES applicant,
    company_id BIGINT
		CONSTRAINT company_applicant REFERENCES company,
	begin_date date NOT NULL,
	end_date date,
	position_name text NOT NULL,
	salary BIGINT
		CONSTRAINT applicant_company_salary_check CHECK(salary >= 0)
);
