create database BookManager;

use BookManager;

create table category
(
    `id`         int AUTO_INCREMENT,
    `createTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updateTime` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `sort`       int(1)      NOT NULL DEFAULT 0,
    `status`     tinyint(1)  NOT NULL DEFAULT 1,
    `name`       varchar(20) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

insert into category(`name`, `sort`, `status`) value ('数学', 0, 1)
