/*1)2003년도에 입사한 사원들의 성과 입사일을 출력한다.*/
select last_name, hire_date from employees
where hire_date>='2003/01/01' and hire_date<='2003/12/31';

/*2)성에 a와 e가 포함된 사원들의 성을 출력한다.*/
select last_name from employees
where last_name like '%a%' and last_name like '%e%';

/*3)사원들의 성과 근속개월수를 출력한다. 근속개월수 칼럼명은 MONTHS_WORKED이다.
잔여일자는 반올림 처리한다.*/
select last_name, round(MONTHS_BETWEEN(sysdate,hire_date)) as "MONTHS_WORKED"
from employees;

/*4)직업별, 부서별 월급합계를 구한다. 20,50,80,90번 부서만을 조사한다.*/
select job_id,
nvl(sum(decode(DEPARTMENT_ID,20,salary)),0) as "dept 20",
nvl(sum(decode(DEPARTMENT_ID,50,salary)),0) as "dept 50",
nvl(sum(decode(DEPARTMENT_ID,80,salary)),0) as "dept 80",
nvl(sum(decode(DEPARTMENT_ID,90,salary)),0) as "dept 90"
from employees
GROUP BY job_id;

/*5)부서에 배치된 사원들만 찾는다. 성, 부서번호, 부서명을 출력한다.*/
select e.last_name,e.department_id,d.department_name
from employees e join departments d on e.DEPARTMENT_ID=d.DEPARTMENT_ID;

/*6)Kochhar에게 보고하는 사원들의 성, 월급을 출력한다.*/
select e.last_name, e.salary, j.job_id from employees e
join jobs j on j.job_id=e.job_id where j.job_id='AD_ASST';