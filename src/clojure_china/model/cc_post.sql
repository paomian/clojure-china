create table if not exists CC_POSTS
(
  ID serial primary key,
  MARK integer,
  AUTHOR varchar(50) NOT NULL references cc_user(username),
  TITLE varchar(100) NOT NULL unique,
  CONTENT text,
  NODE varchar(50) references cc_node(node),
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','other')),
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);