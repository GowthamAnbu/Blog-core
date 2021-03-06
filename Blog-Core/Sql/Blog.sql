CREATE TABLE USERS(
ID BIGINT NOT NULL AUTO_INCREMENT,
ROLE_ID BIGINT NOT NULL DEFAULT 2,
NAME VARCHAR(30) NOT NULL,
USER_NAME VARCHAR(30) NOT NULL,
PASSWORD VARCHAR(30) NOT NULL,
EMAIL_ID VARCHAR(40) NOT NULL,
PHONE_NUMBER VARCHAR(10) NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT USER_NAME_UK UNIQUE(USER_NAME),
CONSTRAINT EMAIL_ID_UK UNIQUE(EMAIL_ID),
CONSTRAINT PHONE_NUMBER_UK UNIQUE(PHONE_NUMBER)
CONSTRAINT ROLE_ID_FK FOREIGN KEY(ROLE_ID) REFERENCES ROLE(ID)
);

CREATE TABLE ROLE(
ID BIGINT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(30) NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT ROLE_USER_NAME_UK UNIQUE(NAME)
);

CREATE TABLE ARTICLES(
ID BIGINT NOT NULL AUTO_INCREMENT,
USER_ID BIGINT NOT NULL,
NAME VARCHAR(30) NOT NULL,
CONTENT TEXT NOT NULL,
PUBLISHED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
MODIFIED_DATE TIMESTAMP NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT ARTICLES_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES USERS(ID),
CONSTRAINT ANAME_USER_ID_UK UNIQUE(NAME,USER_ID)
);

CREATE TABLE CATEGORIES(
ID BIGINT NOT NULL AUTO_INCREMENT,
NAME VARCHAR(30) NOT NULL,
USER_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT CATEGORY_NAME_UK UNIQUE(NAME),
CONSTRAINT CATEGORIES_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE CATEGORY_DETAILS(
ID BIGINT NOT NULL AUTO_INCREMENT,
ARTICLE_ID BIGINT NOT NULL,
CATEGORY_ID BIGINT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT CATEGORY_DETAILS_UK UNIQUE(ARTICLE_ID,CATEGORY_ID),
CONSTRAINT CATEGORY_DETAILS_ARTICLE_ID_FK FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLES(ID),
CONSTRAINT CATEGORY_ID_FK FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORIES(ID)
);


CREATE TABLE COMMENTS_DETAILS(
ID BIGINT NOT NULL AUTO_INCREMENT,
ARTICLE_ID BIGINT NOT NULL,
USER_ID BIGINT NOT NULL,
COMMENTS TEXT NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT COMMENTS_DETAILS_ARTICLE_ID_FK FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLES(ID),
CONSTRAINT COMMENTS_DETAILS_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USERS(ID)
);

CREATE TABLE RATINGS_DETAILS(
ID BIGINT NOT NULL AUTO_INCREMENT,
ARTICLE_ID BIGINT NOT NULL,
USER_ID BIGINT NOT NULL,
LIKE BOOLEAN NOT NULL,
RATINGS TINYINT(5) NOT NULL,
PRIMARY KEY(ID),
CONSTRAINT RATINGS_DETAILS_ARTICLE_ID_FK FOREIGN KEY(ARTICLE_ID) REFERENCES ARTICLES(ID),
CONSTRAINT RATINGS_DETAILS_USER_ID_FK FOREIGN KEY(USER_ID) REFERENCES USERS(ID),
CONSTRAINT RATINGS_DETAILS_UK UNIQUE(ARTICLE_ID,USER_ID)
);