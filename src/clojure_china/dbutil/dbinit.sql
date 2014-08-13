create table if not exists cc_user
(
	id serial primary key,
	username varchar(50) NOT NULL UNIQUE,
	password varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	is_admin boolean NOT NULL,
	register_time timestamp without time zone,
	last_login_time timestamp without time zone
);

create table if not exists cc_node
(
	id serial primary key,
	node_name varchar(20) unique
);

create table if not exists cc_post
(
	id serial primary key,
	mark integer,
	author varchar(50) NOT NULL references cc_user(username),
	title varchar(100) NOT NULL unique,
	content text,
	create_time timestamp without time zone,
	node varchar(50) references cc_node(node_name),
	status varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','other'))
);

create table if not exists cc_reply
(
	id serial primary key,
	time timestamp without time zone,
	post varchar(50) references cc_post(title),
	reply text,
	status varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good'))
);

