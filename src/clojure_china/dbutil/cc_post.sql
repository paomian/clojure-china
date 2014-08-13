create table cc_post
(
	id serial primary key,
	mark integer,
	author varchar(50) NOT NULL references cc_user(username),
	title varchar(100) NOT NULL,
	content text,
	create_time timestamp without time zone,
	node varchar(50) references cc_node(id),
	status varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','other'))
)
