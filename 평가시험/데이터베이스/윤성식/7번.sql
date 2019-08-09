-- 1)
select last_name, hire_date
from employees
where hire_date between '03/01/01' and '03/12/31';

-- 2)
select last_name
from employees
where last_name like '%a%' and last_name like '%e%';

-- 3)
select last_name, ROUND(months_worked)
from employees;

-- 4) 



-- 5)
select last_name, e.department_id, department_name
from EMPLOYEES e inner join departments d using(department_id);

-- 6)
select last_name, salary
from EMPLOYEES
where MANAGER_ID =(
          select EMPLOYEE_ID
          from EMPLOYEES
          where last_name='kochhar');
