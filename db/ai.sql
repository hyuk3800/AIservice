DROP Table Member;

CREATE TABLE member(
    id     VARCHAR(30) NOT NULL COMMENT '회원ID',
    pwd    VARCHAR(30) NOT NULL COMMENT '암호',
    cre_date DATETIME NOT NULL COMMENT '가입일',
    mod_date DATETIME NOT NULL COMMENT '마지막암호변경일'
)
COMMENT '회원기본정보';

ALTER TABLE member
    ADD CONSTRAINT member_pk_id 
        PRIMARY KEY(id);
        
-- CREATE UNIQUE INDEX member_uix_email
--     ON member(email asc);

-- NOW() : 현재 시간     

INSERT INTO member(id,pwd,cre_date,mod_date)
VALUES ('iu','1111',NOW(),NOW());

COMMIT;

SELECT * FROM member;

-- ------------------------------

CREATE TABLE userchatting(
    id             VARCHAR(30) NOT NULL COMMENT '회원ID',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    VARCHAR(30) NOT NULL COMMENT '채팅룸번호',
    chatting_date DATETIME NOT NULL COMMENT '채팅일'
)
COMMENT '유저채팅정보';

CREATE TABLE chatterchatting(
    id             VARCHAR(30) NOT NULL COMMENT '채터ID',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    VARCHAR(30) NOT NULL COMMENT '채팅룸번호',
    chatting_date DATETIME NOT NULL COMMENT '채팅일'
)
COMMENT '채터채팅정보';


-- A
CREATE TABLE chatroom(
    chatroomnum    VARCHAR(30) NOT NULL COMMENT '채팅룸번호',
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomcre_date DATETIME NOT NULL COMMENT '채팅룸생성일'
)
COMMENT '채팅룸정보';
chatroomchatroom

-- B
CREATE TABLE chatroom(
    chatroomnum    VARCHAR(30) NOT NULL COMMENT '채팅룸번호',
    chatroomcre_date DATETIME NOT NULL COMMENT '채팅룸생성일'
)
COMMENT '채팅룸정보';









