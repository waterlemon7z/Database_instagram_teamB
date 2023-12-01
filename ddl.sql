create table if not exists user
(
    id      int auto_increment,
    user_id varchar(20) not null,
    pw      varchar(20) not null,
    primary key (id, user_id)
);

create table if not exists article
(
    article_id int auto_increment
        primary key,
    id         int          not null,
    content    varchar(100) not null,
    date       datetime     not null,
    constraint article_ibfk_1
        foreign key (id) references user (id)
);

create index id
    on article (id);

create table if not exists article_hashtag
(
    article_id int         not null,
    hashtag    varchar(20) not null,
    primary key (article_id, hashtag),
    constraint article_hashtag_ibfk_1
        foreign key (article_id) references article (article_id)
);

create table if not exists article_image
(
    article_id int          not null,
    image      varchar(500) not null,
    primary key (article_id, image),
    constraint article_image_ibfk_1
        foreign key (article_id) references article (article_id)
);

create table if not exists article_likes
(
    article_id int not null,
    id         int not null,
    primary key (article_id, id),
    constraint article_likes_ibfk_1
        foreign key (article_id) references article (article_id),
    constraint article_likes_ibfk_2
        foreign key (id) references user (id)
);

create table if not exists comment
(
    comment_id int auto_increment
        primary key,
    id         int          not null,
    text       varchar(100) not null,
    article_id int          not null,
    date       datetime     null,
    constraint comment_ibfk_1
        foreign key (article_id) references article (article_id),
    constraint comment_ibfk_2
        foreign key (id) references user (id)
);

create table if not exists article_comment
(
    article_id int not null,
    comment_id int not null,
    primary key (article_id, comment_id),
    constraint article_comment_ibfk_1
        foreign key (article_id) references article (article_id),
    constraint article_comment_ibfk_2
        foreign key (comment_id) references comment (comment_id)
);

create index comment_id
    on article_comment (comment_id);

create index article_id
    on comment (article_id);

create index id
    on comment (id);

create table if not exists comment_likes
(
    comment_id int not null,
    id         int not null,
    primary key (comment_id, id),
    constraint comment_likes_ibfk_1
        foreign key (comment_id) references comment (comment_id),
    constraint comment_likes_ibfk_2
        foreign key (id) references user (id)
);

create index id
    on comment_likes (id);

create table if not exists comment_tags
(
    comment_id int          not null,
    tag        varchar(100) not null,
    id         int          null,
    primary key (comment_id, tag),
    constraint comment_tags_ibfk_1
        foreign key (comment_id) references comment (comment_id),
    constraint comment_tags_ibfk_2
        foreign key (id) references user (id)
);

create index id
    on comment_tags (id);

create table if not exists follow
(
    follow_id   int not null,
    followee_id int not null,
    primary key (followee_id, follow_id),
    constraint follow_ibfk_1
        foreign key (follow_id) references user (id),
    constraint follow_ibfk_2
        foreign key (followee_id) references user (id)
);

create index follow_id
    on follow (follow_id);

create table if not exists user_info
(
    id            int           not null
        primary key,
    profile_image varchar(1000) null,
    intro         varchar(30)   null,
    job           varchar(30)   null,
    email         varchar(30)   null,
    mobile        varchar(30)   null,
    constraint user_info_ibfk_1
        foreign key (id) references user (id)
);

create or replace definer = root@localhost view articlefull as
select `instagram`.`article`.`article_id`      AS `article_id`,
       `instagram`.`article`.`content`         AS `content`,
       `instagram`.`article`.`date`            AS `date`,
       `instagram`.`article_hashtag`.`hashtag` AS `hashtag`,
       `instagram`.`article_image`.`image`     AS `image`
from (`instagram`.`article` left join (`instagram`.`article_hashtag` left join (`instagram`.`article_image` left join `instagram`.`article_likes`
                                                                                on ((`instagram`.`article_image`.`article_id` =
                                                                                     `instagram`.`article_likes`.`article_id`)))
                                       on ((`instagram`.`article_hashtag`.`article_id` =
                                            `instagram`.`article_image`.`article_id`)))
      on ((`instagram`.`article`.`article_id` = `instagram`.`article_hashtag`.`article_id`)));

