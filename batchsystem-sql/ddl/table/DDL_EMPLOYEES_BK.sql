
  CREATE TABLE "HR"."EMPLOYEES_BK" 
   (	"EMPLOYEE_ID" NUMBER(6,0), 
	"FIRST_NAME" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"LAST_NAME" VARCHAR2(25 BYTE) COLLATE "USING_NLS_COMP" NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(25 BYTE) COLLATE "USING_NLS_COMP" NOT NULL ENABLE, 
	"PHONE_NUMBER" VARCHAR2(20 BYTE) COLLATE "USING_NLS_COMP", 
	"HIRE_DATE" DATE NOT NULL ENABLE, 
	"JOB_ID" VARCHAR2(10 BYTE) COLLATE "USING_NLS_COMP" NOT NULL ENABLE, 
	"SALARY" NUMBER(8,2), 
	"COMMISSION_PCT" NUMBER(2,2), 
	"MANAGER_ID" NUMBER(6,0), 
	"DEPARTMENT_ID" NUMBER(4,0)
   )  DEFAULT COLLATION "USING_NLS_COMP" SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;

   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."EMPLOYEE_ID" IS 'Primary key of employees table.';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."FIRST_NAME" IS 'First name of the employee. A not null column.';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."LAST_NAME" IS 'Last name of the employee. A not null column.';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."EMAIL" IS 'Email id of the employee';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."PHONE_NUMBER" IS 'Phone number of the employee; includes country code and area code';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."HIRE_DATE" IS 'Date when the employee started on this job. A not null column.';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."JOB_ID" IS 'Current job of the employee; foreign key to job_id column of the
jobs table. A not null column.';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."SALARY" IS 'Monthly salary of the employee. Must be greater
than zero (enforced by constraint emp_salary_min)';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."COMMISSION_PCT" IS 'Commission percentage of the employee; Only employees in sales
department elgible for commission percentage';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."MANAGER_ID" IS 'Manager id of the employee; has same domain as manager_id in
departments table. Foreign key to employee_id column of employees table.
(useful for reflexive joins and CONNECT BY query)';
   COMMENT ON COLUMN "HR"."EMPLOYEES_BK"."DEPARTMENT_ID" IS 'Department id where employee works; foreign key to department_id
column of the departments table';
   COMMENT ON TABLE "HR"."EMPLOYEES_BK"  IS 'employees table. References with departments,
jobs, job_history tables. Contains a self reference.';