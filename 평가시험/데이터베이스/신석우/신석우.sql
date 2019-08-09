/* 1) */
select first_name, hire_date
from employees
where hire_date between '2003-01-01' and '2003-12-31';

/* 2) */
select first_name
from employees
where lower(first_name) like '%a%' and lower(first_name) like '%e%';

/* 3) */
select first_name, round(months_worked)
from employees;

/* 4) */
select sum(salary)
from employees
where department_tid in (20,50,80,90)
group by job_id;

select sum(salary)
from employees
where department_id in (20,50,80,90)
group by department_id;

/* 5) */
select first_name, department_id, department_name
from employees inner join departments using(department_id);

/* 6) */
select first_name, salary
from employees
where manager_id = (
	select employee_id
    from employees
    where last_name = 'Kochhar' or first_name = 'Kochhar');