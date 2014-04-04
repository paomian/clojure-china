create table cc_post
(
	id serial primary key,
	author varchar(50) NOT NULL references cc_user(username),
	title varchar(100) NOT NULL,
	content text,
	create_time timestamp without time zone,
	classify varchar(50) references cc_classify(classify_name),
)
