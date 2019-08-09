-- 평가문항(7) 다음 작업에 해당하는 데이터 조회 명령문을 작성한다. 
-- 1)2003년도에 입사한 사원들의 성과 입사입을 출력한다.
select last_name,hire_date
from employees
where hire_date between '2003-1-11' and '2003-12-31';
-- 2)성에 a와 e가 포함된 사원들의 성을 출력한다.
select last_name from employees
where last_name LIKE '%a%'and last_name like '%e%';

-- 3)사원들의 성과 근속개월수를 출력한다. 근속개월수 칼럼명은 MONTHS_WORKED 이다.
--    잔여일자는 반올림 처리한다.
select last_name, round(MONTH-WORKED) from employees;

-- 4)직업별, 부서별 월급합계를 구한다. 20,50,80,90번 부서만을 조사한다.
select sum(salary) 
from employees 
where  department_id in(20,50,80,90)
group by job_id;

select sum(salary) 
from employees 
where  department_id in(20,50,80,90)
group by department_id;
-- 5)부서에 배치된 사원들만을 찾는다. 성, 부서번호, 부서명을 출력한다.
select last_name, department_id, department_name
from employees 
inner join departments using(department_id);
-- 6)Kochhar 에게 보고하는 사원들의 성, 월급을 출력한다.

