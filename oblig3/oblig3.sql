   -- Skript for å opprette databasen og legge inn litt data
    -- Skjema = datatyper
        -- Tabell(er) = tidogpenger 

-- MERK!!! DROP SCHEMA ... CASCADE sletter alt !!!
DROP SCHEMA IF EXISTS oblig3 CASCADE;

CREATE SCHEMA oblig3;
SET search_path TO oblig3;
    
-- Ikke nødvendig å slette tabellen(e) siden vi har tomt skjema, men ...
-- DROP TABLE tidogpenger;

-- Se https://www.postgresql.org/docs/8.0/static/datatype.html
-- for en oversikt over PostgreSQL datatyper

CREATE TABLE employee
(
    id SERIAL,
    username varchar UNIQUE,
    firstname varchar,
    lastname varchar,
    employment DATE, 
    pos varchar, 
    salary DECIMAL(10, 2),
    department varchar NOT NULL,
    
    CONSTRAINT id_PK PRIMARY KEY (id)
    
    
);

INSERT INTO
  employee(username, firstname, lastname, employment, pos, salary, department)
VALUES
    ('emilie1', 'emilie', 'hinna', '2020-04-08', 'data', 10000, 'IT'),
	('sunniva3', 'sunniva', 'hillesøy', '2020-04-20', 'boss', 90876, 'kronbar'), 
	('alvinTheChipmunk', 'alvin', 'gusfre', '2019-05-12', 'gudinne', 2, 'kronbar'), 
	('solveig123', 'Solveig', 'Kloppen', '2009-02-16', 'programleder', 500000, 'TV'), 
	('oyve', 'øyvind', 'espetvedt', '2007-02-15', 'vin elsker', 56, 'IT');
   
SELECT * FROM oblig3.employee;

CREATE TABLE department
(
	department_id SERIAL,
    department varchar UNIQUE, 
    director_id int NOT NULL,
    
    CONSTRAINT department_id_PK PRIMARY KEY (department_id)
    
     
);

INSERT INTO
	department(department, director_id)
VALUES 
	('IT', 1),
	('kronbar', 2),
	('TV', 4);
 
SELECT * FROM oblig3.department;

CREATE TABLE project 
(
	project_id SERIAL, 
	project_name varchar,
	
	CONSTRAINT project_id_pk PRIMARY KEY (project_id)
	
); 

INSERT INTO 
	project(project_name)
VALUES 
	('Administration'); 
	
	SELECT * FROM oblig3.project; 
	
CREATE TABLE projectparticipation 
(
	projectparticipation_id SERIAL,
	employee_id int NOT NULL, 
	project_id int NOT NULL, 
	hours int,
	position varchar,
	
	CONSTRAINT projectparticipation_PK PRIMARY KEY (projectparticipation_id)
	
);

INSERT INTO 
	projectparticipation(employee_id, project_id, hours, position)
VALUES 
	(1, 1, 0, 'økonomi ansvarlig');
	

ALTER TABLE oblig3.employee
  ADD CONSTRAINT department_FK FOREIGN KEY (department) REFERENCES oblig3.department(department);
  
 ALTER TABLE oblig3.department 
  ADD CONSTRAINT department_FK FOREIGN KEY (director_id) REFERENCES oblig3.employee(id);
  
 ALTER TABLE oblig3.projectparticipation
  ADD CONSTRAINT employeeP_FK FOREIGN KEY (employee_id) REFERENCES oblig3.employee(id), 
	ADD CONSTRAINT projectP_FK FOREIGN KEY (project_id) REFERENCES oblig3.project(project_id);
  
	
	