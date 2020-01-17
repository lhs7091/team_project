create user okawari identified by 1234;

grant connect, resource, dba to okawari;

-- 본사 및 지점 정보 테이블 
drop table okwr_user cascade constraint;
create table okwr_user(
   user_id varchar2(20) primary key,   -- 로그인 id,
   user_pass varchar2(20) not null,   -- 비밀번호
   user_auth number not null,         -- 권한 본사1, 지점관리자2, 지점유저3
   CONSTRAINT user_auth_ck CHECK(user_auth IN(1,2,3,4)),
   user_name varchar2(20),            -- 지점명
   user_manager varchar2(30),         -- 지점장
   user_addr varchar2(100),         -- 지점주소
   user_phone char(15),            -- 지점전화번호
   user_businum varchar2(20),         -- 지점 사업자등록번호
   user_commission number,            -- 지점 commission %
   user_opendate varchar2(20)               -- 지점 open일
);

select * from okwr_user;

-- 본사사장님..(꾸벅꾸벅)
insert into okwr_user values('headoffice','headoffice',1,'東京本社','カンヘリ',
'〒160-8001 東京都新宿区西新宿１丁目１ 新宿区西新宿１丁目１−３','+81 3-3342-1111','081-11-00001',0,'18/01/01');

-- 오사카지점
insert into okwr_user values('osaka', 'osaka', 2, '大阪支店', 'ユンミンヒ',
'〒542-0076 大阪府大阪市中央区難波５丁目１−60','+81 6-6644-2960','081-22-03021',3,'18/02/01');
insert into okwr_user values('osakac', 'osakac', 3, '大阪支店', 'ユンミンヒ',
'〒542-0076 大阪府大阪市中央区難波５丁目１−60','+81 6-6644-2960','081-22-03021',3,'18/02/01');

-- 쿄토지점
insert into okwr_user values('kyoto', 'kyoto', 2, '京都支店', 'オジヨンミン',
'〒616-8375 京都府京都市右京区嵯峨天龍寺立石町１−３８','+81 75-865-1020','043-33-00032',5,'18/03/02');
insert into okwr_user values('kyotoc', 'kyotoc', 3, '京都支店', 'オジヨンミン',
'〒616-8375 京都府京都市右京区嵯峨天龍寺立石町１−３８','+81 75-865-1020','043-33-00032',5,'18/03/02');

-- 후쿠오카지점
insert into okwr_user values('fukuoka', 'fukuoka', 2, '福岡支店', 'イヒス',
'〒812-0018 福岡県福岡市博多区住吉１丁目２ 福岡市博多区住吉１丁目２','+81 92-282-2525','053-44-03442',4,'18/04/02');
insert into okwr_user values('fukuokac', 'fukuokac', 3, '福岡支店', 'イヒス',
'〒812-0018 福岡県福岡市博多区住吉１丁目２ 福岡市博多区住吉１丁目２','+81 92-282-2525','053-44-03442',4,'18/04/02');

-- 요코하마지점
insert into okwr_user values('yokohama', 'yokohama', 2, '横浜支店', 'キム・ヒョンウ',
'〒220-0011 神奈川県横浜市西区高島２丁目１６ 〒220-0005 ２ 丁目 １６','+81 45-314-3490','054-55-01455',2,'18/05/02');
insert into okwr_user values('yokohamac', 'yokohamac', 3, '横浜支店', 'キム・ヒョンウ',
'〒220-0011 神奈川県横浜市西区高島２丁目１６ 〒220-0005 ２ 丁目 １６','+81 45-314-3490','054-55-01455',2,'18/05/02');

-- 삿포로지점
insert into okwr_user values('sapporo', 'sapporo', 2, '札幌支店', 'イムキムン',
'〒065-8633 北海道札幌市東区北７条東９丁目１−１','+81 11-748-1876','063-36-06032',3,'18/02/01');
insert into okwr_user values('sapporoc', 'sapporoc', 3, '札幌支店', 'イムキムン',
'〒065-8633 北海道札幌市東区北７条東９丁目１−１','+81 11-748-1876','063-36-06032',3,'18/02/01');

