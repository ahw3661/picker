
-- 회원 테이블
CREATE TABLE PICKER_MEMBER(
M_ID VARCHAR2(16) PRIMARY KEY,
M_PASSWORD VARCHAR2(16),
M_EMAIL VARCHAR2(50),
M_NAME NVARCHAR2(50),
M_PHONE VARCHAR2(15),
M_ZIPCODE CHAR(5),
M_ROADADDR NVARCHAR2(100),
M_DETAILADDR NVARCHAR2(100),
M_DATE DATE,
M_TYPE NUMBER(1),
M_POINT NUMBER,
M_TERMS CHAR(1),
M_PERSONAL CHAR(1),
SESSION_KEY VARCHAR2(16) DEFAULT 'NONE',
SESSION_LIMIT TIMESTAMP
);

-- 회원 insert 정보
Insert into PICKER_MEMBER (M_ID,M_PASSWORD,M_EMAIL,M_NAME,M_PHONE,M_ZIPCODE,M_ROADADDR,M_DETAILADDR,M_DATE,M_TYPE,M_POINT,M_TERMS,M_PERSONAL,SESSION_KEY,SESSION_LIMIT) values ('admin','admin@1234','admin@n.com','관리자','010-5921-4973','06035','서울 강남구 가로수길 5','536',to_date('16/01/07','RR/MM/DD'),0,0,'Y','Y','none',null);
Insert into PICKER_MEMBER (M_ID,M_PASSWORD,M_EMAIL,M_NAME,M_PHONE,M_ZIPCODE,M_ROADADDR,M_DETAILADDR,M_DATE,M_TYPE,M_POINT,M_TERMS,M_PERSONAL,SESSION_KEY,SESSION_LIMIT) values ('asdf1','asdf@1234','asdf1@n.com','박진주','010-1234-5678','06035','서울 강남구 가로수길 5','537',to_date('21/01/03','RR/MM/DD'),1,1000,'Y','Y','none',null);

-- 포인트 테이블
CREATE TABLE PICKER_POINT(
P_NUM NUMBER PRIMARY KEY,
M_ID VARCHAR2(16),
P_DATE DATE,
P_HISTORY NVARCHAR2(100),
P_POINT NUMBER,
B_CODE NUMBER
);

-- 포인트 insert 정보
Insert into PICKER.PICKER_POINT (P_NUM,M_ID,P_DATE,P_HISTORY,P_POINT,B_CODE) values (1,'admin',to_date('16/01/07','RR/MM/DD'),'관리자',0,null);
Insert into PICKER.PICKER_POINT (P_NUM,M_ID,P_DATE,P_HISTORY,P_POINT,B_CODE) values (2,'asdf1',to_date('21/01/03','RR/MM/DD'),'신규회원 쇼핑지원금',1000,null);

-- 상품 테이블
CREATE TABLE PICKER_ITEM(
I_CODE VARCHAR2(7) PRIMARY KEY,
I_NAME NVARCHAR2(100),
I_PRICE NUMBER,
I_DATE DATE,
I_IMG NVARCHAR2(100),
I_DETAILIMG NVARCHAR2(100),
I_CATEGORY VARCHAR2(10),
I_CHK NUMBER(1)
);

