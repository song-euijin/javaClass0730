		SELECT  BNO, BWRITER ,BTITLE, BCONTENT, SUBSTR(BDATE,4,10)BDATE, BHIT,BFILENAME from boarddto ORDER BY BDATE DESC;
		SELECT * FROM CONTENTDTO;

DROP TABLE COMMENTDTO;
DROP TABLE MEMBERDTO;
DROP TABLE MARKDTO;
DROP TABLE REVIEWDTO;
DROP TABLE BOARDDTO;
DROP TABLE CONTENTDTO;

CREATE TABLE MEMBERDTO(
    MID      NVARCHAR2(20) PRIMARY KEY,
    MPW      NVARCHAR2(60) NOT NULL,
    MNAME    NVARCHAR2(5) NOT NULL,
    MEMAIL   NVARCHAR2(50) NOT NULL, 
    MPHONE   NVARCHAR2(11) NOT NULL
);

CREATE TABLE CONTENTDTO(
    CNUM        NUMBER PRIMARY KEY,
    CPOSTER     NVARCHAR2(50) NOT NULL,
    CNAME       NVARCHAR2(20) NOT NULL,
    CSUMMERY    NVARCHAR2(500) NOT NULL, 
    CDATE       NVARCHAR2(15) NOT NULL,
    CHIT        NUMBER NOT NULL
);

CREATE TABLE BOARDDTO (
bNO NUMBER PRIMARY KEY,
bWriter NVARCHAR2(20),
bTitle NVARCHAR2(50),
bContent NVARCHAR2(200),
bDate DATE,
bHit NVARCHAR2(50),
bFileName NVARCHAR2(50)
);

CREATE TABLE MarkDTO (
    KID NVARCHAR2(20),
    KNUM NUMBER (5),
    KLIKE NUMBER,
    CONSTRAINT KID_FK FOREIGN KEY (KID) REFERENCES MemberDTO(MID),
    CONSTRAINT KNUM_FK FOREIGN KEY (KNUM) REFERENCES ContentDTO(CNUM)
);


SELECT * FROM MarkDTO;
SELECT COUNT(*) FROM MarkDTO WHERE KNUM = 1080 AND KLIKE = 1;
INSERT INTO CONTENTDTO
		VALUES(CNUM_SEQ.NEXTVAL,'trend-1.jpg','���','�ƾ�',SYSDATE,0);
DROP VIEW BLIST;
CREATE VIEW BLIST AS SELECT BOARDDTO.*, ROW_NUMBER() OVER(ORDER BY BNO ASC) AS RN FROM BOARDDTO;
DROP SEQUENCE BOARDDTO_SEQ;
CREATE SEQUENCE BOARDDTO_SEQ;
DROP SEQUENCE CNUM_SEQ;
CREATE SEQUENCE CNUM_SEQ START WITH 1 INCREMENT BY 1;
DROP VIEW CNLIST;
create view CNLIST as select ContentDTO.*, ROW_NUMBER() over(ORDER BY CNAME) as RN
FROM ContentDTO;
DROP VIEW CPLIST;
create view CPLIST as select ContentDTO.*, ROW_NUMBER() over(ORDER BY CHIT DESC) as RN
FROM ContentDTO;
DROP VIEW CRLIST;
create view CRLIST as select ContentDTO.*, ROW_NUMBER() over(ORDER BY CDATE DESC) as RN
FROM ContentDTO;
DROP VIEW MLIST;
create view MLIST as select MemberDTO.*, ROW_NUMBER() over(ORDER BY MNAME) as RN
FROM MemberDTO;

Commit;

INSERT INTO MarkDTO VALUES('admin',1080,1);
SELECT COUNT(*) FROM MarkDTO WHERE KNUM = 1080 AND KLIKE = 1;

CREATE TABLE ReviewDTO(
    RID NVARCHAR2(20),
    RCNUM NUMBER(5),
    RCOMMENT NVARCHAR2(150),
    RDATE  DATE,
    CONSTRAINT RCNUM_FK FOREIGN KEY (RCNUM) REFERENCES ContentDTO(CNUM),
    CONSTRAINT RID_FK FOREIGN KEY (RID) REFERENCES MEMBERDTO(MID)
);

