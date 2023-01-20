use task_manager_db;

insert into category(name)
values ('Work'),
       ('Chores'),
       ('Academic'),
       ('Finance'),
       ('Health'),
       ('Misc');

insert into users(email, first_name, last_name, password, profile_photo, username)
values ('amida@codeup','Amida','F','$2a$10$ew6QYYTaf6DE1Rrq3ZHXiOWzBE8GVjqxIhM97/Sdb4t1F98meFXgm
','https://cdn.filestackcontent.com/SftfgsETQmEGDT0gfjsq','amida')