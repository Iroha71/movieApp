/* 編集の際はバージョンを記述↓ */
/* ver1.2 */
insert into member(member_mail,member_name,member_birthday,member_phone,member_sei,member_pass)
values('aaa@gmail.com','宮本桃太郎','1999-03-22','090-2505-9998','その他','ken0322');


insert into administrator(administrator_mail,administrator_name,administrator_pass) values('mail@gmail.com','宮本尿太郎','ken0322');


insert into fee values('大人',2000,18,60);

insert into term values('1','10:00:00','12:00:00');

insert into theater values('1','宮本劇場');

insert into screen values('1',1,30);

insert into movie(administrator_number,movie_name,cast,directed,release_start_date,release_finish_date,movie_detail) values(1,'came shoot','宮本健太郎','宮本桃太郎','2018-03-22','2019-03-22','宮本健太郎の本当の姿を現すドキュメント映画');

insert into movie_fee(movie_id,fee_type) values(1,'大人');

insert into movie_term(movie_id,term_type) values(1,'1');

insert into movie_reservation(movie_term_number,theater_id,screen_number,member_number) values(1,'1',1,1);

insert into movie_reservation_item values(1,10,1);
