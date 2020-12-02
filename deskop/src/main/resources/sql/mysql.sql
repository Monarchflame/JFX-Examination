CREATE TABLE IF NOT EXISTS `teacher`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `college`      VARCHAR(40)  NOT NULL COMMENT '学院',
    `mobile`       CHAR(11)     NOT NULL COMMENT '手机',
    `email`        VARCHAR(20)  NOT NULL COMMENT '邮箱',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='教师表';

CREATE TABLE IF NOT EXISTS `exam`
(
    `id`                 BIGINT UNSIGNED AUTO_INCREMENT,
    `course_id`          BIGINT   NOT NULL COMMENT '课程ID',
    `teacher_id`         BIGINT   NOT NULL COMMENT '教师ID',
    `software_config_id` BIGINT   NOT NULL COMMENT '软件白名单配置ID',
    `start_time`         datetime NOT NULL COMMENT '开始时间',
    `gmt_create`         datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified`       datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='考试表';

CREATE TABLE IF NOT EXISTS `software_config`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `teacher_id`   BIGINT       NOT NULL COMMENT '教师ID',
    `name`         VARCHAR(100) NOT NULL COMMENT '配置名',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件白名单配置表';

CREATE TABLE IF NOT EXISTS `select_software`
(
    `id`                 BIGINT UNSIGNED AUTO_INCREMENT,
    `software_config_id` BIGINT   NOT NULL COMMENT '软件白名单配置ID',
    `software_id`        BIGINT   NOT NULL COMMENT '软件ID',
    `gmt_create`         datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified`       datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件配置表';

CREATE TABLE IF NOT EXISTS `software`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `name`         VARCHAR(100) NOT NULL COMMENT '软件名',
    `path`         VARCHAR(100) NOT NULL COMMENT '本地安装路径',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='软件表';

CREATE TABLE IF NOT EXISTS `exam_arrangement`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `exam_id`      BIGINT   NOT NULL COMMENT '考试ID',
    `student_id`   BIGINT   NOT NULL COMMENT '学生ID',
    `gmt_create`   datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='考试安排表';

CREATE TABLE IF NOT EXISTS `student`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `college`      VARCHAR(40)  NOT NULL COMMENT '学院',
    `major`        VARCHAR(40)  NOT NULL COMMENT '专业',
    `mobile`       CHAR(11)     NOT NULL COMMENT '手机',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='学生表';

CREATE TABLE IF NOT EXISTS `course`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `teacher_id`   BIGINT       NOT NULL COMMENT '教师ID',
    `name`         VARCHAR(100) NOT NULL COMMENT '姓名',
    `gmt_create`   datetime     NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='课程表';

CREATE TABLE IF NOT EXISTS `select_course`
(
    `id`           BIGINT UNSIGNED AUTO_INCREMENT,
    `course_id`    BIGINT   NOT NULL COMMENT '教师ID',
    `student_id`   BIGINT   NOT NULL COMMENT '学生ID',
    `gmt_create`   datetime NOT NULL DEFAULT current_timestamp,
    `gmt_modified` datetime NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='选课表';