-- 상품 insert 정보
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00001','유기농 온몸비누 제주',8000,to_date('20/12/01','RR/MM/DD'),'soap_jeju.jpg','soap_jeju_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00002','플랑드비 올라이트 바디솝',17000,to_date('20/12/01','RR/MM/DD'),'body_brush.jpg','body_brush_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00003','소창 3겹 핸드타월',8000,to_date('20/12/01','RR/MM/DD'),'3ply_hand_towel.jpg','3ply_hand_towel_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00004','생분해 천연 치실/리필',7000,to_date('20/12/01','RR/MM/DD'),'dental_floss.jpg','dental_floss_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00005','천연 수세미 목욕장갑',10000,to_date('20/12/01','RR/MM/DD'),'screbbers_bath_gloves.jpg','screbbers_bath_gloves_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00006','비누 틴케이스',4500,to_date('20/12/01','RR/MM/DD'),'soap_case.jpg','soap_case_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00007','소창 2겹 세안 수건',7000,to_date('20/12/01','RR/MM/DD'),'2ply_face_towel.jpg','2ply_face_towel_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00008','천연 비누 주머니',5000,to_date('20/12/01','RR/MM/DD'),'soap_pocket.jpg','soap_pocket_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00009','스테인리스 혀클리너',7000,to_date('20/12/01','RR/MM/DD'),'tongue_cleaner.jpg','tongue_cleaner_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00010','천연 목욕 수세미',149000,to_date('20/12/01','RR/MM/DD'),'fresh_scrubbers.jpg','fresh_scrubbers_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00011','내츄럴 린넨 손수건',4000,to_date('20/12/01','RR/MM/DD'),'linnen_handkerchief.jpg','linnen_handkerchief_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00012','소락 유기농 튤립백(도시락가방)',6000,to_date('20/12/01','RR/MM/DD'),'Lunch_bag.jpg','Lunch_bag_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00013','재사용 화장지(10매입)',25000,to_date('20/12/01','RR/MM/DD'),'recycle_paper.jpg','recycle_paper_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00014','SCHOTT 쇼트 유리빨대',5000,to_date('20/12/01','RR/MM/DD'),'shortglass_straw.jpg','shortglass_straw_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00015','스텐 미니집게(10pcs.)',4000,to_date('20/12/01','RR/MM/DD'),'Stainless_mini_tongs.jpg','Stainless_mini_tongs_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00016','스텐 고리 집게(5p.)',4000,to_date('20/12/01','RR/MM/DD'),'Stainless_tongs.jpg','Stainless_tongs_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00017','그랩 리페어 밀랍블록',1500,to_date('20/12/01','RR/MM/DD'),'wax_block.jpg','wax_block_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00018','팔로산토 우드스틱',10000,to_date('20/12/01','RR/MM/DD'),'wood_stic.jpg','wood_stic_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00019','하누만 요가매트',149000,to_date('20/12/01','RR/MM/DD'),'yoga_mat.jpg','yoga_mat_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00020','소창 2겹 행주(손수건 대용)',4000,to_date('20/12/01','RR/MM/DD'),'2ply_cloth_towel.jpg','2ply_cloth_towel_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00021','유기농 재사용 커피필터',4000,to_date('20/12/01','RR/MM/DD'),'coffee_filter.jpg','coffee_filter_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00022','설거지용 롱핸들 브러쉬',6500,to_date('20/12/01','RR/MM/DD'),'longhandle_brush.jpg','longhandle_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00023','설거지용 팟 브러쉬',6000,to_date('20/12/01','RR/MM/DD'),'pot_brush.jpg','pot_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00024','천연 설거지 수세미',4000,to_date('20/12/01','RR/MM/DD'),'refresh_scrubbers.jpg','refresh_scrubbers_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00025','유기농 설거지 비누',8000,to_date('20/12/01','RR/MM/DD'),'shower_soap.jpg','shower_soap_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00026','스테인리스 빨대(일자형)',3000,to_date('20/12/01','RR/MM/DD'),'stainless_straw.jpg','stainless_straw_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00027','사이잘 빨대 세척솔',2000,to_date('20/12/01','RR/MM/DD'),'straw_cleaning_brush.jpg','straw_cleaning_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00028','다회용 밀랍 주방탭',4500,to_date('20/12/01','RR/MM/DD'),'wax_kitchen_lab.jpg','wax_kitchen_lab_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00029','에코백',8000,to_date('20/12/01','RR/MM/DD'),'eco_bag.jpg','eco_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00030','유기농 하프메쉬 에코백',12000,to_date('20/12/01','RR/MM/DD'),'ecobag.jpg','ecobag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00031','재활용 페트병 마켓백',13000,to_date('20/12/01','RR/MM/DD'),'market_bag.jpg','market_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00032','유기농 메쉬 프로듀스백',4500,to_date('20/12/01','RR/MM/DD'),'mash_producebag.jpg','mash_producebag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00033','프로듀스 백',6000,to_date('20/12/01','RR/MM/DD'),'produce_bag.jpg','produce_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00034','유기농면 프로듀스백',4500,to_date('20/12/01','RR/MM/DD'),'producebag.jpg','producebag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00035','소창 프로듀스백',5500,to_date('20/12/01','RR/MM/DD'),'sochang_produce_bag.jpg','sochang_produce_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00036','유기농 스트링 에코백',10000,to_date('20/12/01','RR/MM/DD'),'string_eco_bag.jpg','string_eco_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00037','재활용면 텀블러 가방',13000,to_date('20/12/01','RR/MM/DD'),'tumbler_bag.jpg','tumbler_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00038','다회용 밀랍 푸드백',5000,to_date('20/12/01','RR/MM/DD'),'wax_Foodbag.jpg','wax_Foodbag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00039','대나무 연필깎이',2000,to_date('20/12/01','RR/MM/DD'),'bamboo_pencilsharpener.jpg','bamboo_pencilsharpener_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00040','A5 크라프트 무지노트',2000,to_date('20/12/01','RR/MM/DD'),'craft_muginote.jpg','craft_muginote_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00041','신문지연필(5ea)',2000,to_date('20/12/01','RR/MM/DD'),'newspaper_pencil.jpg','newspaper_pencil_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00042','재생종이 볼펜',1500,to_date('20/12/01','RR/MM/DD'),'pen.jpg','pen_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00043','크라프트 무지 만년 위클리',2000,to_date('20/12/01','RR/MM/DD'),'weekly_note.jpg','weekly_note_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00044','친환경 대나무 칫솔',5500,to_date('20/12/01','RR/MM/DD'),'bamboo_toothbrush.jpg','bamboo_toothbrush_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00045','유기농 햄스코튼 화장솜',12000,to_date('20/12/01','RR/MM/DD'),'makeup_brush.jpg','makeup_brush_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00046','유기농 메쉬 파우치',3000,to_date('20/12/01','RR/MM/DD'),'mash_pouch.jpg','mash_pouch_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00047','휴대용 소창와입스(4매입)',11000,to_date('20/12/01','RR/MM/DD'),'sochang_napkin.jpg','sochang_napkin_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00048','광목 빨대 주머니',3000,to_date('20/12/01','RR/MM/DD'),'straw_pouch.jpg','straw_pouch_detail.jpg','travel',0);


