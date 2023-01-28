CREATE ROLE dev_school_site_admin WITH LOGIN CREATEDB ENCRYPTED PASSWORD 'fakedPassword';

create sequence seq_action_id;

alter sequence seq_action_id owner to dev_school_site_admin;

create domain tbanknotesamt as integer;

alter domain tbanknotesamt owner to dev_school_site_admin;

create domain tbinary as bytea;

alter domain tbinary owner to dev_school_site_admin;

create domain tboolean as boolean;

alter domain tboolean owner to dev_school_site_admin;

create domain tchessgamescore as numeric(3, 1);

alter domain tchessgamescore owner to dev_school_site_admin;

create domain tcoinsamt as integer;

alter domain tcoinsamt owner to dev_school_site_admin;

create domain tcurrencyiso as varchar(3);

alter domain tcurrencyiso owner to dev_school_site_admin;

create domain tcurrencystr10 as varchar(10);

alter domain tcurrencystr10 owner to dev_school_site_admin;

create domain tdate as date;

alter domain tdate owner to dev_school_site_admin;

create domain tdatetime as timestamp;

alter domain tdatetime owner to dev_school_site_admin;

create domain tgpscoordinates as point;

alter domain tgpscoordinates owner to dev_school_site_admin;

create domain tidbigcode as bigint;

alter domain tidbigcode owner to dev_school_site_admin;

create domain tidcode as integer;

comment
on type tidcode is 'Код сущности';

alter domain tidcode owner to dev_school_site_admin;

create domain tiduser as integer;

alter domain tiduser owner to dev_school_site_admin;

create domain timage as bytea;

alter domain timage owner to dev_school_site_admin;

create domain tintcounter as integer;

alter domain tintcounter owner to dev_school_site_admin;

create domain tinteger as integer;

alter domain tinteger owner to dev_school_site_admin;

create domain titemtype as smallint;

alter domain titemtype owner to dev_school_site_admin;

create domain tmoney as numeric(22, 4);

alter domain tmoney owner to dev_school_site_admin;

create domain tpercrate as numeric(12, 6);

alter domain tpercrate owner to dev_school_site_admin;

create domain tpercrateext as numeric(16, 8);

alter domain tpercrateext owner to dev_school_site_admin;

create domain treal as real;

alter domain treal owner to dev_school_site_admin;

create domain tstr10 as varchar(10);

alter domain tstr10 owner to dev_school_site_admin;

create domain tstr100 as varchar(100);

alter domain tstr100 owner to dev_school_site_admin;

create domain tstr10000 as varchar(10000);

alter domain tstr10000 owner to dev_school_site_admin;

create domain tstr128 as varchar(128);

alter domain tstr128 owner to dev_school_site_admin;

create domain tstr2 as varchar(2);

alter domain tstr2 owner to dev_school_site_admin;

create domain tstr20 as varchar(20);

alter domain tstr20 owner to dev_school_site_admin;

create domain tstr200 as varchar(200);

alter domain tstr200 owner to dev_school_site_admin;

create domain tstr2000 as varchar(2000);

alter domain tstr2000 owner to dev_school_site_admin;

create domain tstr3 as varchar(3);

alter domain tstr3 owner to dev_school_site_admin;

create domain tstr30 as varchar(30);

alter domain tstr30 owner to dev_school_site_admin;

create domain tstr40 as varchar(40);

alter domain tstr40 owner to dev_school_site_admin;

create domain tstr400 as varchar(400);

alter domain tstr400 owner to dev_school_site_admin;

create domain tstr50 as varchar(50);

alter domain tstr50 owner to dev_school_site_admin;

create domain tstr80 as varchar(80);

alter domain tstr80 owner to dev_school_site_admin;

create domain tsumext as numeric(24, 4);

alter domain tsumext owner to dev_school_site_admin;

create domain ttext as text;

alter domain ttext owner to dev_school_site_admin;

create domain ttime as time;

alter domain ttime owner to dev_school_site_admin;

create domain tidbytecode as numeric(1, 0);

alter domain tidbytecode owner to dev_school_site_admin;

create table offices
(
    office_id        tidbigcode not null
        constraint fk_offices
            primary key,
    office_code      tstr100    not null
        constraint ak_offices
            unique,
    office_country   tstr2      not null,
    office_name      tstr200    not null,
    office_region    tstr40     not null,
    office_address   tstr200,
    office_note      tstr400,
    parent_office_id tidbigcode
        constraint offices_parent_office
            references offices
);

comment
on table offices is 'Store offices';

alter table offices
    owner to dev_school_site_admin;

create table users
(
    user_id       tidbigcode not null
        primary key,
    user_login    tstr100    not null,
    user_password tstr200    not null,
    user_status   tstr20     not null,
    user_link     tstr100 default NULL:: character varying
);

alter table users
    owner to dev_school_site_admin;

create table role
(
    role_id   tidbigcode not null
        primary key,
    role_name tstr50     not null
);

alter table role
    owner to dev_school_site_admin;

