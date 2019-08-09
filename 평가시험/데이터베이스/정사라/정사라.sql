-- 1.2003 년도에 입사한 사원들의 성과 입사일 출력
select last_name,hire_date
from EMPLOYEES
where HIRE_DATE between '2003/01/01' and '2003/12/31';

-- 2.성에 a와 e가 포함된 사원들의 성 출력.
select last_name
from employees
where lower(last_name) like '%a%' and lower(last_name) like '%e%';

-- 3.사원들의 성과 근속개월수를 출력한다. 근속개월수 컬럼은 MONTHS_WORKED이다.

-- 4.직업별,부서별 월급합계를 구한다. 20,50,80,90번 부서만을 조사한다.
select sum(salary)
from employees
where department_id in (20,50,80,90)
group by job_id;

-- 5.부서에 배치된 사원만을 찾는다. 성,부서번호,부서명을 출력한다.
select last_name, department_id, department_name
from employees inner join departments using (department_id)
where department_id is not null;

-- 6. Kochhar에게 보고하는 사원들의 성,월급을 출력한다.
