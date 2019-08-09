-- 1.2003 �⵵�� �Ի��� ������� ���� �Ի��� ���
select last_name,hire_date
from EMPLOYEES
where HIRE_DATE between '2003/01/01' and '2003/12/31';

-- 2.���� a�� e�� ���Ե� ������� �� ���.
select last_name
from employees
where lower(last_name) like '%a%' and lower(last_name) like '%e%';

-- 3.������� ���� �ټӰ������� ����Ѵ�. �ټӰ����� �÷��� MONTHS_WORKED�̴�.

-- 4.������,�μ��� �����հ踦 ���Ѵ�. 20,50,80,90�� �μ����� �����Ѵ�.
select sum(salary)
from employees
where department_id in (20,50,80,90)
group by job_id;

-- 5.�μ��� ��ġ�� ������� ã�´�. ��,�μ���ȣ,�μ����� ����Ѵ�.
select last_name, department_id, department_name
from employees inner join departments using (department_id)
where department_id is not null;

-- 6. Kochhar���� �����ϴ� ������� ��,������ ����Ѵ�.
