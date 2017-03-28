-- mysql workbench forward engineering

set @old_unique_checks=@@unique_checks, unique_checks=0;
set @old_foreign_key_checks=@@foreign_key_checks, foreign_key_checks=0;
set @old_sql_mode=@@sql_mode, sql_mode='traditional,allow_invalid_dates';

-- -----------------------------------------------------
-- schema bookcomment
-- -----------------------------------------------------
drop schema if exists `bookcomment` ;

-- -----------------------------------------------------
-- schema bookcomment
-- -----------------------------------------------------
create schema if not exists `bookcomment` default character set utf8 ;

use `bookcomment` ;

-- -----------------------------------------------------
-- table `bookcomment`.`staff`
-- -----------------------------------------------------
drop table if exists `bookcomment`.`staff`;
create table if not exists `bookcomment`.`staff` (
  `staffid` varchar(4) not null,
  `staffname` varchar(200) not null,
  `staffkana` varchar(200) not null,
  `authority` int(11) not null,
  `phono` varchar(20) null default null,
  `mobileno` varchar(20) null default null,
  `mailaddress` varchar(200) null default null,
  `password` varchar(255) null default null,
  `remarks` text null default null,
  `regdate` timestamp not null default current_timestamp,
  `regstaffno` varchar(4) not null,
  `modifydate` timestamp not null default current_timestamp on update current_timestamp,
  `returnstaffno` varchar(4) not null,
  primary key (`staffid`))
engine = innodb
default character set = utf8;

-- ユーザーID=0000, パスワード=adash
insert into `biblio`.`staff` (`staffid`, `staffname`, `staffkana`, `authority`, `phono`, `mobileno`, `mailaddress`, `password`, `remarks`, `regdate`, `regstaffno`, `modifydate`, `returnstaffno`)
values ('0000', 'システム', 'ｼｽﾃﾑ', '2', '', '', '', '53fbeebbdf0f50e9033c59bece96b4ea4b7e185d179a3bd8ffb638b68c7f84c7', '', '2017-01-01 00:00:00', '0000', '2017-01-01 00:00:00', '0000');

-- -----------------------------------------------------
-- table `bookcomment`.`book`
-- -----------------------------------------------------
drop table if exists `bookcomment`.`book`;
create table if not exists `bookcomment`.`book` (
  `bookid` varchar(13) not null,
  `isbn` varchar(13) null default null,
  `title` varchar(1000) not null,
  `author` varchar(300) null default null,
  `ownerid` int(11) null default null,
  `ownername` varchar(200) null default null,
  `recommend` varchar(4) null default null,
  `ownercomment` text null default null,
  `ownerdate` datetime null default null,
  `regdate` timestamp not null default current_timestamp,
  `regstaffno` varchar(4) not null,
  `modifydate` timestamp not null default current_timestamp on update current_timestamp,
  `modifystaffno` varchar(4) not null,
  primary key (`bookid`))
engine = innodb
default character set = utf8;


-- -----------------------------------------------------
-- table `bookcomment`.`owner`
-- -----------------------------------------------------
drop table if exists `bookcomment`.`owner`;
create table if not exists `bookcomment`.`owner` (
  `ownerid` int(11) not null auto_increment,
  `ownername` varchar(200) not null,
  `ownermail` varchar(200) not null,
  `ownertwitter` varchar(50) null default null,
  `ownerfacebook` varchar(50) null default null,
  `getmailflg` char(1) not null default '0',
  `regdate` timestamp not null default current_timestamp,
  `regstaffno` varchar(4) not null,
  `modifydate` timestamp not null default current_timestamp on update current_timestamp,
  `modifystaffno` varchar(4) not null,
  primary key (`ownerid`))
engine = innodb
default character set = utf8;


-- -----------------------------------------------------
-- table `bookcomment`.`comment`
-- -----------------------------------------------------
drop table if exists `bookcomment`.`comment`;
create table if not exists `bookcomment`.`comment` (
  `commentid` int(11) not null auto_increment,
  `bookid` varchar(13) not null,
  `isbn` varchar(13) null default null,
  `ownerid` int(11) null default null,
  `name` varchar(200) null default null,
  `twitter` varchar(50) null default null,
  `facebook` varchar(50) null default null,
  `comment` text null default null,
  `commentdate` datetime null default null,
  `sendmailflg` char(1) not null default '0',
  `regdate` timestamp not null default current_timestamp,
  `regstaffno` varchar(4) not null,
  `modifydate` timestamp not null default current_timestamp on update current_timestamp,
  `modifystaffno` varchar(4) not null,
  primary key (`commentid`))
engine = innodb
default character set = utf8;



