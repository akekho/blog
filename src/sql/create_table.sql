create table article
(
  id          int auto_increment
    primary key,
  title       varchar(50)                         not null
  comment '文章标题',
  content     text                                null
  comment '文章内容, html',
  content_md  text                                null
  comment '文章内容，markdown',
  category_id int default 0                           null,
  preface     text,
  status      int default 0                           null
  comment '0=草稿，文章没上线到主站；1=上线；2=删除',
  pv          int default 0                           null,
  create_time timestamp default CURRENT_TIMESTAMP not null,
  update_time timestamp default CURRENT_TIMESTAMP not null,
  constraint article_title_uindex
  unique (title)
)
  comment '文章'
  engine = InnoDB default charset=utf8;

CREATE TABLE category
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30) NOT NULL,
    deleted int DEFAULT 0     COMMENT '是否删除 0删除，1未删除',
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
) engine = InnoDB default charset=utf8;
CREATE UNIQUE INDEX category_name_uindex ON category (name);

CREATE TABLE image
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(256) NOT NULL,
    url varchar(256) NOT NULL,
    create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)engine = InnoDB default charset=utf8;