-- 뉴욕지점
insert into okwr_user values('newyork', 'newyork', 2, 'newyork branch', 'Kang Haeri',
'350 5th Ave, New York, NY 10118 USA','+1 212-736-3100','011-34-16032',3,'18/08/01');
insert into okwr_user values('newyorkc', 'newyorkc', 3, 'newyork branch', 'Kang Haeri',
'350 5th Ave, New York, NY 10118 USA','+1 212-736-3100','011-34-16032',3,'18/0i/01');

-- 메뉴 테이블 : 본사에서 지정
drop table okwr_menu cascade constraint;
create table okwr_menu(
   menu_num number primary key,      -- 메뉴번호
   menu_name varchar2(30),            -- 메뉴이름
   menu_price number,               -- 메뉴가격
   menu_image varchar2(30),			-- 메뉴이미지
   menu_check varchar2(2) default 'y',      -- 판매유무
   CONSTRAINT menu_check_ck CHECK(menu_check IN('y','n'))      -- y, n 만 입력가능
);

insert into okwr_menu values(1, 'ベーコンパスタ', 10000, 'bacon_pasta.jpg','y');
insert into okwr_menu values(2, 'チーズトンカツ', 12000, 'cheesecutlet.jpg','y');
insert into okwr_menu values(3, 'チキンチリドリア',11000, 'chikenchilidoria.jpg','y');
insert into okwr_menu values(4, 'チキンカツカレー', 7000, 'chikencutletcurry.jpg','y');
insert into okwr_menu values(5, 'チキンオムライス', 8500, 'chikenomulet.jpg','y');
insert into okwr_menu values(6, 'トンカツカレー', 6000, 'culet&curry.jpg','y');
insert into okwr_menu values(7, 'トンカツオムライス', 9000, 'cutlet&omulet.jpg','y');
insert into okwr_menu values(8, '牛カツ',20000, 'cyukattsu.jpg','y');
insert into okwr_menu values(9, '和風カツセット', 30000, 'japsall.jpg','y');
select * from okwr_menu;



-- 주문 테이블 (각 지점별로 필요하며, 고객들이 주문할 때 사용)
select * from okwr_order_customer
select * from okwr_stock_branch;

drop table okwr_order_customer cascade constraint;
-- 주문 09.06 브런치 아이디 포함, 원가 포함
create table okwr_order_customer(
   order_num number,   -- 주문번호
   order_branch_id varchar2(20),
   order_date varchar2(20),         -- 주문날짜
   order_time varchar2(20),         -- 주문시간
   order_menu_num number,   -- 메뉴번호
   order_menu_name varchar2(30),   -- 메뉴이름
   order_count number not null,   -- 주문수량
   order_perprice number,          -- 개당 가격
   order_oriprice number,          -- 개당 가격
   order_totprice number         -- 총 금액
);

-- 매출 테이블 (각 지점별로 필요, 지점별 매출 정리)
select * from okwr_sales_branch;
drop table okwr_sales_branch cascade constraints;
create table okwr_sales_branch(
   sales_branch_id varchar2(20) references okwr_user(user_id),   -- 지점 Id
    sales_date date,   -- 판매일
   sales_total number,         -- 총 판매 금액
   sales_cost number,         -- 총 판매 원가
   sales_commission number,   -- 본사에 지급할 commission 금액
   sales_origin number      -- 순이익 = total-cost-junk-commission
);


-- 매출 테이블 (본사용) 
drop table okwr_sales_head cascade constraints;
create table okwr_sales_head(
   sales_branch_id varchar2(20),	-- 판매지점
   sales_date date,	-- 판매일
   sales_total number,      -- 판매총액
   sales_cost number,      -- 판매원가
   sales_junk number,      -- 폐기한 물품의 원가
   sales_commission number,   -- 
   sales_origin number      -- 순이익
);