DROP VIEW  RVLIST;
create view RVLIST as select ReviewDTO.*, ROW_NUMBER() over(ORDER BY RDATE DESC) as RVRN
FROM ReviewDTO;
SELECT * FROM RVLIST WHERE RVRN BETWEEN 1 AND 5 AND RCNUM=5;

DROP TABLE ReviewDTO;

INSERT INTO ReviewDTO VALUES('admin',1080,'#{RCOMMENT}','2021-10-31');
SELECT * FROM ReviewDTO;
SELECT * FROM ContentDTO;   --1080
SELECT * FROM MEMBERDTO;    --

SELECT * FROM RVLIST WHERE RVRN BETWEEN 1 AND 5 AND RCNUM=1080;



INSERT INTO CONTENTDTO
		VALUES(CNUM_SEQ.NEXTVAL,'trend-1.jpg','��','�ƾ�','00/01/01',0);
    commit;


---------------------------------------------------------------------------------------------


INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'Venom.JPG','����','������ ���ؼ���� ���� �縮�� �ʴ� ���Ƿο� ���� ���� ���� ��� �Ŵ� ��� ������ �Ŀ�̼��� �ڸ� �Ѵ� �״� �̵��� �繫�ǿ� �����ߴٰ� ����ǿ��� �ܰ� ����ü �ɺ��Ʈ�� ��� ������ �ް� �ȴ�.�ɺ��Ʈ�� �����ϰ� �� ���� ����� ��ħ�� ���� ������ �������� �ŵ쳪��, ���� ���縸�� ����Ϸ��� ���� ����� ������ �޸� ������ ������ ���� ��ü���� ���ϴµ���!','18/10/15',240);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'moga.JPG','�𰡵�','���ѹα��� UN������ ���� ���м����ϴ� �ñ�1991�� �Ҹ������� ���� �𰡵𽴿����� ��������� ������ �Ͼ��.��Ÿ��� ���� �� ���� ���� ���ѹα� ������ ������ ���������Ѿ˰� ��ź�� ����ġ�� ���, ��Ƴ��� ���� �Ϸ��Ϸ縦 ���߳���.�׷��� ��� �� ��, ���� ������ ������� ������ ��û�ϸ� ���� �ε帮�µ�����ǥ�� �ϳ�, �𰡵𽴿��� Ż���ؾ� �Ѵ�!','21/07/28',230);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'woman.JPG','�������','���ð�� ����ġ�� �״´ٴ� ���� �ִ�. �̴� �ڱ� �ڽ��� ������ ��ݿ� ���帶�� ���� �����̶�� �Ѵ�. �׷��ٸ� ��Ż�� �� �� ���ð�� �����ϸ�? ','21/09/17',220);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'free.JPG','��������','����� ����, ��ģ �׸��� �� ���� Ŀ��.��ȭ�ο� �ϻ� �� ���� �Ѱ����� �������� ��Ÿ���¹����̾�Ƽ�� ������Ƽ�� ��� �ִ� ����.�׿��� �� �ϳ� ������ ���� ������.�쿬�� ����ģ �׳࿡�� �Ѵ��� ���ϱ� �������¡����� ��� ���� �ٽ� ���� �׳�� ���̰� ���� ���� ������Ƽ�� ��� ��� ĳ�����̰�,�� ������ �� �ı��� �Ŷ� ����Ѵ�.ȥ���� ���� ���� �׷��� �״� ������Ƽ�� �ı��� ���� ���ش� �̻� ��� ĳ���Ͱ� �ƴ�, ����ΰ� �Ǳ�� ����Ѵ�.�ÿ���, �ÿ��ϰ� ������ ����ʿ� �������̴� �׼� ��Ϲ�����8��, �λ��� ���� �ٲ� ¥���� �ݶ��� ���۵ȴ�!','21/08/11',210);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'squid.JPG','��¡�����','���� �ѱ�� ���� ���� ������� �����̹� ���ӿ� �پ���. �ž��� ������� ���ο� ���� �����ϱ� ����. ������ ��� ���ڰ� �� �� ���� ��. Ż���ϴ� �̵��� ġ������ ����� �����ؾ� �Ѵ�.','21/09/17',200);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'time.JPG','Ÿ�� �з�����','���Ĺ�, ���, �ǹ��� ����, �׸��� ��, ���� �츮�� �� ���� ���� �¾�� ������ ����ȭ��Ų ���� ������� ��Ը� ����ڰ� �߻��Ѵ�.���� ���� ���Ĺ��� ��� ���� ���� ���� ���δ� �ð������� �� �� �ִ� ������ ����� �����Ѵ�. �ܼ�1. ������ ����� ���� ���Ĺ��� ���ٰ� ���� ���� �̽ļ����� �Ѵ�. �ܼ�2. ������ ����� ���ٴ��� ���� ����� ���� ������. �ܼ�3. ����, ��ƿ����� �ڶ� ���ֺ���縦 �޲ٴٰ� �ǹ��� ���ڸ� ���� ���̸� ���� �λ��� ��ģ �ҳ� ���ο� ���� �̾߱⸦ �Ѵ�.','14/08/28',190);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'007.JPG','007 ������','�־��� �� �����Ϳ� ������ ���ӽ� ������ ���ű׸��� �� �ӿ� ������ �Ŵ��� ���! �߽��ڿ��� �Ͼ ���� �׷� ���� MI6�� ���� ���ο� ���� ��ü ���⿡ ���δ�.�ڽ��� ���ſ� ������ ��ȣ�� �����ϴ� ���ӽ� ����� ��� �־��� ���� �������͡����ڽ��� �����Ǿ� �ִٴ� ����� �˰� �ǰ�, ������ ���� MI6���� �׸� �����ϸ鼭��ü������ ���⿡ �����ϴµ�����','15/10/26',180);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'cowboy.JPG','ī�캸�� ���','ī�캸�� ����� �Ϻ��� �ִϸ��̼� ���ۻ� ������� ������ ��ǰ���� TV����迭���� 1998�� 4������ ���� �� 6������ ��26ȭ �� �Ϻΰ� ��۵ǰ� �� �� WOWOW���� ���� �� 10������ 1999�� 4���� ���� ��26ȭ�� ��۵Ǿ���.','01/10/23',170);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'yeonmo.JPG','����','�ֵ��̷� �¾ ���ƶ�� ���������� �������� ���̰� ����� ������ �������� ������ ���� ���ڰ� �Ǹ鼭 �������� ��н����� ���� �θǽ� ���','21/10/11',160);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'1994.JPG','�����϶� 1994','�����϶� 1994�� 2013�� 10�� 18�Ϻ��� 2013�� 12�� 28�ϱ��� tvN���� �濵�� ���� ��󸶷� 2012�⿡ �濵�� �������϶� 1997���� �ļ����̴�. ���̵��� ������ ���� ���翴�� ���۰��� �޸� �̹����� �߱���� ������ ���� ���簡 ���Դ�.','13/10/18',150);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'oats.JPG','������Ʃ���','������ ã�ƿ� ����. �ΰ��� �ΰ��ٿ� �� ���� �� �װ��� � ����ϱ�. �� �����ϰ� ó���� ������ �������� ���� ��ȭ�� ���� ��������. ��ȭ���� �� ���ķ���� ������ �ø���.','17/10/01',140);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'you.JPG','�ʸ� ���� ���','�Ƴ��� ������� ���ľ ������ �ڽ��� ����� ����ߴ� ���ڿ�, �� ���ڿ��� ª�� �������� �� �λ��� ������ �Ǿ���� �� �ٸ� ������ �̾߱�','21/10/13',130);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'myname.JPG','���� ����','�ƺ��� �Ҿ���. �װ͵� �ٷ� ���տ���. ���� ���� ����Ѵ�. �ݵ�� �� ������ �����ϰڳ���. ��ǥ�� ���ؼ���� ����� �������. ���� ������ ���Ŀ���� �Ǿ� ������ �����ϴ� ���̶� �ص�.','21/10/15',120);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'cha.JPG','������ ������','�������� ġ���ǻ� �������� ���� ��� ȫ������ §�� ������� ������ �ٴ帶�� ������������ ���̴� ƼŰŸī ���� �θǽ��� �׸� ���','21/08/28',110);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'super.JPG','SUPERNATURAL','���۳��߷��� �̱� The CW�� �ڷ��������� �濵�� ������ �׼� �ø����. 2005�� 9�� 13�Ϻ��� �̱����� ù ��۵Ǿ�����, 2020�� ���� 15�� �����Ǿ���. ���ΰ��� �� ������ �̾߱⸦ ���ĳ����� ����̴�.','05/09/13',100);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'boys.JPG','The Boys','20�� ���� �Ѵ� ��������ΰ� ������ ���� ������ �ൿ�� �ϻ�� ������ ���� ��������ο� ������� ���� �ʴ� �Ϲ������� ������ CIA ��� �ڰ����� �׵��� ������ �ϴ� �̾߱�','19/07/26',90);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'office.JPG','The Office','���ǽ��� �̱� NBC���� �濵�ߴ� �α� ��ť���͸� �ڹ̵� TV�ø����̴�. ������ ���� BBC���� �濵�� ������ ��Ʈ�� �����ǽ����̴�. ��Ǻ��̴Ͼ��� ��ũ���Ͽ� ��ġ�� ������ ����ȸ�� ���� ���ø� ������� �ϻ��Ȱ�� �׸��� �ִ�.','05/03/24',80);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'dune.JPG','DUNE','10191��, ��Ʈ���̵��� ������ �İ����� ��(Ƽ��� �����)�� �ð��� �ʿ��� �������� �� ���ָ� ������ ������ ���� ����� Ÿ����. �׸��� � ���ó�� ���� �޿��� �ƶ�Ű�� �༺�� �ִ� �� ������ ������. �𷡾���� ���ϴ� ���̶� �Ҹ��� �ƶ�Ű���� �� �� ��� ���� �縷������ ���ֿ��� ���� ��� ������ �ż��� ȯ���� �����̽��� ������ �������� �̸� �����ϱ� ���� ������ ġ���ϴ�. Ȳ���� ������� ���� ��Ʈ���̵��� ������ ������ ��ٸ��� �ƶ�Ű���� ���ϴµ��������� �ڴ� �θ��� �����Ѵ�, �η��� �¼���, �̰��� ������ �����̴�!','21/10/20',70);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'homeland.JPG','HOMELAND','Ȩ����� �Ͽ��� ���� �˷��� ���簡 ��ȹ�ϰ� ������ �̱��� ��ġ ������ �ڷ����� �ø����̴�. ����� ������ ������ �̽��� ��� �������θ� �������� �������.','10/10/02',60);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'billion.JPG','Billion Dollar Code','1990��� ����������, ������ �ٶ󺸴� ���ο� ����� ������ ���� �������� ��Ŀ. �׷κ��� 25�� ��, �� ����� �������� �ٽ� ������. ������ ���� Ư��ħ�� �Ҽ��� �����ϱ� ����.','21/10/09',50);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'husband.JPG','���ֺε�','�޼��迡 ������ ������ ���� ����� ������, �һ�� Ÿ��.�׷� �װ� �����ڸ� �����ϰ� ������ ������.�������ֺΡ�����! ������ ���� �ڹ̵�, ���⿡ ����!','21/10/05',40);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'euphoria.JPG','euphoria','�������ƴ� HBO���� 2019�� 6������ �濵�� �̱��� �ʴ� ��󸶷�, �̽����� ���� ��󸶸� �������� �Ѵ�.','19/06/16',30);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'billi.JPG','BILLIONS','���ܰ� ����� ������ �ʰ� �̵��� ���ϴ� �︸���� �����ݵ� �Ŵ���. �߽��� ���� �˻簡 �׿� ���� ö���� ���縦 �����Ѵ�.','16/1/17',20);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'love.JPG','������ ���','�ֵ��� ������ �ܴ��� �ø��� ��Ӵ� ������ ������ ���� ��� ȥ���� ������. ������ ������ ���� �ٷθ� �˾Ҵ� ���ο� �������� ������ ������ ã�� �ڽ��� ���� ������ ���ش޶�� ��. ���� ������ ���ϱ� �������� ���� ��ʸ� ġ���� ����� ��ε� �Բ� ����ִ�. ��Ӵ��� ������ ���� �ߵ����� ���� ���Ŵ� ���Ͽ� �ο� �ִ� �׳��� ���ſ� �����Ѵ�. �׸��� �� ������ ������ ������� ������ ��ٸ��� �ִµ�....','10/09/17',10);
COMMIT;

SELECT * FROM CONTENTDTO;
SELECT * FROM MARKDTO;
SELECT * FROM memberdto;

DELETE FROM MARKDTO WHERE KNUM = 3;

ROLLBACK { TRAN | TRANSACTION }   
     [ transaction_name | @tran_name_variable  
     | savepoint_name | @savepoint_variable ]   
[ ; ]  
