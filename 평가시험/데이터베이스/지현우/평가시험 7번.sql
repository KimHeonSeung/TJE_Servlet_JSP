-- 1번 문제
select last_name, hire_date
from employees
where hire_date >= '03/01/01' and hire_date <= '03/12/31';

-- 2번 문제
select last_name 
from employees
where last_name like '%a%' or last_name like '%e%';

-- 3번 문제
select first_name, round(months_worked)
from employees;

-- 4번 
select DEPARTMENT_name,DEPARTMENT_ID ,sum(salary)
from employees inner join DEPARTMENTS using(DEPARTMENT_ID)
where DEPARTMENT_ID = 20 or DEPARTMENT_ID = 50 or DEPARTMENT_ID = 80 or DEPARTMENT_ID = 90
GROUP by DEPARTMENT_name,DEPARTMENT_ID;

-- 5번 문제 
select last_name, DEPARTMENT_ID, DEPARTMENT_NAME
from employees inner join DEPARTMENTS using(DEPARTMENT_ID);

-- 6번 문제
select last_name, salary
from employees
where job_id = 'AC_ACCOUNT';

