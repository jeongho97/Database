DELIMITER $$
create procedure Getcustom()
begin
select*from 고객;
end $$
DELIMITER ;
call Getcustom();

DELIMITER $$
create procedure custom1(in 아이디 varchar(20))
begin
select*from 고객 where 고객아이디=아이디;
end $$
DELIMITER ;
call custom1('banana');

DELIMITER $$
create procedure custom2(in grade varchar(20), in age int)
begin
select*from 고객 where 등급=grade and 나이>age;
end $$
DELIMITER ;
call custom2('silver',30);
describe 고객;

drop table if exists guguTBL;
create table guguTBL (txt varchar(100));

drop procedure if exists whileProc;
DELIMITER // -- java에서의 ;(세미클론)과 같은 역할이다
create procedure whileProc()
Begin
	declare str varchar(100);
    declare i int;
    declare k int;
    set i=2;
    
    while(i<10) do
		set str=''; -- 각 단의 결과를 저장할 문자열 초기화
        set k=1;
        while(k<10) do
			set str=concat(str,' ',i,'x',k,'=',i*k); -- 문자열 만들기
            set k=k+1;
		end while;
        set i=i+1;
        insert into guguTBL values(str);
	end while;
end //
DELIMITER ;
call whileProc();
select * from guguTBL;

USE web;
drop procedure if exists cursorProc; -- 커서란 프로시저 내부에서 복수 행을 처리할때 사용하는 구성 요소이다. 쿼리의 행 집합에서 한 행씩 옮겨가며 명령을 처리함
delimiter $$
create procedure cursorProc()
begin
	declare userMoney int; -- declare는 커서 선언
    declare cnt int default 0;
    declare totalMoney int default 0;
    declare endOfRow boolean default false;
    declare userCursor cursor for -- 커서 선언
		select 적립금 from 고객;
	declare continue handler -- declare handler는 반복 조건 선언
		for not found set endOfRow=true; -- 행의 끝일때
	open userCursor; -- 커서열기
    
    cursor_loop: LOOP
		fetch userCursor into userMoney; -- fetch는 커서에서 데이터 가져오기
        
        if endOfRow then
			leave cursor_loop;
		end if;
        set cnt=cnt+1;
        set totalMoney=totalMoney+userMoney;
        end loop cursor_loop;
        
        select concat('고객 적립금의 평균 ==>',(totalMoney/cnt));
        
        close userCursor; -- 커서닫기
	end $$
    delimiter ;고객고객
    call cursorProc();
    select 적립금 from 고객;