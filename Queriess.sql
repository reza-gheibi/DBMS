-- Stored procedure for Query 1

CREATE PROCEDURE proc1
    @name VARCHAR(30),
    @add VARCHAR(40),
    @cat INT
AS
BEGIN
    INSERT INTO customer VALUES (@name, @add, @cat);  
END 


-- Stored procedure for Query 2
CREATE PROCEDURE proc2
    @dno INT,
    @data VARCHAR(30)
AS
BEGIN
    INSERT INTO department VALUES (@dno, @data, null);  
END  


-- Stored procedure for Query 3
CREATE PROCEDURE proc3
    @aid INT,
    @date DATE,
    @det VARCHAR(30),
    @cname VARCHAR(30)
AS
BEGIN
    INSERT INTO assembly VALUES (@aid, @date, @det, @cname, null);
END


-- Stored procedures for Query 4
CREATE PROCEDURE proc4
    @pid INT,
    @pdata VARCHAR(30),
    @dno INT
AS
BEGIN
    INSERT INTO process VALUES (@pid, @pdata, @dno, null);  
END  

CREATE PROCEDURE proc4_1
    @pid INT,
    @ctype VARCHAR(20),
    @mtype VARCHAR(20)
AS
BEGIN
    INSERT INTO cut_process VALUES (@pid, @ctype, @mtype);  
END  

CREATE PROCEDURE proc4_2
    @pid INT,
    @ptype VARCHAR(20),
    @pmethod VARCHAR(20)
AS
BEGIN
    INSERT INTO paint_process VALUES (@pid, @ptype, @pmethod);  
END  

CREATE PROCEDURE proc4_3
    @pid INT,
    @ftype VARCHAR(20)
AS
BEGIN
    INSERT INTO fit_process VALUES (@pid, @ftype);  
END  


-- Stored procedure for Query 5
CREATE PROCEDURE proc5
    @acc INT,
    @date DATE,
    @type INT,
    @id INT
AS
BEGIN
    IF (@type = 1) 
    INSERT INTO assembly_acc VALUES (@acc, @date, 0); 
    UPDATE assembly SET acc_no=@acc WHERE assembly_id=@id;
    
    IF (@type = 2) 
    INSERT INTO dept_acc VALUES (@acc, @date, 0); 
    UPDATE department SET acc_no=@acc WHERE dept_no=@id;
    
    IF (@type = 3)
    INSERT INTO process_acc VALUES (@acc, @date, 0); 
    UPDATE process SET acc_no=@acc WHERE process_id=@id;
END 


-- Stored procedure for Query 6
CREATE PROCEDURE proc6
    @jno INT,
    @aid INT,
    @pid INT,
    @sdate DATE
AS
BEGIN
    INSERT INTO job VALUES (@jno, @sdate, null, @aid, @pid);
END  


-- Stored procedures for Query 7
CREATE PROCEDURE proc7
    @jno INT,
    @edate DATE
AS
BEGIN
    UPDATE job SET date_completed=@edate WHERE job_no=@jno;
END  

CREATE PROCEDURE proc7_1
    @jno INT,
    @mtype VARCHAR(20),
    @mtime FLOAT,
    @mat VARCHAR(20),
    @ltime FLOAT
AS
BEGIN
    INSERT INTO cut_job VALUES (@jno, @mtype, @mtime, @mat, @ltime);
END  

CREATE PROCEDURE proc7_2
    @jno INT,
    @ltime FLOAT
AS
BEGIN
    INSERT INTO fit_job VALUES (@jno, @ltime);
END  

CREATE PROCEDURE proc7_3
    @jno INT,
    @color VARCHAR(20),
    @vol FLOAT,
    @ltime FLOAT
AS
BEGIN
    INSERT INTO paint_job VALUES (@jno, @color, @vol, @ltime);
END  


-- Stored procedure for Query 8
CREATE PROCEDURE proc8
    @tno INT,
    @scost FLOAT,
    @jno INT
