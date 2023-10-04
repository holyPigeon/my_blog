drop table if exists post_likes;
drop table if exists comments;
drop table if exists posts;
drop table if exists users;

create table users
(
    user_id  bigint auto_increment comment '유저 ID'
        primary key,
    login_id varchar(50) not null unique comment '로그인 ID',
    name     varchar(30) not null comment '유저 이름',
    nickname varchar(30) not null unique comment '유저 닉네임',
    password varchar(100) not null comment '로그인 PASSWORD'
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table posts
(
    post_id            bigint auto_increment comment '게시글 ID'
        primary key,
    created_by         varchar(100) not null comment '게시글 작성자',
    last_modified_by   varchar(100) not null comment '게시글 수정자',
    created_date       datetime(6)  not null comment '게시글 생성일',
    last_modified_date datetime(6)  not null comment '게시글 수정일',
    content            longtext     not null comment '게시글 내용',
    title              longtext     not null comment '게시글 제목',
    user_id            bigint       not null comment '유저 ID',

    constraint FK5lidm6cqbc7u4xhqpxm898qme
        foreign key (user_id) references users (user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table comments
(
    comment_id         bigint auto_increment comment '댓글 ID'
        primary key,
    created_by         varchar(100) not null comment '댓글 작성자',
    last_modified_by   varchar(100) not null comment '댓글 수정자',
    created_date       datetime(6)  not null comment '댓글 생성일',
    last_modified_date datetime(6)  not null comment '댓글 수정일',
    content            longtext     not null comment '댓글 내용',
    parent_id          bigint                comment '부모 댓글 ID',
    post_id            bigint       not null comment '게시글 ID',
    user_id            bigint       not null comment '유저 ID',

    constraint FK8omq0tc18jd43bu5tjh6jvraq
        foreign key (user_id) references users (user_id),
    constraint FKh4c7lvsc298whoyd4w9ta25cr
        foreign key (post_id) references posts (post_id),
    constraint FKlri30okf66phtcgbe5pok7cc0
        foreign key (parent_id) references comments (comment_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table post_likes
(
    post_like_id bigint auto_increment comment '좋아요 ID'
        primary key,
    post_id      bigint not null comment '게시글 ID',
    user_id      bigint not null comment '유저 ID',

    constraint FKa5wxsgl4doibhbed9gm7ikie2
        foreign key (post_id) references posts (post_id),
    constraint FKkgau5n0nlewg6o9lr4yibqgxj
        foreign key (user_id) references users (user_id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;