DROP Table Member;

CREATE TABLE member(
    id       INT NOT NULL AUTO_INCREMENT  COMMENT '회원ID',
    username VARCHAR(30) NOT NULL COMMENT '닉네임',
    pwd      VARCHAR(30) NOT NULL COMMENT '암호',
    cre_date DATETIME NOT NULL COMMENT '가입일',
    mod_date DATETIME NOT NULL COMMENT '마지막암호변경일',
    PRIMARY KEY (id)
)
COMMENT '회원기본정보';

-- ALTER TABLE member
--     ADD CONSTRAINT member_pk_id 
--         PRIMARY KEY(id);

-- CREATE UNIQUE INDEX member_uix_email
--     ON member(email asc);

-- NOW() : 현재 시간     

INSERT INTO member(id,nickname,pwd,cre_date,mod_date)
VALUES ('usertest01','iu','1111',NOW(),NOW());

COMMIT;

SELECT * FROM member;

-- ------------------------------
-- B
CREATE TABLE chatroom(
    chatroomnum      INT NOT NULL COMMENT '채팅룸번호',
    user_id          INT NOT NULL COMMENT '유저id',
    chatroomcre_date DATETIME NOT NULL COMMENT '채팅룸생성일',
    PRIMARY KEY (chatroomnum)
)
COMMENT '채팅룸정보';

CREATE TABLE userchatting(
    id             INT NOT NULL AUTO_INCREMENT COMMENT '회원ID',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    INT NOT NULL COMMENT '채팅룸번호',
    chatting_date  DATETIME NOT NULL COMMENT '채팅일',
    PRIMARY KEY (id)
)
COMMENT '유저채팅정보';

CREATE TABLE chatterchatting(
    id             INT NOT NULL AUTO_INCREMENT COMMENT '채터ID',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    INT NOT NULL COMMENT '채팅룸번호',
    chatting_date  DATETIME NOT NULL COMMENT '채팅일',
    PRIMARY KEY (id)
)
COMMENT '채터채팅정보';




-- ---------------------------------------------------------------
-- A
CREATE TABLE chatroom(
    chatroomnum    VARCHAR(30) NOT NULL AUTO_INCREMENT COMMENT '채팅룸번호',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomcre_date DATETIME NOT NULL COMMENT '채팅룸생성일'
)
COMMENT '채팅룸정보';


-- -------------------------------------------------------
ALTER TABLE `aiservice`.`member` 
CHANGE COLUMN `nickname` `username` VARCHAR(30) NOT NULL COMMENT '닉네임' ;










