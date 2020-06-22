
/* Drop Triggers */

DROP TRIGGER TRI_cmt_cmt_id;
DROP TRIGGER TRI_comments_cmt_id;
DROP TRIGGER TRI_image_img_id;
DROP TRIGGER TRI_jjim_jjim_id;
DROP TRIGGER TRI_notice_notice_id;
DROP TRIGGER TRI_notification_notice_id;
DROP TRIGGER TRI_notification_not_id;
DROP TRIGGER TRI_post_post_id;



/* Drop Tables */

DROP TABLE jjim CASCADE CONSTRAINTS;
DROP TABLE s_post CASCADE CONSTRAINTS;
DROP TABLE category CASCADE CONSTRAINTS;
DROP TABLE notice CASCADE CONSTRAINTS;
DROP TABLE comments CASCADE CONSTRAINTS;
DROP TABLE image CASCADE CONSTRAINTS;
DROP TABLE post CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;
DROP TABLE city CASCADE CONSTRAINTS;
DROP TABLE cmt CASCADE CONSTRAINTS;
DROP TABLE notification CASCADE CONSTRAINTS;


/* Drop Sequences */

DROP SEQUENCE SEQ_cmt_cmt_id;
DROP SEQUENCE SEQ_comments_cmt_id;
DROP SEQUENCE SEQ_image_img_id;
DROP SEQUENCE SEQ_jjim_jjim_id;
DROP SEQUENCE SEQ_notice_notice_id;
DROP SEQUENCE SEQ_notification_notice_id;
DROP SEQUENCE SEQ_notification_not_id;
DROP SEQUENCE SEQ_post_post_id;




/* Create Sequences */

CREATE SEQUENCE SEQ_comments_cmt_id INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_image_img_id INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_jjim_jjim_id INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_notice_notice_id INCREMENT BY 1 START WITH 1 NOCACHE;
CREATE SEQUENCE SEQ_post_post_id INCREMENT BY 1 START WITH 1 NOCACHE;



/* Create Tables */

CREATE TABLE category
(
	ctg_id char(2) NOT NULL,
	ctg_name varchar2(30),
	PRIMARY KEY (ctg_id)
);


CREATE TABLE city
(
	city_id char(5) NOT NULL,
	city varchar2(30),
	region_id char(2),
	region varchar2(30),
	PRIMARY KEY (city_id)
);


CREATE TABLE comments
(
	cmt_id number NOT NULL,
	post_id number,
	parent_id number,
	user_id varchar2(20),
	secret char(1),
	content varchar2(1500),
	w_date date,
	PRIMARY KEY (cmt_id)
);


CREATE TABLE image
(
	img_id number NOT NULL,
	post_id number,
	img_path varchar2(100),
	PRIMARY KEY (img_id)
);


CREATE TABLE jjim
(
	jjim_id number NOT NULL,
	user_id varchar2(20),
	post_id number,
	PRIMARY KEY (jjim_id)
);


CREATE TABLE members
(
	id varchar2(20) NOT NULL,
	pwd varchar2(20),
	nick varchar2(30) NOT NULL UNIQUE,
	name varchar2(100),
	b_date date,
	gender char(1),
	email varchar2(50),
	tel varchar2(20),
	city_id char(5),
	prf_path varchar2(100),
	j_date date,
	PRIMARY KEY (id)
);


CREATE TABLE notice
(
	notice_id number NOT NULL,
	post_id number,
	cmt_id number,
	user_id varchar2(20),
	n_content varchar2(50),
	n_time date,
	PRIMARY KEY (notice_id)
);


CREATE TABLE post
(
	post_id number NOT NULL,
	board_id char(2) NOT NULL,
	user_id varchar2(20),
	title varchar2(200),
	content varchar2(4000),
	w_date date,
	hit number,
	PRIMARY KEY (post_id)
);


CREATE TABLE s_post
(
	post_id number NOT NULL,
	ctg_id char(2),
	city_id char(5),
	s_type char(1),
	price number,
	progress char(1),
	PRIMARY KEY (post_id)
);



/* Create Foreign Keys */

ALTER TABLE s_post
	ADD FOREIGN KEY (ctg_id)
	REFERENCES category (ctg_id)
;


ALTER TABLE members
	ADD FOREIGN KEY (city_id)
	REFERENCES city (city_id)
;


ALTER TABLE s_post
	ADD FOREIGN KEY (city_id)
	REFERENCES city (city_id)
;


ALTER TABLE comments
	ADD FOREIGN KEY (parent_id)
	REFERENCES comments (cmt_id)
	ON DELETE CASCADE
;


ALTER TABLE notice
	ADD FOREIGN KEY (cmt_id)
	REFERENCES comments (cmt_id)
	ON DELETE CASCADE
;


ALTER TABLE comments
	ADD FOREIGN KEY (user_id)
	REFERENCES members (id)
	ON DELETE CASCADE
;


ALTER TABLE jjim
	ADD FOREIGN KEY (user_id)
	REFERENCES members (id)
	ON DELETE CASCADE
;


ALTER TABLE notice
	ADD FOREIGN KEY (user_id)
	REFERENCES members (id)
	ON DELETE CASCADE
;


ALTER TABLE post
	ADD FOREIGN KEY (user_id)
	REFERENCES members (id)
	ON DELETE CASCADE
;


ALTER TABLE comments
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON DELETE CASCADE
;


ALTER TABLE image
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON DELETE CASCADE
;


ALTER TABLE notice
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON DELETE CASCADE
;


ALTER TABLE s_post
	ADD FOREIGN KEY (post_id)
	REFERENCES post (post_id)
	ON DELETE CASCADE
;


ALTER TABLE jjim
	ADD FOREIGN KEY (post_id)
	REFERENCES s_post (post_id)
	ON DELETE CASCADE
;



/* Create Triggers */


CREATE OR REPLACE TRIGGER TRI_comments_cmt_id BEFORE INSERT ON comments
FOR EACH ROW
BEGIN
	SELECT SEQ_comments_cmt_id.nextval
	INTO :new.cmt_id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_image_img_id BEFORE INSERT ON image
FOR EACH ROW
BEGIN
	SELECT SEQ_image_img_id.nextval
	INTO :new.img_id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_jjim_jjim_id BEFORE INSERT ON jjim
FOR EACH ROW
BEGIN
	SELECT SEQ_jjim_jjim_id.nextval
	INTO :new.jjim_id
	FROM dual;
END;

/

CREATE OR REPLACE TRIGGER TRI_notice_notice_id BEFORE INSERT ON notice
FOR EACH ROW
BEGIN
	SELECT SEQ_notice_notice_id.nextval
	INTO :new.notice_id
	FROM dual;
END;

/


CREATE OR REPLACE TRIGGER TRI_post_post_id BEFORE INSERT ON post
FOR EACH ROW
BEGIN
	SELECT SEQ_post_post_id.nextval
	INTO :new.post_id
	FROM dual;
END;

/




