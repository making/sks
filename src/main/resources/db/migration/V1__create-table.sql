CREATE TABLE cleaning_type(
	cleaning_type_id NUMBER(2) CONSTRAINT pk_cleaning_type PRIMARY KEY,
	cleaning_type_name VARCHAR2(50) CONSTRAINT cleaning_type_name_nn NOT NULL,
	cleaning_type_cycle NUMBER(3) CONSTRAINT cleaning_type_cycle_nn NOT NULL
	);

CREATE TABLE cleaning_event(
	cleaning_type_id NUMBER(2) CONSTRAINT cleaning_type_id_fk REFERENCES cleaning_type(cleaning_type_id),
	cleaning_date DATE CONSTRAINT cleaning_date_nn NOT NULL,
	cleaning_user VARCHAR2(100) CONSTRAINT cleaning_user_nn NOT NULL,
	register_date DATE CONSTRAINT register_date_nn NOT NULL,
	register_user VARCHAR2(100) CONSTRAINT register_user_nn NOT NULL,
	CONSTRAINT cleaning_event_pk PRIMARY KEY(cleaning_type_id, cleaning_date)
	);