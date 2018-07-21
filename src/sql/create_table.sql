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
    instance_cnt int DEFAULT 0 COMMENT '执行次数',
    type int default 1 comment '1 interval 2 cron',
    create_time timestamp DEFAULT current_timestamp NOT NULL,
    update_time timestamp DEFAULT current_timestamp NOT NULL
)
  comment '定时任务'
  engine = InnoDB default charset=utf8;
CREATE UNIQUE INDEX job_job_id_uindex ON job (job_id);

CREATE TABLE leetcode_info
(
  id                  BIGINT AUTO_INCREMENT
    PRIMARY KEY,
  username            VARCHAR(255)                           NOT NULL
  COMMENT '用户名',
  user_slag           VARCHAR(255)                           NULL
  COMMENT '用户别名(用于url)',
  password            VARCHAR(255)                           NOT NULL
  COMMENT '密码',
  real_name           VARCHAR(50)                            NULL
  COMMENT '姓名',
  avatar              VARCHAR(255)                           NULL
  COMMENT '头像url',
  location            VARCHAR(255)                           NULL
  COMMENT '地址',
  school              VARCHAR(50)                            NULL
  COMMENT '学校',
  finished_contests   INT                                    NULL
  COMMENT '完场比赛数',
  rating              INT                                    NULL
  COMMENT '排名',
  global_rank         VARCHAR(50)                            NULL
  COMMENT '总排名',
  solved_question     VARCHAR(50)                            NULL
  COMMENT '解决问题数',
  accepted_submission VARCHAR(50)                            NULL
  COMMENT '解决问题数',
  points              INT                                    NULL
  COMMENT '金币数',
  status              INT DEFAULT 1                          NOT NULL
  COMMENT '0不运行，1运行，-1删除',
  executed_times      INT DEFAULT 0                          NOT NULL
  COMMENT '签到次数',
  create_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP    NOT NULL,
  update_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP    NOT NULL,
  CONSTRAINT username
  UNIQUE (username)
) engine = InnoDB default charset=utf8;

create table `1point3acres_info`
(
  id             bigint auto_increment
    primary key,
  username       varchar(255) not null
  comment '用户名',
  password       varchar(255) not null
  comment '密码',
  real_name      varchar(50)  null
  comment '真实用户名',
  point          int          null
  comment '积分',
  status         int   default 1       not null
  comment '0不运行，1运行，-1删除',
  executed_times int   default 1      not null
  comment '签到次数',
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP      not null,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP      not null,
  constraint username
  unique (username)
) engine = InnoDB default charset=utf8;

create table `account`
(
  id             bigint auto_increment
    primary key,
  username       varchar(255) not null
  comment '用户名',
  password       varchar(255) not null
  comment '密码',
  real_name      varchar(50)  not null
  comment '真实用户名',
  token         varchar(255)       null,
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP      not null,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP      not null,
  constraint username
  unique (username)
) engine = InnoDB default charset=utf8;


create table log
(
  id          bigint auto_increment
    primary key,
  pid         varchar(50)                         not null
  comment '项目id',
  content     text                                null,
  level       int default '0'                     not null
  comment '日志级别，0info 1warning 2error 3fatal',
  err_code    int                                 not null,
  datetime    date                    not null,
  create_time timestamp default CURRENT_TIMESTAMP not null,
  update_time timestamp default CURRENT_TIMESTAMP not null
) engine = InnoDB default charset=utf8;