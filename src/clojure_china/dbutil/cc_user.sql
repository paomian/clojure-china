create table cc_user
(
	id serial primary key,
	username varchar(20) not null,
	password varchar(100) not null,
	email varchar(20) not null,
	is_admin boolean not null,
	register_time timestamp without time zone,
	last_login_time timestamp without time zone
);
