
--mysql -uroot -p123456789 链接数据库

CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `user_id` VARCHAR(64) NOT NULL,
   `login_name` VARCHAR(128) NOT NULL,
   `password` VARCHAR(128) NOT NULL,
   `name` VARCHAR(128) NOT NULL,
   `create_time` TIMESTAMP,
   PRIMARY KEY ( `id` ),
   UNIQUE KEY `uk_user_id`( `user_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user (user_id,login_name,password,name,create_time) values ('202101011212121000000','wangnaijiang','123456','wangnaijiang',now());
INSERT INTO user (user_id,login_name,password,name,create_time) values ('202101011212122000000','wnj','123456','wnj',now());
INSERT INTO user (user_id,login_name,password,name,create_time) values ('202101011212123000000','river','123456','river',now());

-- 分库分表如何拦截？