-- 장바구니 테이블
CREATE TABLE PICKER_CART(
C_NUM NUMBER PRIMARY KEY,
I_IMG NVARCHAR2(100),
I_CODE VARCHAR2(7),
I_NAME NVARCHAR2(100),
C_CNT NUMBER(2),
I_PRICE NUMBER,
M_ID VARCHAR2(16)
);

-- 구매 테이블
CREATE TABLE PICKER_BUY(
B_CODE NUMBER PRIMARY KEY,
B_ORDER_NAME NVARCHAR2(50),
B_ORDER_PHONE VARCHAR2(15),
B_ORDER_EMAIL VARCHAR2(50),
B_TAKE_NAME NVARCHAR2(50),
B_TAKE_PHONE VARCHAR2(15),
B_TAKE_ZIPCODE CHAR(5),
B_TAKE_ROADADDR NVARCHAR2(100),
B_TAKE_DETAILADDR NVARCHAR2(100),
M_ID VARCHAR2(16),
B_PRICE NUMBER,
B_DATE DATE,
B_AGREE CHAR(1),
B_CHK NUMBER(1),
U_DATE DATE
);

-- 구매상세 테이블
CREATE TABLE PICKER_BUYITEM(
BI_NUM NUMBER PRIMARY KEY,
B_CODE NUMBER,
I_IMG NVARCHAR2(100),
I_CODE VARCHAR2(7),
I_NAME NVARCHAR2(100),
BI_CNT NUMBER(2),
I_PRICE NUMBER
);

-- 공지사항 테이블
CREATE TABLE PICKER_NOTICE(
N_NUM NUMBER PRIMARY KEY,
N_TITLE NVARCHAR2(100),
N_CONTENT CLOB,
N_DATE DATE,
M_ID VARCHAR2(16),
N_CNT NUMBER,
N_CHK NUMBER(1)
); 

-- Q&A(1:1) 테이블
CREATE TABLE PICKER_QNA(
Q_NUM NUMBER PRIMARY KEY,
Q_TITLE NVARCHAR2(100),
Q_CONTENT CLOB,
Q_DATE DATE,
M_ID VARCHAR2(16),
M_NAME NVARCHAR2(50),
Q_RCHK NUMBER(1),
I_IMG NVARCHAR2(100),
I_CODE VARCHAR2(7),
I_NAME NVARCHAR2(100),
Q_CHK NUMBER(1)
);

-- 상품리뷰 테이블
CREATE TABLE PICKER_EVAL(
E_NUM NUMBER PRIMARY KEY,
E_CONTENT NVARCHAR2(1000),
M_ID VARCHAR2(16),
M_NAME NVARCHAR2(50),
E_DATE DATE,
B_CODE NUMBER,
I_CODE VARCHAR2(7),
E_LEVEL NUMBER(1),
E_CHK NUMBER(1)
);

-- 댓글 테이블
CREATE TABLE PICKER_REPLY(
R_NUM NUMBER PRIMARY KEY,
Q_NUM NUMBER,
R_CONTENT NVARCHAR2(1000),
M_ID VARCHAR2(16),
M_NAME NVARCHAR2(50),
R_DATE DATE,
R_DEP NUMBER,
R_SEQ NUMBER,
R_CHK NUMBER(1)
);