create table role_user
(
    roles_users_id tidbigcode not null
        primary key,
    role_id        tidbigcode not null
        constraint fk_role
            references role
            on delete cascade,
    user_id        tidbigcode not null
        constraint fk_user
            references users
            on delete cascade
);

alter table role_user
    owner to dev_school_site_admin;

create table parents
(
    parents_id             tidbigcode not null
        primary key,
    parents_name_mom       tstr100,
    parents_lastname_mom   tstr100,
    parents_patronymic_mom tstr100,
    parents_name_dad       tstr100,
    parents_lastname_dad   tstr100,
    parents_patronymic_dad tstr100
);

alter table parents
    owner to dev_school_site_admin;

create table subject
(
    subject_id   tidbigcode not null
        primary key,
    subject_name tstr100    not null
);

alter table subject
    owner to dev_school_site_admin;

create table teacher
(
    teacher_id            tidbigcode not null
        primary key,
    user_teacher_id       tidbigcode
        references users,
    teacher_name          tstr100    not null,
    teacher_lastname      tstr100    not null,
    teacher_patronymic    tstr100,
    teacher_email         tstr100,
    teacher_qualification tstr100,
    teacher_position      tstr100
);

alter table teacher
    owner to dev_school_site_admin;

create table classroom
(
    classroom_id         tidbigcode not null
        primary key,
    teacher_classroom_id tidbigcode not null
        references teacher,
    classroom_name       tstr100    not null
);

alter table classroom
    owner to dev_school_site_admin;

create table pupil
(
    pupil_id               tidbigcode not null
        primary key,
    user_pupil_id          tidbigcode
        references users,
    pupil_name             tstr100    not null,
    pupil_lastname         tstr100    not null,
    pupil_patronymic       tstr100    not null,
    pupil_date_of_birthday tdate      not null,
    pupil_email            tstr100,
    pupil_personal_check   tstr100,
    classroom_pupil_id     tidbigcode not null
        references classroom,
    parents_pupil_id       tidbigcode not null
        references parents
);

alter table pupil
    owner to dev_school_site_admin;

create table calendar
(
    calendar_id        tidbigcode not null
        primary key,
    calendar_semester  tinteger   not null,
    calendar_week_day  tinteger   not null,
    lesson_calendar_id tinteger   not null
);

alter table calendar
    owner to dev_school_site_admin;

create table schedule
(
    schedule_id           tidbigcode not null
        primary key,
    classroom_schedule_id tidbigcode not null
        constraint fk_schedule_classroomid
            references classroom
            on delete cascade,
    subject_schedule_id   tidbigcode not null
        constraint fk_schedule_subjectid
            references subject
            on delete cascade,
    teacher_schedule_id   tidbigcode not null
        constraint fk_schedule_teacherid
            references teacher
            on delete cascade,
    schedule_week_day     tinteger   not null
        constraint ck_schedule_weekday
            check (((schedule_week_day):: integer >= 1
) AND ((schedule_week_day)::integer <= 6)),
    schedule_date         tdate      not null,
    calendar_schedule_id  bigint
        constraint fk_schedule_calendarid
            references calendar
            on delete cascade,
    schedule_hometask     tstr2000
);

alter table schedule
    owner to dev_school_site_admin;

create table attendance
(
    attendance_id        tidbigcode not null
        primary key,
    pupil_attendance_id  tidbigcode not null
        references pupil,
    class_attendance_id  tidbigcode not null
        references classroom,
    lesson_attendance_id tidbigcode not null
        references schedule
);

alter table attendance
    owner to dev_school_site_admin;

create table academic_performance
(
    academic_performance_id        tidbigcode not null
        primary key,
    pupil_academic_performance_id  tidbigcode not null
        references pupil,
    class_academic_performance_id  tidbigcode not null
        references classroom,
    lesson_academic_performance_id tidbigcode not null
        references schedule,
    academic_performance_grade     tinteger
);

alter table academic_performance
    owner to dev_school_site_admin;

create table questions_from_users
(
    questions_from_users_id       tidbigcode not null
        primary key,
    questions_from_users_question tstr2000   not null,
    questions_from_users_response tstr10000,
    questions_from_users_flag     tboolean
);

alter table questions_from_users
    owner to dev_school_site_admin;

insert into role (role_id, role_name)
values (1, 'ROLE_PUPIL');

insert into role (role_id, role_name)
values (2, 'ROLE_TEACHER');

insert into role (role_id, role_name)
values (3, 'ROLE_DIRECTOR');

insert into teacher (teacher_id, teacher_name, teacher_lastname, teacher_patronymic, teacher_email,
                     teacher_qualification, teacher_position)
values (1,'root','root','root','root','root','root');

insert into classroom (classroom_id, teacher_classroom_id, classroom_name)
values (1, 1, '11');

insert into users (user_id, user_login, user_password, user_status, user_link) values (1,'root','root','root','root');

insert into role_user (roles_users_id, role_id, user_id) VALUES (1, 3, 1);