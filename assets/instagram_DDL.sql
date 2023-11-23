# create database Instagram;
# use Instagram;
create table user
(
    id      int         not null auto_increment,
    user_id varchar(20) not null,
    pw      varchar(20) not null,
    primary key (id, user_id)
);

create table article
(
    article_id int primary key auto_increment,
    id         int          not null,
    content    varchar(100) not null,
    date       DATE         not null,
    foreign key (id) references user (id)
);

create table article_image
(
    article_id int,
    image      varchar(500),
    primary key (article_id, image),
    foreign key (article_id) references article (article_id)
);
create table article_likes
(
    article_id int,
    id         int,
    primary key (article_id, id),
    foreign key (article_id) references article (article_id),
    foreign key (id) references user (id)
);
create table article_hashtag
(
    article_id int,
    hashtag    varchar(20),
    primary key (article_id, hashtag),
    foreign key (article_id) references article (article_id)
);
create table comment
(
    comment_id int primary key auto_increment,
    id         int          not null,
    text       varchar(100) not null,
    article_id int          not null,
    foreign key (article_id) references article (article_id),
    foreign key (id) references user (id)
);

create table comment_likes
(
    comment_id int,
    id         int,
    primary key (comment_id, id),
    foreign key (comment_id) references comment(comment_id),
    foreign key (id) references user (id)
);
create table comment_tags
(
    comment_id int,
    tag        varchar(100),
    primary key (comment_id, tag),
    foreign key (comment_id) references comment(comment_id)
);

create table follow
(
    follow_id   int not null,
    followee_id int not null,
    primary key (followee_id, follow_id),
    foreign key (follow_id) references user (id),
    foreign key (followee_id) references user (id)
);

create table user_info
(
    id            int primary key,
    profile_image varchar(1000),
    intro         varchar(30),
    job           varchar(30),
    email         varchar(30),
    mobile        varchar(30),
    foreign key (id) references user (id)
);

create table article_comment
(
    article_id int,
    comment_id int,
    primary key (article_id, comment_id),
    foreign key (article_id) references article (article_id),
    foreign key (comment_id) references comment (comment_id)
)