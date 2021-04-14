CREATE TABLE member
(
    `uid`      INT             NOT NULL    AUTO_INCREMENT COMMENT '고유번호', 
    `id`       VARCHAR(45)     NOT NULL    COMMENT '아이디', 
    `pw`       VARCHAR(200)    NOT NULL    COMMENT '비밀번호', 
    `name`     VARCHAR(45)     NOT NULL    COMMENT '이름', 
    `email`    VARCHAR(200)    NOT NULL    COMMENT '이메일', 
    `regDate`  DATETIME        NOT NULL    COMMENT '가입날짜', 
    PRIMARY KEY (uid)
);

ALTER TABLE member
    ADD CONSTRAINT UC_id UNIQUE (id);

ALTER TABLE member
    ADD CONSTRAINT UC_email UNIQUE (email);