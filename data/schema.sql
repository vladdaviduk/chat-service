use chatty;

drop table if exists message;
drop table if exists chat;

create table if not exists chat (
  id int not null auto_increment,
  first_owner varchar(255) not null,
  second_owner varchar(255) not null,
  primary key (id)
);

create table if not exists message (
   id int not null auto_increment,
   chat_id int not null,
   owner varchar(255) not null,
   timestamp timestamp not null,
   content varchar(5000) not null,

   foreign key (chat_id) REFERENCES chat(id),
   primary key (id)
);