AS
BEGIN
    DECLARE @aacc INT, @dacc INT, @pacc INT;

    SET @aacc = (SELECT acc_no FROM assembly as a, job as j WHERE job_no=@jno AND a.assembly_id=j.assembly_id);
    SET @pacc = (SELECT acc_no FROM process as p, job as j WHERE job_no=@jno AND p.process_id=j.process_id);
    SET @dacc = (SELECT d.acc_no FROM department as d, process as p WHERE dept_no=d_no AND p.acc_no=@pacc);
    
    INSERT INTO transactions VALUES (@tno, @scost, @jno, @aacc, @dacc, @pacc);
    UPDATE assembly_acc SET details_1 = details_1 + @scost WHERE account_no = @aacc;
    UPDATE dept_acc SET details_2 = details_2 + @scost WHERE account_no = @dacc;
    UPDATE process_acc SET details_3 = details_3 + @scost WHERE account_no = @pacc;
END  


-- Stored procedure for Query 9
CREATE PROCEDURE proc9
    @aid INT
AS
BEGIN
    SELECT details_1 FROM assembly, assembly_acc WHERE account_no=acc_no AND assembly_id=@aid;
END 


-- Stored procedure for Query 10
CREATE PROCEDURE proc10
    @dno INT,
    @date DATE
AS
BEGIN
    DECLARE @t1 FLOAT, @t2 FLOAT, @t3 FLOAT, @tt FLOAT;

    SET @t1 = (SELECT sum(labor_time) FROM job as j, cut_job as c WHERE date_completed=@date AND j.job_no=c.job_no AND process_id in (SELECT process_id from process WHERE d_no=@dno));
    IF @t1 IS NULL SET @t1 = 0;
    SET @t2 = (SELECT sum(labor_time) FROM job as j, fit_job as c WHERE date_completed=@date AND j.job_no=c.job_no AND process_id in (SELECT process_id from process WHERE d_no=@dno));
    IF @t2 IS NULL SET @t2 = 0;
    SET @t3 = (SELECT sum(labor_time) FROM job as j, paint_job as c WHERE date_completed=@date AND j.job_no=c.job_no AND process_id in (SELECT process_id from process WHERE d_no=@dno));
    IF @t3 IS NULL SET @t3 = 0;

    SET @tt = @t1 + @t2 + @t3
    SELECT @tt;
END 


-- Stored procedure for Query 11
CREATE PROCEDURE proc11
    @aid INT
AS
BEGIN
    SELECT job.process_id, d_no, date_commenced FROM job, process WHERE job.process_id=process.process_id AND assembly_id=@aid ORDER BY date_commenced;
END


-- Stored procedures for Query 12
CREATE PROCEDURE proc12_1
    @date DATE,
    @dno  INT
AS
BEGIN
    SELECT j.job_no, date_commenced, date_completed, machine_type, machine_time, material, labor_time, assembly_id
    FROM job as j, process as p, cut_job as c
    WHERE date_completed=@date AND j.process_id=p.process_id AND j.job_no=c.job_no AND d_no=@dno
END

CREATE PROCEDURE proc12_2
    @date DATE,
    @dno  INT
AS
BEGIN
    SELECT j.job_no, date_commenced, date_completed, labor_time, assembly_id
    FROM job as j, process as p, fit_job as c
    WHERE date_completed=@date AND j.process_id=p.process_id AND j.job_no=c.job_no AND d_no=@dno
END

CREATE PROCEDURE proc12_3
    @date DATE,
    @dno  INT
AS
BEGIN
    SELECT j.job_no, date_commenced, date_completed, color, volume, labor_time, assembly_id
    FROM job as j, process as p, paint_job as c
    WHERE date_completed=@date AND j.process_id=p.process_id AND j.job_no=c.job_no AND d_no=@dno
END


-- Stored procedure for Query 13
CREATE PROCEDURE proc13
    @lb INT,
    @ub INT
AS
BEGIN
    SELECT * FROM customer WHERE category BETWEEN @lb AND @ub ORDER BY name; 
END


-- Stored procedure for Query 14
CREATE PROCEDURE proc14
    @lb INT,
    @ub INT
AS
BEGIN
    DELETE FROM transactions WHERE job_no IN (SELECT job_no FROM cut_job WHERE job_no BETWEEN @lb AND @ub);
    DELETE FROM job WHERE job_no IN (SELECT job_no FROM cut_job WHERE job_no BETWEEN @lb AND @ub);
    DELETE FROM cut_job WHERE job_no BETWEEN @lb AND @ub;
END


-- Stored procedure for Query 15
CREATE PROCEDURE proc15
    @jno INT,
    @col VARCHAR(20)
AS
BEGIN
    UPDATE paint_job SET color = @col WHERE job_no = @jno;
END