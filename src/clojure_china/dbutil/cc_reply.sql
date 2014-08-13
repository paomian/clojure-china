create table if not exists CC_REPLY
(
  ID serial primary key,
  TIME timestamp without time zone,
  POST varchar(50) references cc_post(title),
  REPLY text,
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good'))
);