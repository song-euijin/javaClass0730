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
		VALUES(CNUM_SEQ.NEXTVAL,'trend-1.jpg','김아','아아',SYSDATE,0);
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
		VALUES(CNUM_SEQ.NEXTVAL,'trend-1.jpg','하','아아','00/01/01',0);
    commit;


---------------------------------------------------------------------------------------------


INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'Venom.JPG','베놈','진실을 위해서라면 몸을 사리지 않는 정의로운 열혈 기자 에디 브록 거대 기업 라이프 파운데이션의 뒤를 쫓던 그는 이들의 사무실에 잠입했다가 실험실에서 외계 생물체 심비오트의 기습 공격을 받게 된다.심비오트와 공생하게 된 에디 브록은 마침내 한층 강력한 베놈으로 거듭나고, 악한 존재만을 상대하려는 에디 브록의 의지와 달리 베놈은 난폭한 힘을 주체하지 못하는데…!','18/10/15',240);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'moga.JPG','모가디슈','대한민국이 UN가입을 위해 동분서주하던 시기1991년 소말리아의 수도 모가디슈에서는 일촉즉발의 내전이 일어난다.통신마저 끊긴 그 곳에 고립된 대한민국 대사관의 직원과 가족들은총알과 포탄이 빗발치는 가운데, 살아남기 위해 하루하루를 버텨낸다.그러던 어느 날 밤, 북한 대사관의 일행들이 도움을 요청하며 문을 두드리는데…목표는 하나, 모가디슈에서 탈출해야 한다!','21/07/28',230);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'woman.JPG','원더우먼','도플갱어를 마주치면 죽는다는 말이 있다. 이는 자기 자신을 마주한 충격에 심장마비가 오기 때문이라고 한다. 그렇다면 멘탈이 센 두 도플갱어가 마주하면? ','21/09/17',220);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'free.JPG','프리가이','평범한 직장, 절친 그리고 한 잔의 커피.평화로운 일상 속 때론 총격전과 날강도가 나타나는버라이어티한 프리시티에 살고 있는 가이.그에겐 뭐 하나 부족한 것이 없었다.우연히 마주친 그녀에게 한눈에 반하기 전까지는…갖은 노력 끝에 다시 만난 그녀는 가이가 비디오 게임 프리시티에 사는 배경 캐릭터이고,이 세상은 곧 파괴될 거라 경고한다.혼란에 빠진 가이 그러나 그는 프리시티의 파괴를 막기 위해더 이상 배경 캐릭터가 아닌, 히어로가 되기로 결심한다.올여름, 시원하게 터지는 상상초월 엔터테이닝 액션 블록버스터8월, 인생의 판을 바꿀 짜릿한 반란이 시작된다!','21/08/11',210);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'squid.JPG','오징어게임','빚에 쫓기는 수백 명의 사람들이 서바이벌 게임에 뛰어든다. 거액의 상금으로 새로운 삶을 시작하기 위해. 하지만 모두 승자가 될 순 없는 법. 탈락하는 이들은 치명적인 결과를 각오해야 한다.','21/09/17',200);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'time.JPG','타임 패러독스','폭파범, 요원, 의문의 남자, 그리고 존, 제인 우리는 이 일을 위해 태어났다 뉴욕을 초토화시킨 폭파 사건으로 대규모 사상자가 발생한다.의자 피즐 폭파범을 잡기 위해 범죄 예방 본부는 시간여행을 할 수 있는 템포럴 요원을 투입한다. 단서1. 템포럴 요원은 피즐 폭파범을 막다가 얼굴을 다쳐 이식수술을 한다. 단서2. 템포럴 요원은 바텐더로 위장 취업해 존을 만난다. 단서3. 존은, 고아원에서 자라나 우주비행사를 꿈꾸다가 의문의 남자를 만나 아이를 낳고 인생을 망친 소녀 제인에 대한 이야기를 한다.','14/08/28',190);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'007.JPG','007 스펙터','최악의 적 스펙터와 연관된 제임스 본드의 과거그리고 그 속에 숨겨진 거대한 비밀! 멕시코에서 일어난 폭발 테러 이후 MI6는 영국 정부에 의해 해체 위기에 놓인다.자신의 과거와 연관된 암호를 추적하던 제임스 본드는 사상 최악의 조직 ‘스펙터’와자신이 연관되어 있다는 사실을 알게 되고, 궁지에 몰린 MI6조차 그를 포기하면서절체절명의 위기에 직면하는데……','15/10/26',180);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'cowboy.JPG','카우보이 비밥','카우보이 비밥은 일본의 애니메이션 제작사 선라이즈가 제작한 작품으로 TV동경계열에서 1998년 4월부터 같은 해 6월까지 전26화 중 일부가 방송되고 그 후 WOWOW에서 같은 해 10월부터 1999년 4월에 걸쳐 전26화가 방송되었다.','01/10/23',170);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'yeonmo.JPG','연모','쌍둥이로 태어나 여아라는 이유만으로 버려졌던 아이가 오라비 세손의 죽음으로 남장을 통해 세자가 되면서 벌어지는 비밀스러운 궁중 로맨스 드라마','21/10/11',160);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'1994.JPG','응답하라 1994','응답하라 1994는 2013년 10월 18일부터 2013년 12월 28일까지 tvN에서 방영한 금토 드라마로 2012년에 방영된 《응답하라 1997》의 후속작이다. 아이돌과 음악이 메인 소재였던 전작과는 달리 이번에는 야구라는 흔하지 않은 소재가 나왔다.','13/10/18',150);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'oats.JPG','오츠스튜디오','종말이 찾아온 세계. 인간이 인간다울 수 없게 된 그곳은 어떤 모습일까. 그 끔찍하고 처참한 세상이 실험적인 단편 영화를 통해 펼쳐진다. 영화감독 닐 블롬캠프가 제작한 시리즈.','17/10/01',140);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'you.JPG','너를 닮은 사람','아내와 엄마라는 수식어를 버리고 자신의 욕망에 충실했던 여자와, 그 여자와의 짧은 만남으로 제 인생의 조연이 되어버린 또 다른 여자의 이야기','21/10/13',130);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'myname.JPG','마이 네임','아빠를 잃었다. 그것도 바로 눈앞에서. 남은 딸은 결심한다. 반드시 내 손으로 복수하겠노라고. 목표를 위해서라면 방법은 상관없다. 마약 조직의 언더커버가 되어 경찰에 잠입하는 것이라 해도.','21/10/15',120);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'cha.JPG','갯마을 차차차','현실주의 치과의사 윤혜진과 만능 백수 홍반장이 짠내 사람내음 가득한 바닷마을 ‘공진’에서 벌이는 티키타카 힐링 로맨스를 그린 드라마','21/08/28',110);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'super.JPG','SUPERNATURAL','수퍼내추럴은 미국 The CW의 텔레비전으로 방영된 스릴러 액션 시리즈다. 2005년 9월 13일부터 미국에서 첫 방송되었으며, 2020년 시즌 15로 종영되었다. 주인공인 두 형제의 이야기를 펼쳐나가는 드라마이다.','05/09/13',100);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'boys.JPG','The Boys','20만 명이 넘는 슈퍼히어로가 유명세에 취해 무모한 행동을 일삼고 부패한 세상에 슈퍼히어로와 상대조차 되지 않는 일반인으로 구성된 CIA 비밀 자경팀이 그들을 막으려 하는 이야기','19/07/26',90);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'office.JPG','The Office','오피스는 미국 NBC에서 방영했던 인기 모큐멘터리 코미디 TV시리즈이다. 원작은 영국 BBC에서 방영된 동명의 시트콤 《오피스》이다. 펜실베이니아주 스크랜턴에 위치한 가상의 제지회사 던더 미플린 사원들의 일상생활을 그리고 있다.','05/03/24',80);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'dune.JPG','DUNE','10191년, 아트레이데스 가문의 후계자인 폴(티모시 샬라메)은 시공을 초월한 존재이자 전 우주를 구원할 예지된 자의 운명을 타고났다. 그리고 어떤 계시처럼 매일 꿈에서 아라키스 행성에 있는 한 여인을 만난다. 모래언덕을 뜻하는 듄이라 불리는 아라키스는 물 한 방울 없는 사막이지만 우주에서 가장 비싼 물질인 신성한 환각제 스파이스의 유일한 생산지로 이를 차지하기 위한 전쟁이 치열하다. 황제의 명령으로 폴과 아트레이데스 가문은 죽음이 기다리는 아라키스로 향하는데…위대한 자는 부름에 응답한다, 두려움에 맞서라, 이것은 위대한 시작이다!','21/10/20',70);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'homeland.JPG','HOMELAND','홈랜드는 하워드 고든과 알렉스 갠사가 기획하고 제작한 미국의 정치 스릴러 텔레비전 시리즈이다. 가디온 래프가 제작한 이스라엘 드라마 전쟁포로를 바탕으로 만들었다.','10/10/02',60);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'billion.JPG','Billion Dollar Code','1990년대 베를린에서, 세상을 바라보는 새로운 방식을 개발한 젊은 예술가와 해커. 그로부터 25년 후, 두 사람은 법정에서 다시 만난다. 구글을 상대로 특허침해 소송을 제기하기 위해.','21/10/09',50);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'husband.JPG','극주부도','뒷세계에 수많은 전설을 남긴 흉악한 야쿠자, 불사신 타츠.그런 그가 야쿠자를 은퇴하고 선택한 길은―.「전업주부」였다! 가정적 임협 코미디, 여기에 개막!','21/10/05',40);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'euphoria.JPG','euphoria','유포리아는 HBO에서 2019년 6월부터 방영된 미국의 십대 드라마로, 이스라엘의 동명 드라마를 원작으로 한다.','19/06/16',30);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'billi.JPG','BILLIONS','수단과 방법을 가리지 않고 이득을 취하는 억만장자 헤지펀드 매니저. 야심찬 연방 검사가 그에 대한 철저한 수사를 시작한다.','16/1/17',20);
INSERT INTO CONTENTDTO 
VALUES(CNUM_SEQ.NEXTVAL,'love.JPG','그을린 사랑','쌍둥이 남매인 잔느와 시몽은 어머니 나왈의 유언을 전해 듣고 혼란에 빠진다. 유언의 내용은 죽은 줄로만 알았던 생부와 존재조차 몰랐던 형제를 찾아 자신이 남긴 편지를 전해달라는 것. 또한 편지를 전하기 전까지는 절대 장례를 치르지 말라는 당부도 함께 담겨있다. 어머니의 흔적을 따라 중동으로 떠난 남매는 베일에 싸여 있던 그녀의 과거와 마주한다. 그리고 그 과거의 끝에는 충격적인 진실이 기다리고 있는데....','10/09/17',10);
COMMIT;

SELECT * FROM CONTENTDTO;
SELECT * FROM MARKDTO;
SELECT * FROM memberdto;

DELETE FROM MARKDTO WHERE KNUM = 3;

ROLLBACK { TRAN | TRANSACTION }   
     [ transaction_name | @tran_name_variable  
     | savepoint_name | @savepoint_variable ]   
[ ; ]  
