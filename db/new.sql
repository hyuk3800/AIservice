CREATE TABLE aiservice.member(
    id       VARCHAR(100) NOT NULL COMMENT '회원ID',
    username VARCHAR(100) NOT NULL COMMENT '닉네임',
    pwd      VARCHAR(255) NOT NULL COMMENT '암호',
    cre_date DATETIME NOT NULL COMMENT '가입일',
    mod_date DATETIME NOT NULL COMMENT '마지막암호변경일',
    PRIMARY KEY (id)
)
COMMENT '회원기본정보';

CREATE TABLE aiservice.chatroom(
    chatroomnum      INT NOT NULL AUTO_INCREMENT COMMENT '채팅룸번호',
    user_id          VARCHAR(100) NOT NULL COMMENT '유저id',
    chatroomcre_date DATETIME NOT NULL COMMENT '채팅룸생성일',
    PRIMARY KEY (chatroomnum)
)
COMMENT '채팅룸정보';

CREATE TABLE aiservice.userchatting(
	chatter VARCHAR(200) NOT NULL,
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    INT NOT NULL COMMENT '채팅룸번호',
    chatting_date  DATETIME NOT NULL COMMENT '채팅일'
)
COMMENT '유저채팅정보';

CREATE TABLE aiservice.chatterchatting(
	chatter VARCHAR(200) NOT NULL,
    chattingrecord VARCHAR(400) NOT NULL COMMENT '채팅기록',
    chatroomnum    INT NOT NULL COMMENT '채팅룸번호',
    chatting_date  DATETIME NOT NULL COMMENT '채팅일'
)
COMMENT '채터채팅정보';