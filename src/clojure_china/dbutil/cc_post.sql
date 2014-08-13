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