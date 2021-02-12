
-- ȸ�� ���̺�
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

-- ȸ�� insert ����
Insert into PICKER_MEMBER (M_ID,M_PASSWORD,M_EMAIL,M_NAME,M_PHONE,M_ZIPCODE,M_ROADADDR,M_DETAILADDR,M_DATE,M_TYPE,M_POINT,M_TERMS,M_PERSONAL,SESSION_KEY,SESSION_LIMIT) values ('admin','admin@1234','admin@n.com','������','010-5921-4973','06035','���� ������ ���μ��� 5','536',to_date('16/01/07','RR/MM/DD'),0,0,'Y','Y','none',null);
Insert into PICKER_MEMBER (M_ID,M_PASSWORD,M_EMAIL,M_NAME,M_PHONE,M_ZIPCODE,M_ROADADDR,M_DETAILADDR,M_DATE,M_TYPE,M_POINT,M_TERMS,M_PERSONAL,SESSION_KEY,SESSION_LIMIT) values ('asdf1','asdf@1234','asdf1@n.com','������','010-1234-5678','06035','���� ������ ���μ��� 5','537',to_date('21/01/03','RR/MM/DD'),1,1000,'Y','Y','none',null);

-- ����Ʈ ���̺�
CREATE TABLE PICKER_POINT(
P_NUM NUMBER PRIMARY KEY,
M_ID VARCHAR2(16),
P_DATE DATE,
P_HISTORY NVARCHAR2(100),
P_POINT NUMBER,
B_CODE NUMBER
);

-- ����Ʈ insert ����
Insert into PICKER.PICKER_POINT (P_NUM,M_ID,P_DATE,P_HISTORY,P_POINT,B_CODE) values (1,'admin',to_date('16/01/07','RR/MM/DD'),'������',0,null);
Insert into PICKER.PICKER_POINT (P_NUM,M_ID,P_DATE,P_HISTORY,P_POINT,B_CODE) values (2,'asdf1',to_date('21/01/03','RR/MM/DD'),'�ű�ȸ�� ����������',1000,null);

-- ��ǰ ���̺�
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

