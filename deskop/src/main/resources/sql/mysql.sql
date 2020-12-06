DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `teacher_no`   VARCHAR(20)  NOT NULL COMMENT '账号',
    `password`     VARCHAR(20)  NOT NULL COMMENT '密码',
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `college`      VARCHAR(40)  NOT NULL COMMENT '学院',
    `mobile`       CHAR(11)     NOT NULL COMMENT '手机',
    `email`        VARCHAR(20)  NOT NULL COMMENT '邮箱',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='教师表';

DROP TABLE IF EXISTS `exam`;
CREATE TABLE `exam`
(
    `id`                 BIGINT UNSIGNED AUTO_INCREMENT,
    `course_id`          BIGINT       NOT NULL COMMENT '课程ID',
    `teacher_id`         BIGINT       NOT NULL COMMENT '教师ID',
    `software_config_id` BIGINT       NOT NULL COMMENT '软件白名单配置ID',
    `name`               VARCHAR(100) NOT NULL COMMENT '考试名',
    `start_time`         datetime     NOT NULL COMMENT '开始时间',
    `end_time`           datetime     NOT NULL COMMENT '结束时间',
    `exam_time`          smallint     NOT NULL COMMENT '考试时长',
    `gmt_create`         datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified`       datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='考试表';

DROP TABLE IF EXISTS `software_config`;
CREATE TABLE `software_config`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `teacher_id`   BIGINT       NOT NULL COMMENT '教师ID',
    `name`         VARCHAR(100) NOT NULL COMMENT '配置名',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件白名单配置表';

DROP TABLE IF EXISTS `select_software`;
CREATE TABLE `select_software`
(
    `id`                 BIGINT UNSIGNED AUTO_INCREMENT,
    `software_config_id` BIGINT   NOT NULL COMMENT '软件白名单配置ID',
    `software_id`        BIGINT   NOT NULL COMMENT '软件ID',
    `gmt_create`         datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified`       datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件配置表';

DROP TABLE IF EXISTS `software`;
CREATE TABLE `software`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `name`         VARCHAR(100) NOT NULL COMMENT '软件名',
    `path`         VARCHAR(100) NOT NULL COMMENT '本地安装路径',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件表';

DROP TABLE IF EXISTS `exam_arrangement`;
CREATE TABLE `exam_arrangement`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `exam_id`      BIGINT   NOT NULL COMMENT '考试ID',
    `student_id`   BIGINT   NOT NULL COMMENT '学生ID',
    `gmt_create`   datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='考试安排表';

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `student_no`   VARCHAR(20)  NOT NULL COMMENT '学号',
    `password`     VARCHAR(20)  NOT NULL COMMENT '密码',
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `college`      VARCHAR(40)  NOT NULL COMMENT '学院',
    `major`        VARCHAR(40)  NOT NULL COMMENT '专业',
    `mobile`       CHAR(11)     NOT NULL COMMENT '手机',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='学生表';

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `teacher_id`   BIGINT       NOT NULL COMMENT '教师ID',
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程表';

DROP TABLE IF EXISTS `select_course`;
CREATE TABLE `select_course`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `course_id`    BIGINT   NOT NULL COMMENT '教师ID',
    `student_id`   BIGINT   NOT NULL COMMENT '学生ID',
    `gmt_create`   datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='选课表';