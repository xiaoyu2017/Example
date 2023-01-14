create database BookManager;

use BookManager;

create table attachment
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)             NOT NULL DEFAULT 0,
    `status`     tinyint(1)         NOT NULL DEFAULT 1,
    `aid`        varchar(50) UNIQUE NOT NULL,
    `type`       tinyint(1)         NOT NULL,
    `name`       varchar(500)        NOT NULL,
    `size`       double             NOT NULL,
    `sizeUnit`   varchar(5)         NOT NULL,
    `extension`  varchar(5)         NOT NULL,
    `filePath`   varchar(200)       NOT NULL,
    `dynamicId`  varchar(50),
    `parent`     int,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table category
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)             NOT NULL DEFAULT 0,
    `status`     tinyint(1)         NOT NULL DEFAULT 1,
    `name`       varchar(20) UNIQUE NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table tag
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)      NOT NULL DEFAULT 0,
    `status`     tinyint(1)  NOT NULL DEFAULT 1,
    `name`       varchar(20) NOT NULL,
    `type`       tinyint(1)  NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table ebook
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)       NOT NULL DEFAULT 0,
    `status`     tinyint(1)   NOT NULL DEFAULT 1,
    `book_name`  varchar(100) NOT NULL,
    `edition`    varchar(10)  NOT NULL,
    `year`       varchar(10)  NOT NULL,
    `language`   varchar(10)  NOT NULL,
    `pages`      varchar(10),
    `bookmark`   tinyint(1)            DEFAULT 0,
    `summary`    varchar(1000),
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table ebook_tag
(
    `id`   int AUTO_INCREMENT,
    `createTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)       NOT NULL DEFAULT 0,
    `status`     tinyint(1)   NOT NULL DEFAULT 1,
    `eid`  int        NOT NULL,
    `tid`  int        NOT NULL,
    `type` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table ebook_category
(
    `id`  int AUTO_INCREMENT,
    `eid` int,
    `cid` int,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table if not exists user
(
    `id`           int AUTO_INCREMENT,
    `createTime`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime`   timestamp           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`         int(1)              NOT NULL DEFAULT 0,
    `status`       tinyint(1)          NOT NULL DEFAULT 1,
    `nick_name`    varchar(20) unicode NOT NULL,
    `name`         varchar(20) unicode NOT NULL,
    `password`     varchar(16)         NOT NULL,
    `icon`         varchar(50),
    `email`        varchar(100),
    `company_name` varchar(100),
    `sex`          tinyint,
    `birthday`     timestamp,
    `code`         varchar(10),
    `role`         int,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

create table menu
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)             NOT NULL DEFAULT 0,
    `status`     tinyint(1)         NOT NULL DEFAULT 1,
    `name`       varchar(20) UNIQUE NOT NULL,
    `link`       varchar(100)       NOT NULL,
    `icon`       varchar(100),
    `parent`     int,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

/*慎用*/
delete from attachment where 1=1;
delete from category where 1=1;
delete from ebook where 1=1;
delete from ebook_category where 1=1;
delete from ebook_tag where 1=1;
delete from tag where 1=1;