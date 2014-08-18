create table if not exists CC_USER
(
  ID serial primary key,
  USERNAME varchar(50) NOT NULL UNIQUE,
  PASSWORD varchar(100) NOT NULL,
  EMAIL varchar(100) NOT NULL,
  IS_ADMIN boolean NOT NULL,
  REGISTER_TIME timestamp without time zone,
  LAST_LOGIN_TIME timestamp without time zone
);

create table if not exists CC_NODE
(
  ID serial primary key,
  NODE varchar(20) unique,
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);

create table if not exists CC_POST
(
  ID serial primary key,
  MARK integer,
  AUTHOR varchar(50) NOT NULL references cc_user(id),
  TITLE varchar(100) NOT NULL unique,
  CONTENT text,
  NODE varchar(50) references cc_node(id),
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','other')),
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);

create table if not exists CC_REPLY
(
  ID serial primary key,
  POST varchar(50) references cc_post(id),
  REPLY text,
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good')),
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);

create table if not exists POSTS_NODES
(
  POST_ID
)