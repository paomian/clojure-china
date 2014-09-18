create table if not exists CC_NODES
(
  ID serial primary key,
  NAME varchar(20) unique,
  CREATE_TIME timestamp without time zone,
  UPDATE_TIME timestamp without time zone
);