1
select last_name, hire_date
from employees
where hire_date BETWEEN '2003/01/01' and '2003/12/31';

2
select last_name
from employees
where last_name like '%a%' and last_name like '%e%';

3 XXX
select first_name, round(months_worked)
from employees;

4
select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by job_id;

select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by department_id;

5
select last_name, e.department_id, department_name
from employees e inner join departments d using(department_id);

6
select last_name, salary
from employees
where manager_id = (
  select employee_id from employees where last_name = 'Kochhar'
);