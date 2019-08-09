



-- 1번
 select last_name, hire_date
 from employees
 where hire_date  between '03/01/01' and '03/12/31';
 
 --2번
 select last_name
 from employees
 where last_name like '%a%' and last_name like '%e%';
 
 --3번
 
 select last_name, round(months_between(sysdate, hire_date)) as "근속월수"
 from employees;
 
 
 --4번
select sum(salary)
 from employees
 where department_id in(20,50,80,90)
 group by job_id;
 
select sum(salary)
 from employees
 where department_id in(20,50,80,90)
 group by department_id;
 
--5번
select last_name, e1.department_id, d1.department_name
from employees e1 inner JOIN departments d1
on e1.department_id = d1.department_id;

--6번
select b.last_name, b.salary
from employees a, employees b
where a.employee_id = b.manager_id
and a.last_name='Kochhar';
