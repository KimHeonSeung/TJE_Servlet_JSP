-- �򰡹��� 7�� 1)
select last_name, hire_date
from employees
where hire_date between '2003/01/01' and '2003/12/31';

-- �򰡹��� 7�� 2)
select last_name
from employees
where last_name like '%a%e%';
=> where last_name like '%a%' and '%e%'; �� ���ľ� �Ѵ�.

-- �򰡹��� 7�� 3)
select last_name, round(months_worked)
from employees;

-- �򰡹��� 7�� 4)
select a.job_id, sum(salary) as "������ �����հ�"
from employees a, jobs b
where a.department_id in (20, 50, 80, 90)
group by a.job_id;

select a.department_id, sum(salary) as "�μ��� �����հ�"
from employees a, departments b
where a.department_id in (20, 50, 80, 90)
group by a.department_id;

-- �򰡹��� 7�� 5)
select a.last_name, b.department_id, b.department_name
from employees a inner join departments b on a.department_id = b.department_id;
=> select last_name, e.department_id, department_name
	from employees e inner join departments d using(department_id); �� ��뵵 �����ϴ�.

-- �򰡹��� 7�� 6)
select a.last_name, a.salary
from employees a
where a.manager_id = (
  select employee_id from employees where last_name = 'Kochhar'
);