-- 주문테이블 : 지점과 본사간의 주문 발주 관계를 위한 테이블
drop table okwr_order_branch cascade constraint;
create table okwr_order_branch(
   order_num number primary key,      -- 주문번호
   order_branch_id varchar2(20) references okwr_user(user_id),      -- 주문지점 id
   order_date varchar2(20),      -- 주문날짜
   order_time varchar2(20),      -- 주문시간
   order_menu_num number references okwr_menu(menu_num),
   order_count number,      --주문갯수
   order_perprice number, -- 개당판매가격
   order_totprice number,
   order_complete_branch varchar2(2) default 'n',   -- 입고완료여부 default n
   CONSTRAINT order_branch_ck CHECK(order_complete_branch IN('y','n')),
   order_complete_branch_date varchar2(20),      -- 입고날짜
   order_complete_branch_time varchar2(20),       -- 입고시간
   order_complete_head varchar2(2) default 'n',   -- 출하완료여부 default n
   CONSTRAINT order_head_ck CHECK(order_complete_head IN('y','n')),
   order_complete_date varchar2(20),            -- 출고날짜
   order_complete_time varchar2(20),            -- 출고시간
   order_expiredate varchar2(20)                -- 유통기한
);


insert into okwr_order_branch(order_num, order_branch_id, order_date, 
order_time, order_menu_num, order_count, order_perprice, order_totprice) 



-- 재고테이블(각 지점별, 지점의 재고를 저장하기 위한 테이블) 
drop table okwr_stock_branch cascade constraints;
create table okwr_stock_branch(
   stock_branch_id varchar2(20) references okwr_user(user_id),
   stock_menu_num number references okwr_menu(menu_num), -- 메뉴번호 
   stock_count number, -- 수량
   stock_cost number, -- 원가 
   stock_price number,-- 판매가 
   stock_expiredate varchar2(20),    -- 유통기한
   stock_junk char(2) default 'n',      -- 유통기한 만료에 따른 폐기유무
   CONSTRAINT order_expiredate_ck CHECK(stock_junk IN('y','n'))
);

insert into okwr_stock_branch values('fukuoka', 1, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 2, 10, 10000, 12000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 3, 10, 9000, 11000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 4, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 5, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 6, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 7, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 8, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('fukuoka', 9, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('osaka', 4, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('osaka', 2, 10, 9000, 10000, '18/09/30','n');
insert into okwr_stock_branch values('kyoto', 8, 10, 9000, 10000, '18/09/30','n');


-- 본사에서 보냈을때 잠시 보관할 테이블
drop table okwr_stock_delivery cascade constraints;
create table okwr_stock_delivery(
   order_num number,
   stock_branch_id varchar2(20),
   stock_menu_num number, -- 메뉴번호 
   stock_count number, -- 수량
   stock_cost number, -- 원가 
   stock_price number,-- 판매가 
   stock_expiredate varchar2(20)    -- 유통기한
);
select * from okwr_stock_delivery;

-- 본사재고테이블 
drop table okwr_stock_head cascade constraints;
create table okwr_stock_head(
   stock_head_id varchar2(20) references okwr_user(user_id),
   stock_menu_num number references okwr_menu(menu_num),   -- 메뉴번호
   stock_count number,    -- 수량
   stock_cost number,   -- 원가 
   stock_price number,   -- 판매가 
   stock_expiredate varchar2(20), -- 유통기한
   stock_junk varchar(1) default 'n', -- 유통기한 만료에 따른 폐기유무
   CONSTRAINT stock_junk_ck CHECK(stock_junk IN('y','n'))
);
insert into okwr_stock_head values('headoffice',1,100,8000,9000,'18/09/21','n');
insert into okwr_stock_branch values('fukuoka',1,100,4000,5000,'18/09/21','n');
insert into okwr_stock_branch values('kyoto',2,10,10000,11000,'18/09/21','n');
insert into okwr_stock_branch values('osaka',2,10,10000,11000,'18/09/30','n');
select sum(stock_count) from okwr_stock_head where stock_menu_num=1;






