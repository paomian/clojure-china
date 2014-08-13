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