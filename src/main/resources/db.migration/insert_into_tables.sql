insert into user values('1@mail.ru','11111','ROLE_USER');
insert into user values('2@mail.ru','22222','ROLE_ADMIN');
insert into user values('3@mail.ru','33333','ROLE_NEW');

insert into user_data values ('1@mail.ru','Petr','Petr Petrovich', 'М', 'image1', '2000-06-16','Москва','Телец');
insert into user_data values ('2@mail.ru','Ann','Ann Antonova', 'Ж', 'image2', '1990-12-03', 'Самара','Овен');
insert into user_data values ('3@mail.ru','Nikolay','Kolyan', 'М', 'image3', '2010-01-03', 'Сызрань','Весы');



insert into message(date,time, sender, recipient,text, last) values (curdate(), curtime(), '2@mail.ru','3@mail.ru','Hello!', false);
insert into message(date,time, sender, recipient,text, last) values (curdate(), curtime(), '3@mail.ru','2@mail.ru', 'Hi!', false);
insert into message(date,time, sender, recipient,text, last) values (curdate(), curtime(), '2@mail.ru','1@mail.ru', 'Ты уволен!',false);
insert into message(date,time, sender, recipient,text, last) values (curdate(), curtime(), '1@mail.ru','2@mail.ru', 'Прости!',true);
insert into message(date,time, sender, recipient,text, last) values (curdate(), curtime(), '2@mail.ru','3@mail.ru', 'Петя мне надоел!',true);

insert into friends(friend1, friend2, status) values('1@mail.ru','2@mail.ru','Ожидание');
insert into friends(friend1, friend2, status) values('2@mail.ru','3@mail.ru','Ожидание');
insert into friends(friend1, friend2, status) values('1@mail.ru','3@mail.ru','Ожидание');

insert into complaint(date,time,sender,victim,text, checked) values (curdate(), curtime(), '2@mail.ru', '1@mail.ru', 'Заносчив', false);
insert into complaint(date,time,sender,victim,text, checked) values (curdate(), curtime(), '3@mail.ru', '1@mail.ru', 'Очень заносчив', false);