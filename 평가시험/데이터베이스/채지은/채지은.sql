



-- 1��
 select last_name, hire_date
 from employees
 where hire_date  between '03/01/01' and '03/12/31';
 
 --2��
 select last_name
 from employees
 where last_name like '%a%' and last_name like '%e%';
 
 --3��
 
 select last_name, round(months_between(sysdate, hire_date)) as "�ټӿ���"
 from employees;
 
 
 --4��
select sum(salary)
 from employees
 where department_id in(20,50,80,90)
 group by job_id;
 
select sum(salary)
 from employees
 where department_id in(20,50,80,90)
 group by department_id;
 
--5��
select last_name, e1.department_id, d1.department_name
from employees e1 inner JOIN departments d1
on e1.department_id = d1.department_id;

--6��
select b.last_name, b.salary
from employees a, employees b
where a.employee_id = b.manager_id
and a.last_name='Kochhar';
