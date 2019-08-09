/*1)2003�⵵�� �Ի��� ������� ���� �Ի����� ����Ѵ�.*/
select last_name, hire_date from employees
where hire_date>='2003/01/01' and hire_date<='2003/12/31';

/*2)���� a�� e�� ���Ե� ������� ���� ����Ѵ�.*/
select last_name from employees
where last_name like '%a%' and last_name like '%e%';

/*3)������� ���� �ټӰ������� ����Ѵ�. �ټӰ����� Į������ MONTHS_WORKED�̴�.
�ܿ����ڴ� �ݿø� ó���Ѵ�.*/
select last_name, round(MONTHS_BETWEEN(sysdate,hire_date)) as "MONTHS_WORKED"
from employees;

/*4)������, �μ��� �����հ踦 ���Ѵ�. 20,50,80,90�� �μ����� �����Ѵ�.*/
select job_id,
nvl(sum(decode(DEPARTMENT_ID,20,salary)),0) as "dept 20",
nvl(sum(decode(DEPARTMENT_ID,50,salary)),0) as "dept 50",
nvl(sum(decode(DEPARTMENT_ID,80,salary)),0) as "dept 80",
nvl(sum(decode(DEPARTMENT_ID,90,salary)),0) as "dept 90"
from employees
GROUP BY job_id;

/*5)�μ��� ��ġ�� ����鸸 ã�´�. ��, �μ���ȣ, �μ����� ����Ѵ�.*/
select e.last_name,e.department_id,d.department_name
from employees e join departments d on e.DEPARTMENT_ID=d.DEPARTMENT_ID;

/*6)Kochhar���� �����ϴ� ������� ��, ������ ����Ѵ�.*/
select e.last_name, e.salary, j.job_id from employees e
join jobs j on j.job_id=e.job_id where j.job_id='AD_ASST';