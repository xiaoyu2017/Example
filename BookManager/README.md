# JavaWeb练习项目，项目主要是实现电子书的管理。
---

![](https://img.shields.io/badge/Java-1.8-blue)
![](https://img.shields.io/badge/Maven-4.0.0-blue)
![](https://img.shields.io/badge/Jstl-1.2-blue)
![](https://img.shields.io/badge/fastjson2-2.0.21.graal-blue)
![](https://img.shields.io/badge/mybatis-3.5.11-blue)
![](https://img.shields.io/badge/mysql-8.0.27-blue)

# Mybatis

> 相关示例

## 1. 直接使用Mapper配置文件

1. mapper配置文件

   [CategoryMapper.xml](/src/main/resources/cn/fishland/bookmanager/mapper/CategoryMapper.xml)

2. 核心配置文件添加。

   [mybatis.xml](/src/main/resources/mybatis.xml)

3. 调用

   [CategoryService.java](/src/main/java/cn/fishland/bookmanager/service/CategoryService.java)

## 2. 代理接口使用

1. mapper配置文件

   [CategoryMapper.xml](/src/main/resources/cn/fishland/bookmanager/mapper/CategoryMapper.xml)

2. 核心配置文件添加。

   [EbookMapper.java](/src/main/java/cn/fishland/bookmanager/mapper/EbookMapper.java)
   [EbookMapper.xml](/src/main/resources/cn/fishland/bookmanager/mapper/EbookMapper.xml)

3. 调用

   [EbookService.java](/src/main/java/cn/fishland/bookmanager/service/EbookService.java)

## 3. 注解使用
1. 核心配置文件添加。

   [CategoryMapper.xml](/src/main/resources/cn/fishland/bookmanager/mapper/CategoryMapper.xml)

2. 创建接口

   [MenuMapper.java](/src/main/java/cn/fishland/bookmanager/mapper/MenuMapper.java)
