-- Statements for creating the tables

CREATE TABLE customer
(
    name VARCHAR(30) PRIMARY KEY,
    address VARCHAR(40),
    category INT,
    CONSTRAINT chk_cat CHECK (category>=1 AND category<=10)
);

CREATE TABLE assembly_acc
(
    account_no INT PRIMARY KEY,
    date_established DATE,
    details_1 FLOAT, 
);

CREATE TABLE dept_acc
(
    account_no INT PRIMARY KEY,
    date_established DATE,
    details_2 FLOAT, 
);

CREATE TABLE process_acc
(
    account_no INT PRIMARY KEY,
    date_established DATE,
    details_3 FLOAT, 
);

CREATE TABLE assembly
(
    assembly_id INT PRIMARY KEY,
    date_ordered DATE,
    details VARCHAR(30),
    cust_name VARCHAR(30),
    acc_no INT,
    FOREIGN KEY (cust_name) REFERENCES customer(name), 
    FOREIGN KEY (acc_no) REFERENCES assembly_acc(account_no)
);

CREATE TABLE department
(
    dept_no INT PRIMARY KEY,
    dept_data VARCHAR(30),
    acc_no INT, 
    FOREIGN KEY (acc_no) REFERENCES dept_acc(account_no)
);

CREATE TABLE process
(
    process_id INT PRIMARY KEY,
    process_data VARCHAR(30),
    d_no INT,
    acc_no INT, 
    FOREIGN KEY (d_no) REFERENCES department(dept_no),
    FOREIGN KEY (acc_no) REFERENCES process_acc(account_no)
);

CREATE TABLE cut_process
(
    process_id INT PRIMARY KEY,
    cut_type VARCHAR(20),
    machine_type VARCHAR(20)
);

CREATE TABLE paint_process
(
    process_id INT PRIMARY KEY,
    paint_type VARCHAR(20),
    paint_method VARCHAR(20),
);

CREATE TABLE fit_process
(
    process_id INT PRIMARY KEY,
    fit_type VARCHAR(20),
);

CREATE TABLE job
(
    job_no INT PRIMARY KEY,
    date_commenced DATE,
    date_completed DATE,
    assembly_id INT,
    process_id INT,  
    FOREIGN KEY (assembly_id) REFERENCES assembly,
    FOREIGN KEY (process_id) REFERENCES process  
);

CREATE TABLE cut_job
(
    job_no INT PRIMARY KEY,
    machine_type VARCHAR(20),
    machine_time FLOAT,
    material VARCHAR(20),
    labor_time FLOAT
);

CREATE TABLE fit_job
(
    job_no INT PRIMARY KEY,
    labor_time FLOAT
);

CREATE TABLE paint_job
(
    job_no INT PRIMARY KEY,
    color VARCHAR(20),
    volume FLOAT,
    labor_time FLOAT
);

CREATE TABLE transactions
(
    transaction_no INT PRIMARY KEY,
    sup_cost FLOAT,
    job_no INT,
    ass_acc_no INT,
    dept_acc_no INT,
    proc_acc_no INT,
    FOREIGN KEY (job_no) REFERENCES job,
    FOREIGN KEY (ass_acc_no) REFERENCES assembly_acc(account_no),
    FOREIGN KEY (dept_acc_no) REFERENCES dept_acc(account_no),
    FOREIGN KEY (proc_acc_no) REFERENCES process_acc(account_no)
);


-- Statements for creating the indexes

CREATE INDEX cust1 ON customer(category);

CREATE INDEX process1 ON process(acc_no);

CREATE INDEX process2 ON process(d_no);

CREATE INDEX job1 ON job(process_id);

CREATE INDEX job2 ON job(date_completed);

CREATE INDEX job3 ON job(assembly_id);