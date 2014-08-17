create table if not exists CC_REPLYS
(
  ID serial primary key,
  POST varchar(50) references cc_post(title),
  REPLY text,
  STATUS varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good')),
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);