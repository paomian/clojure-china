create table cc_user
(
	id serial primary key,
	username varchar(50) NOT NULL UNIQUE,
	password varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	is_admin boolean NOT NULL,
	register_time timestamp without time zone,
	last_login_time timestamp without time zone
);
