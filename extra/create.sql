CREATE TABLE app_user
(
    id INTEGER PRIMARY KEY NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    sso_id VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX uk_hqk6uc88j3imq8u9jhro36vt3 ON app_user (sso_id);

CREATE SEQUENCE public.app_user_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.app_user ALTER COLUMN id SET DEFAULT nextval('public.app_user_id_seq');
ALTER SEQUENCE public.app_user_id_seq OWNED BY public.app_user.id;

CREATE TABLE app_user_user_profile
(
    user_id INTEGER NOT NULL,
    user_profile_id INTEGER NOT NULL,
    CONSTRAINT app_user_user_profile_pkey PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT fk_brmce0t584euix4wb4rursf1q FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_gs2lq4vmukguubh36utd4r2cl FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

CREATE TABLE exam
(
    id INTEGER PRIMARY KEY NOT NULL,
    datecreated TIMESTAMP,
    difficulty VARCHAR(255),
    items INTEGER,
    locked BOOLEAN,
    name VARCHAR(255) NOT NULL,
    points INTEGER,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_6jvbpulikqwootp7biqrqgo8x FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE public.exam_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.exam ALTER COLUMN id SET DEFAULT nextval('public.exam_id_seq');
ALTER SEQUENCE public.exam_id_seq OWNED BY public.exam.id;

CREATE TABLE exam_group
(
    id INTEGER PRIMARY KEY NOT NULL,
    datecreated TIMESTAMP,
    locked BOOLEAN,
    name VARCHAR(255),
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_2mw18faf524ju2mq460779aui FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE public.exam_group_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.exam_group ALTER COLUMN id SET DEFAULT nextval('public.exam_group_id_seq');
ALTER SEQUENCE public.exam_group_id_seq OWNED BY public.exam_group.id;

CREATE TABLE exam_group_app_user
(
    exam_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_ma5ji0i85kl7mdb0iqgsykmkj FOREIGN KEY (exam_id) REFERENCES exam_group (id),
    CONSTRAINT fk_1ytmegefd3bk3w6iitaivnln1 FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE TABLE exam_instance
(
    id INTEGER PRIMARY KEY NOT NULL,
    autosolved BOOLEAN,
    datecreated TIMESTAMP,
    enddate TIMESTAMP,
    name VARCHAR(255) NOT NULL,
    startdate TIMESTAMP,
    status VARCHAR(255),
    exam_id INTEGER NOT NULL,
    group_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_4h49sg9yg0f7i1v0gguyvv78y FOREIGN KEY (exam_id) REFERENCES exam (id),
    CONSTRAINT fk_40ryce2kwor126wknsihit7tk FOREIGN KEY (group_id) REFERENCES exam_group (id),
    CONSTRAINT fk_9r9n3x7rvhjmnets9tfmxs36 FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE public.exam_instance_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.exam_instance ALTER COLUMN id SET DEFAULT nextval('public.exam_instance_id_seq');
ALTER SEQUENCE public.exam_instance_id_seq OWNED BY public.exam_instance.id;

CREATE TABLE exam_item
(
    id INTEGER PRIMARY KEY NOT NULL,
    assertion VARCHAR(255) NOT NULL,
    datecreated TIMESTAMP,
    difficulty VARCHAR(255),
    ord INTEGER,
    points INTEGER,
    title VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    exam_id INTEGER NOT NULL,
    CONSTRAINT fk_33a142q93vmitig9cbk7ggf6r FOREIGN KEY (exam_id) REFERENCES exam (id)
);

CREATE SEQUENCE public.exam_item_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.exam_item ALTER COLUMN id SET DEFAULT nextval('public.exam_item_id_seq');
ALTER SEQUENCE public.exam_item_id_seq OWNED BY public.exam_item.id;

CREATE TABLE item_answer
(
    id INTEGER PRIMARY KEY NOT NULL,
    correct BOOLEAN,
    datecreated TIMESTAMP,
    value VARCHAR(255),
    item_id INTEGER NOT NULL,
    CONSTRAINT fk_einrad84lv17190gndhfvdik3 FOREIGN KEY (item_id) REFERENCES exam_item (id)
);

CREATE SEQUENCE public.item_answer_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.item_answer ALTER COLUMN id SET DEFAULT nextval('public.item_answer_id_seq');
ALTER SEQUENCE public.item_answer_id_seq OWNED BY public.item_answer.id;

CREATE TABLE persistent_logins
(
    series VARCHAR(255) PRIMARY KEY NOT NULL,
    last_used TIMESTAMP,
    token VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX uk_3gq9wkitbp2ave684iu50mhn7 ON persistent_logins (token);
CREATE UNIQUE INDEX uk_a6c251uovnx2cp2c3vv2dentk ON persistent_logins (username);

CREATE TABLE student_exam_instance
(
    id INTEGER PRIMARY KEY NOT NULL,
    datecreated TIMESTAMP,
    dateupdated TIMESTAMP,
    points INTEGER,
    status INTEGER,
    exam_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_hbvkid2xgb28esqprmm8t4lbd FOREIGN KEY (exam_id) REFERENCES exam (id),
    CONSTRAINT fk_o2hbqg1jmnfd6t56hh69ett5k FOREIGN KEY (user_id) REFERENCES app_user (id)
);

CREATE SEQUENCE public.student_exam_instance_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.student_exam_instance ALTER COLUMN id SET DEFAULT nextval('public.student_exam_instance_id_seq');
ALTER SEQUENCE public.student_exam_instance_id_seq OWNED BY public.student_exam_instance.id;

CREATE TABLE student_item_answer
(
    id INTEGER PRIMARY KEY NOT NULL,
    correct BOOLEAN,
    datecreated TIMESTAMP,
    points INTEGER,
    rawanswer VARCHAR(255),
    reviewed BOOLEAN,
    solvable BOOLEAN,
    value VARCHAR(255),
    examitem_id INTEGER,
    user_id INTEGER NOT NULL,
    instance_id INTEGER NOT NULL,
    CONSTRAINT fk_6mjky8sm0xojtvcj3van91jgh FOREIGN KEY (examitem_id) REFERENCES exam_item (id),
    CONSTRAINT fk_t5njww8a4vxni1y9rl8oeg7u5 FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT fk_95wh7kmygdu2p4haqru1nt2g FOREIGN KEY (instance_id) REFERENCES student_exam_instance (id)
);

CREATE SEQUENCE public.student_item_answer_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.student_item_answer ALTER COLUMN id SET DEFAULT nextval('public.student_item_answer_id_seq');
ALTER SEQUENCE public.student_item_answer_id_seq OWNED BY public.student_item_answer.id;

CREATE TABLE user_profile
(
    id INTEGER PRIMARY KEY NOT NULL,
    type VARCHAR(15) NOT NULL
);
CREATE UNIQUE INDEX uk_cva7m2blp7ekclxworqxau1l7 ON user_profile (type);

CREATE SEQUENCE public.user_profile_id_seq MINVALUE 1000 NO MAXVALUE NO CYCLE;
ALTER TABLE public.user_profile ALTER COLUMN id SET DEFAULT nextval('public.user_profile_id_seq');
ALTER SEQUENCE public.user_profile_id_seq OWNED BY public.user_profile.id;


INSERT INTO user_profile (type) VALUES ('ADMIN');
INSERT INTO user_profile (type) VALUES ('PROFESSOR');
INSERT INTO user_profile (type) VALUES ('STUDENT');
INSERT INTO app_user (email, first_name, last_name, password, sso_id)
  VALUES ('admin@mail.com', 'adi', 'minu', '$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'admin');

INSERT INTO app_user_user_profile (user_id, user_profile_id) VALUES (1000, 1000);

