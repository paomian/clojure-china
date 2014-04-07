create table cc_reply
(
	id serial primary key,
	time timestamp without time zone,
	post varchar(50) references cc_post(id)
	reply text,
	status varchar(10) CONSTRAINT check_status CHECK (status in ('normal','is_delete','good'))
)
