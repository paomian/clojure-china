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
  NODE_NAME varchar(20) unique
);

create table if not exists CC_POST
(
  ID serial primary key,
  MARK integer,
  AUTHOR varchar(50) NOT NULL references cc_user(username),
  TITLE varchar(100) NOT NULL unique,
  CONTENT text,
  CREATE_TIME timestamp without time zone,
  NODE varchar(50) references cc_node(node_name),
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','other'))
);

create table if not exists CC_REPLY
(
  ID serial primary key,
  TIME timestamp without time zone,
  POST varchar(50) references cc_post(title),
  REPLY text,
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good'))
);