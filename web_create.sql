DROP SCHEMA IF EXISTS web CASCADE;
CREATE SCHEMA web;
SET search_path TO web;

CREATE TABLE city (
    city_id BIGSERIAL PRIMARY KEY,
    city_name text NOT NULL
);

CREATE TABLE applicant (
    applicant_id BIGSERIAL PRIMARY KEY,
    fullname text NOT NULL,
	contact_info text NOT NULL,
    age integer CONSTRAINT applicant_age_check CHECK(age >= 16) NOT NULL,
    city_id BIGINT
		CONSTRAINT applicant_city REFERENCES city,
    education jsonb,
	work_experience jsonb,
	status bool DEFAULT TRUE,
	desired_position text DEFAULT NULL,
	desired_salary BIGINT DEFAULT NULL,
	additional_info text DEFAULT NULL
);

CREATE TABLE company (
    company_id BIGSERIAL PRIMARY KEY,
    company_name text NOT NULL,
    workers_id integer[]
);

CREATE TABLE vacancy (
    company_id BIGINT 
		CONSTRAINT company_vacancy REFERENCES company,
    vacancy_id BIGSERIAL NOT NULL,
	vacancy_name text NOT NULL,
	salary BIGINT NOT NULL,
	requirements text
);
