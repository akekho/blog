create table article
(
  id          int auto_increment primary key,
  title       varchar(50)                        not null
  comment '文章标题',
  content     text                                null
  comment '文章内容, html',
  content_md  text                                null
  comment '文章内容，markdown',
  category_id int default 0                       not null,
  preface     text,
  status      int default 0                       not null
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
  url text NOT NULL COMMENT '原图url',
  thumb_url text NOT NULL COMMENT '压缩缩略图url 5-10k',
  slim_url text NOT NULL COMMENT '压缩缩略图url 0-500k',
  create_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  update_time timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
)engine = InnoDB default charset=utf8;

create table doc_template
(
  id          int auto_increment
    primary key,
  title       varchar(100)                        not null,
  image_id    int default '0'                     not null,
  content     text                                null,
  use_time    int default '0'                     null,
  status      int default '0'                     not null
  comment '0下线审核, 1上线, 2已删除',
  create_time timestamp default CURRENT_TIMESTAMP not null,
  update_time timestamp default CURRENT_TIMESTAMP not null
)
  comment '文档模板表'
  engine = InnoDB default charset=utf8;

CREATE TABLE job
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL COMMENT '任务名称',
    job_id varchar(50) NOT NULL COMMENT '程序指定的任务id，唯一',
    status int DEFAULT 0 NOT NULL COMMENT '-1删除，0停止，1执行中，2暂停',
    executed_times bigint DEFAULT 0 NOT NULL COMMENT '执行次数',
    cron varchar(255) DEFAULT '' COMMENT '执行策略',
    start_date timestamp DEFAULT '2000-01-01 00:00:00' COMMENT '开始时间，空则立刻开始',
    end_date timestamp DEFAULT '2000-01-01 00:00:00' COMMENT '结束时间，空则一直运行',
    create_time timestamp DEFAULT current_timestamp NOT NULL,
    update_time timestamp DEFAULT current_timestamp NOT NULL
)
  comment '定时任务'
  engine = InnoDB default charset=utf8;;
CREATE UNIQUE INDEX job_job_id_uindex ON job (job_id);
