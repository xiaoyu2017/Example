create database BookManager;

use BookManager;

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

insert into category(`name`, `sort`, `status`) value ('数学', 0, 1)

create table if not exists menu
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)       NOT NULL DEFAULT 0,
    `status`     tinyint(1)   NOT NULL DEFAULT 1,
    `name`       varchar(20)  NOT NULL,
    `icon`       varchar(50),
    `link`       varchar(500) NOT NULL,
    `parent`     int          NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into menu(`name`, `icon`, `link`, `parent`, `sort`, `status`) value ('图书管理', null, 'http://localhost:8080/view/book', 0, 0, 1);

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

insert into user(`sort`, `status`, `nick_name`, `name`, `password`, `icon`, `email`, `company_name`, `sex`, `birthday`,
                 `code`, `role`) value ('0', 1, 'fish', 'fish', 'fish123', null, null, null, 18, null, null, 0);
