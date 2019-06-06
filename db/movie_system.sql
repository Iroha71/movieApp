/*fๆ\๑VXe SQL(~ุ) ฯXตฝ็o[WLqซ */
/* ver1.2 */

DROP TABLE movie_reervation_item;

DROP TABLE movie_reservation;

DROP TABLE movie_fee;

DROP TABLE movie_term;

DROP TABLE movie;

DROP TABLE term;

DROP TABLE fee;

DROP TABLE screen;

DROP TABLE theater;

DROP TABLE administrator;

DROP TABLE member;
/*๏๕e[u*/


CREATE TABLE member(
	member_number integer auto_increment NOT NULL,
	member_mail varchar(128) NOT NULL,
	member_name varchar(128) NOT NULL,
	member_birthday date NOT NULL,
	member_phone varchar(15) NOT NULL,
	member_sei varchar(3) NOT NULL,
	member_pass varchar(32) NOT NULL,
	primary key(member_number),
	index(member_number)
);

/*วาtable*/

CREATE TABLE administrator(
	administrator_number integer auto_increment NOT NULL,
	administrator_mail varchar(128) NOT NULL,
	administrator_name varchar(128) NOT NULL,
	administrator_pass varchar(32)  NOT NULL,
	primary key(administrator_number)
);
/*VA^[table*/

CREATE TABLE theater(
	theater_id varchar(128) NOT NULL,
	theater_name varchar(128) NOT NULL,
	primary key(theater_id)
);

/*XN[table*/

CREATE TABLE screen(
	theater_id varchar(128) NOT NULL,
	screen_number integer NOT NULL,
	sheet_total integer NOT NULL,
	primary key(theater_id,screen_number),
	foreign key (theater_id) references theater(theater_id)
);

/*ฟเtable*/

CREATE TABLE fee(
	fee_type varchar(128) NOT NULL,
	fee_base integer NOT NULL,
	fee_min_age integer,
	fee_max_age integer,
	primary key(fee_type)
);

/*^[*/



CREATE TABLE term(
	term_type varchar(128) NOT NULL,
	term_start time NOT NULL,
	term_finish time NOT NULL,
	primary key(term_type)
);

/*fๆtable*/


CREATE TABLE movie(
	movie_id varchar(128) NOT NULL,
	administrator_number integer NOT NULL,
	movie_name varchar(128) NOT NULL,
	cast varchar(128) NOT NULL,
	directed varchar(128) NOT NULL,
	release_start_date DATE NOT NULL,
	release_finish_date DATE NOT NULL,
	movie_detail varchar(256) NOT NULL,
	primary key(movie_id),
	foreign key(administrator_number) references administrator(administrator_number)
);

/*fๆ^[*/


CREATE TABLE movie_term(
	movie_term_number integer auto_increment NOT NULL,
	movie_id varchar(128) NOT NULL,
	term_type varchar(128) NOT NULL,
	primary key (movie_term_number),
	foreign key (movie_id) references movie(movie_id),
	foreign key (term_type) references term(term_type),
	index(movie_term_number)
);


/*fๆฟเtable*/

CREATE TABLE movie_fee(
	movie_fee_number integer NOT NULL,
	movie_id varchar(128) NOT NULL,
	fee_type varchar(128) NOT NULL,
	primary key(movie_fee_number),
	foreign key (movie_id) references movie(movie_id),
	foreign key (fee_type) references fee(fee_type)
);

/*fๆ\๑*/


CREATE TABLE movie_reservation(
	reservation_number integer auto_increment NOT NULL,
	movie_term_number integer NOT NULL,
	theater_id varchar(128) NOT NULL,
	screen_number integer NOT NULL,
	member_number integer NOT NULL,
	reservation_date date NOT NULL,
	primary key (reservation_number),
	foreign key (movie_term_number) references movie_term(movie_term_number),
	foreign key (theater_id,screen_number) references screen(theater_id,screen_number),
	foreign key (member_number) references member(member_number),
	index(reservation_number)
);

/*fๆ\๑พื*/



CREATE TABLE movie_reservation_item(
	reservation_number integer NOT NULL,
	sheet_number varchar(2) NOT NULL,
	movie_fee_number integer NOT NULL,
	primary key(reservation_number,sheet_number),
	foreign key (reservation_number) references movie_reservation(reservation_number),
	foreign key (movie_fee_number) references movie_fee(movie_fee_number)
);
