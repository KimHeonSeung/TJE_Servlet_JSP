-- 1번

select last_name, hire_date
from employees
where hire_date between '03-01-01' and '03-12-31';

-- 2번
select last_name
from employees
where LOWER(last_name) like '%a%' and LOWER(last_name) like '%e%';

-- 3번
select last_name, trunc(months_between(sysdate, hire_date)) as "근속월수"
from employees;

-- 4번
-- 직업별
select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by job_id;
-- 부서별
select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by departement_id;

-- 5번
select last_name, department_id, department_name
from employees inner join departments using (department_id)
where department_id is not null;

-- 6번
select last_name, salary
from employees
where manager_id = (select employee_id from employees where last_name = "Kochhar");