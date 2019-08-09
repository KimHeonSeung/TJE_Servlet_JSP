-- 1��

select last_name, hire_date
from employees
where hire_date between '03-01-01' and '03-12-31';

-- 2��
select last_name
from employees
where LOWER(last_name) like '%a%' and LOWER(last_name) like '%e%';

-- 3��
select last_name, trunc(months_between(sysdate, hire_date)) as "�ټӿ���"
from employees;

-- 4��
-- ������
select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by job_id;
-- �μ���
select sum(salary)
from employees
where department_id in (20, 50, 80, 90)
group by departement_id;

-- 5��
select last_name, department_id, department_name
from employees inner join departments using (department_id)
where department_id is not null;

-- 6��
select last_name, salary
from employees
where manager_id = (select employee_id from employees where last_name = "Kochhar");