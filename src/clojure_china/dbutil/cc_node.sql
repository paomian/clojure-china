create table if not exists CC_NODE
(
  ID serial primary key,
  NODE_NAME varchar(20) unique
);