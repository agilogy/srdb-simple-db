create sequence employees_serial;

create table employees(
    id integer primary key default nextval('employees_serial'),
    name text not null,
    department_id integer
);

create table employee_family_members(
    id serial primary key,
    employee_id integer not null,
    name text not null,
    foreign key (employee_id) references employees(id)
);

create sequence departments_serial;

create table departments(
    id integer primary key default nextval('departments_serial'),
    name varchar(128) not null,
    city text not null default 'Vilafranca',
    code varchar(5),
    head_id integer,
    active boolean not null default true,
    foreign key (head_id) references employees(id),
    unique(name)
);

alter table employees add foreign key (department_id) references departments(id);

create table planets(
    name varchar(128) primary key,
    position integer not null,
    unique(position)
);

create sequence table_with_numeric_serial;

create table table_with_numeric(
    id integer default nextval('table_with_numeric_serial'),
    number numeric,
    primary key (id,number)
);


create table dummy_with_date_times(
    id serial primary key,
    ts timestamp with time zone,
    d date,
    t time
);