-- ��ǰ insert ����
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00001','����� �¸��� ����',8000,to_date('20/12/01','RR/MM/DD'),'soap_jeju.jpg','soap_jeju_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00002','�ö���� �ö���Ʈ �ٵ��',17000,to_date('20/12/01','RR/MM/DD'),'body_brush.jpg','body_brush_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00003','��â 3�� �ڵ�Ÿ��',8000,to_date('20/12/01','RR/MM/DD'),'3ply_hand_towel.jpg','3ply_hand_towel_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00004','������ õ�� ġ��/����',7000,to_date('20/12/01','RR/MM/DD'),'dental_floss.jpg','dental_floss_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00005','õ�� ������ ����尩',10000,to_date('20/12/01','RR/MM/DD'),'screbbers_bath_gloves.jpg','screbbers_bath_gloves_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00006','�� ƾ���̽�',4500,to_date('20/12/01','RR/MM/DD'),'soap_case.jpg','soap_case_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00007','��â 2�� ���� ����',7000,to_date('20/12/01','RR/MM/DD'),'2ply_face_towel.jpg','2ply_face_towel_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00008','õ�� �� �ָӴ�',5000,to_date('20/12/01','RR/MM/DD'),'soap_pocket.jpg','soap_pocket_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00009','�����θ��� ��Ŭ����',7000,to_date('20/12/01','RR/MM/DD'),'tongue_cleaner.jpg','tongue_cleaner_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00010','õ�� ��� ������',149000,to_date('20/12/01','RR/MM/DD'),'fresh_scrubbers.jpg','fresh_scrubbers_detail.jpg','bathroom',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00011','���� ���� �ռ���',4000,to_date('20/12/01','RR/MM/DD'),'linnen_handkerchief.jpg','linnen_handkerchief_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00012','�Ҷ� ����� ƫ����(���ö�����)',6000,to_date('20/12/01','RR/MM/DD'),'Lunch_bag.jpg','Lunch_bag_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00013','���� ȭ����(10����)',25000,to_date('20/12/01','RR/MM/DD'),'recycle_paper.jpg','recycle_paper_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00014','SCHOTT ��Ʈ ��������',5000,to_date('20/12/01','RR/MM/DD'),'shortglass_straw.jpg','shortglass_straw_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00015','���� �̴�����(10pcs.)',4000,to_date('20/12/01','RR/MM/DD'),'Stainless_mini_tongs.jpg','Stainless_mini_tongs_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00016','���� �� ����(5p.)',4000,to_date('20/12/01','RR/MM/DD'),'Stainless_tongs.jpg','Stainless_tongs_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00017','�׷� ����� �ж����',1500,to_date('20/12/01','RR/MM/DD'),'wax_block.jpg','wax_block_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00018','�ȷλ��� ��彺ƽ',10000,to_date('20/12/01','RR/MM/DD'),'wood_stic.jpg','wood_stic_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00019','�ϴ��� �䰡��Ʈ',149000,to_date('20/12/01','RR/MM/DD'),'yoga_mat.jpg','yoga_mat_detail.jpg','living',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00020','��â 2�� ����(�ռ��� ���)',4000,to_date('20/12/01','RR/MM/DD'),'2ply_cloth_towel.jpg','2ply_cloth_towel_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00021','����� ���� Ŀ������',4000,to_date('20/12/01','RR/MM/DD'),'coffee_filter.jpg','coffee_filter_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00022','�������� ���ڵ� �귯��',6500,to_date('20/12/01','RR/MM/DD'),'longhandle_brush.jpg','longhandle_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00023','�������� �� �귯��',6000,to_date('20/12/01','RR/MM/DD'),'pot_brush.jpg','pot_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00024','õ�� ������ ������',4000,to_date('20/12/01','RR/MM/DD'),'refresh_scrubbers.jpg','refresh_scrubbers_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00025','����� ������ ��',8000,to_date('20/12/01','RR/MM/DD'),'shower_soap.jpg','shower_soap_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00026','�����θ��� ����(������)',3000,to_date('20/12/01','RR/MM/DD'),'stainless_straw.jpg','stainless_straw_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00027','������ ���� ��ô��',2000,to_date('20/12/01','RR/MM/DD'),'straw_cleaning_brush.jpg','straw_cleaning_brush_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00028','��ȸ�� �ж� �ֹ���',4500,to_date('20/12/01','RR/MM/DD'),'wax_kitchen_lab.jpg','wax_kitchen_lab_detail.jpg','kitchen',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00029','���ڹ�',8000,to_date('20/12/01','RR/MM/DD'),'eco_bag.jpg','eco_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00030','����� �����޽� ���ڹ�',12000,to_date('20/12/01','RR/MM/DD'),'ecobag.jpg','ecobag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00031','��Ȱ�� ��Ʈ�� ���Ϲ�',13000,to_date('20/12/01','RR/MM/DD'),'market_bag.jpg','market_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00032','����� �޽� ���εེ��',4500,to_date('20/12/01','RR/MM/DD'),'mash_producebag.jpg','mash_producebag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00033','���εེ ��',6000,to_date('20/12/01','RR/MM/DD'),'produce_bag.jpg','produce_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00034','������ ���εེ��',4500,to_date('20/12/01','RR/MM/DD'),'producebag.jpg','producebag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00035','��â ���εེ��',5500,to_date('20/12/01','RR/MM/DD'),'sochang_produce_bag.jpg','sochang_produce_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00036','����� ��Ʈ�� ���ڹ�',10000,to_date('20/12/01','RR/MM/DD'),'string_eco_bag.jpg','string_eco_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00037','��Ȱ��� �Һ� ����',13000,to_date('20/12/01','RR/MM/DD'),'tumbler_bag.jpg','tumbler_bag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00038','��ȸ�� �ж� Ǫ���',5000,to_date('20/12/01','RR/MM/DD'),'wax_Foodbag.jpg','wax_Foodbag_detail.jpg','market',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00039','�볪�� ���ʱ���',2000,to_date('20/12/01','RR/MM/DD'),'bamboo_pencilsharpener.jpg','bamboo_pencilsharpener_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00040','A5 ũ����Ʈ ������Ʈ',2000,to_date('20/12/01','RR/MM/DD'),'craft_muginote.jpg','craft_muginote_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00041','�Ź�������(5ea)',2000,to_date('20/12/01','RR/MM/DD'),'newspaper_pencil.jpg','newspaper_pencil_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00042','������� ����',1500,to_date('20/12/01','RR/MM/DD'),'pen.jpg','pen_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00043','ũ����Ʈ ���� ���� ��Ŭ��',2000,to_date('20/12/01','RR/MM/DD'),'weekly_note.jpg','weekly_note_detail.jpg','office',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00044','ģȯ�� �볪�� ĩ��',5500,to_date('20/12/01','RR/MM/DD'),'bamboo_toothbrush.jpg','bamboo_toothbrush_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00045','����� �ܽ���ư ȭ���',12000,to_date('20/12/01','RR/MM/DD'),'makeup_brush.jpg','makeup_brush_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00046','����� �޽� �Ŀ�ġ',3000,to_date('20/12/01','RR/MM/DD'),'mash_pouch.jpg','mash_pouch_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00047','�޴�� ��â���Խ�(4����)',11000,to_date('20/12/01','RR/MM/DD'),'sochang_napkin.jpg','sochang_napkin_detail.jpg','travel',0);
Insert into PICKER_ITEM (I_CODE,I_NAME,I_PRICE,I_DATE,I_IMG,I_DETAILIMG,I_CATEGORY,I_CHK) values ('P00048','���� ���� �ָӴ�',3000,to_date('20/12/01','RR/MM/DD'),'straw_pouch.jpg','straw_pouch_detail.jpg','travel',0);


-- ��ٱ��� ���̺�
CREATE TABLE PICKER_CART(
C_NUM NUMBER PRIMARY KEY,
I_IMG NVARCHAR2(100),
I_CODE VARCHAR2(7),
I_NAME NVARCHAR2(100),
C_CNT NUMBER(2),
I_PRICE NUMBER,
M_ID VARCHAR2(16)
);

-- ���� ���̺�
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

-- ���Ż� ���̺�
CREATE TABLE PICKER_BUYITEM(
BI_NUM NUMBER PRIMARY KEY,
B_CODE NUMBER,
I_IMG NVARCHAR2(100),
I_CODE VARCHAR2(7),
I_NAME NVARCHAR2(100),
BI_CNT NUMBER(2),
I_PRICE NUMBER
);

-- �������� ���̺�
CREATE TABLE PICKER_NOTICE(
N_NUM NUMBER PRIMARY KEY,
N_TITLE NVARCHAR2(100),
N_CONTENT CLOB,
N_DATE DATE,
M_ID VARCHAR2(16),
N_CNT NUMBER,
N_CHK NUMBER(1)
); 

-- Q&A(1:1) ���̺�
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

-- ��ǰ���� ���̺�
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

-- ��� ���̺�
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
