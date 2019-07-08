/*
navicat mysql data transfer

source server         : 121.41.119.158
source server version : 50622
source host           : 121.41.119.158:3306
source database       : dmc

target server type    : mysql
target server version : 50622
file encoding         : 65001

date: 2016-12-27 22:23:47
*/

set foreign_key_checks=0;

DROP DATABASE IF EXISTS bmall;
CREATE DATABASE bmall;
USE bmall;

-- ----------------------------
-- table structure for resource
-- ----------------------------
drop table if exists `resource`;
create table `resource` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `url` varchar(200) default null,
  `method` varchar(20) default null,
  `pid` bigint(36) default null,
  `type` int(11) not null,
  primary key (`id`),
  key `fk_sogl6f9lioeptbf7s105wbx82` (`pid`),
  key `fk_bjlrqegc9iu81src5vlta7p00` (`type`),
  constraint `resource_ibfk_1` foreign key (`pid`) references `resource` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of resource
-- ----------------------------
insert into `resource` values ('0', '系统管理', null, '0', null, null, null, '0');
insert into `resource` values ('1', '角色管理', '角色列表', '2', '../rolelist/rolelist.html', null, '0', '0');
insert into `resource` values ('2', '用户管理', '用户列表', '3', '../userlist/userlist.html', null, '0', '0');
insert into `resource` values ('3', '资源管理', '管理系统中所有的菜单或功能', '1', '../menu/menu.html', null, '0', '0');
insert into `resource` values ('4', '添加角色', null, '3', '/role', 'post', '1', '1');
insert into `resource` values ('5', '删除角色', null, '6', '/role/*', 'delete', '1', '1');
insert into `resource` values ('6', '编辑角色', null, '5', '/role', 'put', '1', '1');
insert into `resource` values ('7', '角色授权', null, '8', '/role/grant', 'post', '1', '1');
insert into `resource` values ('9', '添加用户', null, '3', '/user', 'post', '2', '1');
insert into `resource` values ('12', '删除用户', null, '6', '/user/*', 'delete', '2', '1');
insert into `resource` values ('13', '编辑用户', null, '5', '/user', 'put', '2', '1');
insert into `resource` values ('14', '用户修改密码', null, '11', '/user/editpwd', 'post', '2', '1');
insert into `resource` values ('15', '用户授权', null, '9', '/user/grant', 'post', '2', '1');
insert into `resource` values ('16', '添加资源', null, '4', '/resource', 'post', '3', '1');
insert into `resource` values ('17', '删除资源', null, '7', '/resource/*', 'delete', '3', '1');
insert into `resource` values ('18', '编辑资源', null, '6', '/resource', 'put', '3', '1');
insert into `resource` values ('19', '资源树列表', null, '2', '/resource/tree', 'post', '3', '1');
insert into `resource` values ('3907913782690816', '角色详情', null, null, '/role/*', 'get', '1', '1');

ROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` bigint(36) NOT NULL,
  `pid` bigint(36) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `seq` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, NULL, 'test', 1, 'test');

-- ----------------------------
-- Table structure for category_commodity
-- ----------------------------
DROP TABLE IF EXISTS `category_commodity`;
CREATE TABLE `category_commodity`  (
  `categoryId` bigint(36) NOT NULL,
  `commodityId` bigint(36) NULL DEFAULT NULL,
  PRIMARY KEY (`categoryId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity`  (
  `id` bigint(36) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `descreption` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `exist` int(11) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10375 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES (1, 'KumiKiwa卡米女鞋新款酒红色真皮头层牛皮浅口性感尖头细跟高跟鞋工作鞋女鞋 酒红色（牛皮8.5cm） 36', 298, 'KumiKiwa卡米女鞋新款酒红色真皮头层牛皮浅口性感尖头细跟高跟鞋工作鞋女鞋 酒红色（牛皮8.5cm） 36', 1);
INSERT INTO `commodity` VALUES (2, '邻家天使凉鞋女2019夏季新款女韩版细跟尖头单鞋包头高跟浅口大码女鞋 L6022白色 37', 165, '邻家天使凉鞋女2019夏季新款女韩版细跟尖头单鞋包头高跟浅口大码女鞋 L6022白色 37', 1);
INSERT INTO `commodity` VALUES (3, '慕澜格调高跟鞋女2019新款仙女风尖头OL细跟工作鞋女百搭婚礼伴娘鞋尖头时尚大码单鞋女 红色 37', 128, '慕澜格调高跟鞋女2019新款仙女风尖头OL细跟工作鞋女百搭婚礼伴娘鞋尖头时尚大码单鞋女 红色 37', 1);
INSERT INTO `commodity` VALUES (4, '享利运 高跟鞋女 2019新款职业细跟单鞋女百搭休闲时尚一字扣带包头凉鞋 黑色 37', 98, '享利运 高跟鞋女 2019新款职业细跟单鞋女百搭休闲时尚一字扣带包头凉鞋 黑色 37', 1);
INSERT INTO `commodity` VALUES (5, '享利运 高跟鞋女 2019新款韩版尖头单鞋女鞋绒面一字扣猫跟百搭办公室通勤上班时尚高跟凉鞋女 黑色 35', 89, '享利运 高跟鞋女 2019新款韩版尖头单鞋女鞋绒面一字扣猫跟百搭办公室通勤上班时尚高跟凉鞋女 黑色 35', 1);
INSERT INTO `commodity` VALUES (6, '享利运 高跟鞋女 2019新款欧美时尚一字扣酒杯跟女鞋性感百搭高跟婚鞋单鞋女 黑色（8.5CM） 36', 98, '享利运 高跟鞋女 2019新款欧美时尚一字扣酒杯跟女鞋性感百搭高跟婚鞋单鞋女 黑色（8.5CM） 36', 1);
INSERT INTO `commodity` VALUES (7, '【京东优品】欧美春夏新款尖头12cm高跟鞋女细跟黑色一字扣带性感浅口大码凉鞋 黑色8cm 43', 369, '【京东优品】欧美春夏新款尖头12cm高跟鞋女细跟黑色一字扣带性感浅口大码凉鞋 黑色8cm 43', 1);
INSERT INTO `commodity` VALUES (8, '图亚格 2019新款高跟鞋女 细跟韩版休闲百搭一字带高跟夜店性感OL职业工作凉单鞋女 黑色9cm 36标码', 168, '图亚格 2019新款高跟鞋女 细跟韩版休闲百搭一字带高跟夜店性感OL职业工作凉单鞋女 黑色9cm 36标码', 1);
INSERT INTO `commodity` VALUES (9, '享利运 高跟鞋2019夏季新款韩版时尚简约细跟高跟绸缎面浅口尖头镂空性感百搭网红单鞋一字扣带凉鞋 黑色 37', 89, '享利运 高跟鞋2019夏季新款韩版时尚简约细跟高跟绸缎面浅口尖头镂空性感百搭网红单鞋一字扣带凉鞋 黑色 37', 1);
INSERT INTO `commodity` VALUES (10, '鲨之魅2019新款高跟鞋女浅口细跟尖头晚宴婚鞋高跟OL职业工作鞋优雅大小码礼服女单鞋 粉色 38', 148, '鲨之魅2019新款高跟鞋女浅口细跟尖头晚宴婚鞋高跟OL职业工作鞋优雅大小码礼服女单鞋 粉色 38', 1);
INSERT INTO `commodity` VALUES (11, '享利运 高跟鞋女 2019夏季新款亮片新款尖头一字扣带凉鞋女细跟仙女风单鞋 黑色 35', 98, '享利运 高跟鞋女 2019夏季新款亮片新款尖头一字扣带凉鞋女细跟仙女风单鞋 黑色 35', 1);
INSERT INTO `commodity` VALUES (12, '帛屐高跟鞋女细跟高跟婚礼伴娘鞋潮尖头浅口单鞋婚鞋 银色 37', 139, '帛屐高跟鞋女细跟高跟婚礼伴娘鞋潮尖头浅口单鞋婚鞋 银色 37', 1);
INSERT INTO `commodity` VALUES (13, '彩姿妮（CAIZINI）高跟鞋女2019新款细跟尖头单鞋浅口百搭黑色绒面职业ol工作鞋女鞋 黑色(7厘米) 37', 128, '彩姿妮（CAIZINI）高跟鞋女2019新款细跟尖头单鞋浅口百搭黑色绒面职业ol工作鞋女鞋 黑色(7厘米) 37', 1);
INSERT INTO `commodity` VALUES (14, '足迹缘2019春夏新款水钻高跟鞋女职场OL工作鞋超细高跟尖头细浅口单鞋女大小码宴会婚礼伴娘鞋 银色 38', 138, '足迹缘2019春夏新款水钻高跟鞋女职场OL工作鞋超细高跟尖头细浅口单鞋女大小码宴会婚礼伴娘鞋 银色 38', 1);
INSERT INTO `commodity` VALUES (15, 'Atiniya 高跟鞋 2019夏季新款浅口尖头时尚细跟亮片高跟鞋百搭银色带子包头女凉鞋 金色（9CM） 34（脚肥脚宽建议拍大一码）', 138, 'Atiniya 高跟鞋 2019夏季新款浅口尖头时尚细跟亮片高跟鞋百搭银色带子包头女凉鞋 金色（9CM） 34（脚肥脚宽建议拍大一码）', 1);
INSERT INTO `commodity` VALUES (16, 'FRT  高跟鞋2019春夏新款凉鞋女鞋欧美时尚OL尖头超高跟细跟防水台职业工作单鞋 杏色7厘米 37标准码', 168, 'FRT  高跟鞋2019春夏新款凉鞋女鞋欧美时尚OL尖头超高跟细跟防水台职业工作单鞋 杏色7厘米 37标准码', 1);
INSERT INTO `commodity` VALUES (17, '宝蓝骏 春秋 新款高跟鞋 细跟女性感夜店浅口尖头高跟鞋OL职业纯色简约工作单鞋 卡其色标码 36', 168, '宝蓝骏 春秋 新款高跟鞋 细跟女性感夜店浅口尖头高跟鞋OL职业纯色简约工作单鞋 卡其色标码 36', 1);
INSERT INTO `commodity` VALUES (18, '枫泠 高跟鞋女2019夏季新款时尚凉鞋女性感交叉绑带通勤上班职业增高网红ins防水台单鞋女 黑色 34', 89, '枫泠 高跟鞋女2019夏季新款时尚凉鞋女性感交叉绑带通勤上班职业增高网红ins防水台单鞋女 黑色 34', 1);
INSERT INTO `commodity` VALUES (19, '金利来（goldlion）女士尖头细高跟时尚拼色脚环绑带凉鞋61291004221P-米白-36码', 439, '金利来（goldlion）女士尖头细高跟时尚拼色脚环绑带凉鞋61291004221P-米白-36码', 1);
INSERT INTO `commodity` VALUES (20, '貂绅2019大码高跟鞋女细跟婚鞋女春夏结婚水晶鞋新娘婚纱伴娘礼服宴会单鞋 银色 37', 138, '貂绅2019大码高跟鞋女细跟婚鞋女春夏结婚水晶鞋新娘婚纱伴娘礼服宴会单鞋 银色 37', 1);
INSERT INTO `commodity` VALUES (21, '多米卡布2019新款水晶婚鞋网红法式高跟鞋女性感细跟婚纱伴娘鞋尖头大码水钻单鞋女 黑色 38', 138, '多米卡布2019新款水晶婚鞋网红法式高跟鞋女性感细跟婚纱伴娘鞋尖头大码水钻单鞋女 黑色 38', 1);
INSERT INTO `commodity` VALUES (22, '足迹缘2019春夏新款高跟鞋粗跟一字扣单鞋女防水台大小码工作鞋甜美女鞋 黑色 37', 129, '足迹缘2019春夏新款高跟鞋粗跟一字扣单鞋女防水台大小码工作鞋甜美女鞋 黑色 37', 1);
INSERT INTO `commodity` VALUES (23, '爵士迹尘新款尖头高跟鞋女细跟单鞋尖头婚礼伴娘鞋OL工作鞋 红色 37', 138, '爵士迹尘新款尖头高跟鞋女细跟单鞋尖头婚礼伴娘鞋OL工作鞋 红色 37', 1);
-- ----------------------------
-- table structure for role
-- ----------------------------
drop table if exists `role`;
create table `role` (
  `id` bigint(36) not null,
  `name` varchar(100) not null,
  `remark` varchar(200) default null,
  `seq` int(11) default null,
  `pid` bigint(36) default null,
  primary key (`id`),
  key `fk_tealaj0x99w9xj8on8ax0jgjb` (`pid`),
  constraint `role_ibfk_1` foreign key (`pid`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of role
-- ----------------------------
insert into `role` values ('0', '超管', '超级管理员角色，拥有系统中所有的资源访问权限', '0', null);

-- ----------------------------
-- table structure for role_resource
-- ----------------------------
drop table if exists `role_resource`;
create table `role_resource` (
  `role_id` bigint(36) not null,
  `resource_id` bigint(36) not null,
  primary key (`resource_id`,`role_id`),
  key `resource_id` (`resource_id`),
  key `role_resource_ibfk_1` (`role_id`),
  constraint `role_resource_ibfk_1` foreign key (`role_id`) references `role` (`id`) on delete cascade,
  constraint `role_resource_ibfk_2` foreign key (`resource_id`) references `resource` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of role_resource
-- ----------------------------
insert into `role_resource` values ('0', '0');
insert into `role_resource` values ('0', '1');
insert into `role_resource` values ('0', '2');
insert into `role_resource` values ('0', '3');
insert into `role_resource` values ('0', '4');
insert into `role_resource` values ('0', '5');
insert into `role_resource` values ('0', '6');
insert into `role_resource` values ('0', '7');
insert into `role_resource` values ('0', '9');
insert into `role_resource` values ('0', '12');
insert into `role_resource` values ('0', '13');
insert into `role_resource` values ('0', '14');
insert into `role_resource` values ('0', '15');
insert into `role_resource` values ('0', '16');
insert into `role_resource` values ('0', '17');
insert into `role_resource` values ('0', '18');
insert into `role_resource` values ('0', '19');
insert into `role_resource` values ('0', '3907913782690816');

-- ----------------------------
-- table structure for user
-- ----------------------------
drop table if exists `user`;
create table `user` (
  `id` bigint(36) not null,
  `create_time` datetime default current_timestamp,
  `modify_time` datetime default current_timestamp,
  `name` varchar(100) not null,
  `password` varchar(100) not null,
  `username` varchar(100) not null,
  primary key (`id`),
  unique key `uk_o3uyea7py4jnln0qxrtg1qqhq` (`username`) using btree
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of user
-- ----------------------------
insert into `user` values ('0', '2015-05-02 17:50:05', '2016-12-27 21:58:01', '管理员', '96e79218965eb72c92a549dd5a330112', 'admin');
insert into `user` values ('1', '2015-05-02 17:50:06', '2016-12-27 18:34:10', 'admin1', '202cb962ac59075b964b07152d234b70', 'admin1');

-- ----------------------------
-- table structure for user_role
-- ----------------------------
drop table if exists `user_role`;
create table `user_role` (
  `user_id` bigint(20) not null,
  `role_id` bigint(20) not null,
  primary key (`user_id`,`role_id`),
  key `role_id` (`role_id`),
  key `role_id_2` (`role_id`),
  key `role_id_3` (`role_id`),
  constraint `user_role_ibfk_1` foreign key (`user_id`) references `user` (`id`) on delete cascade,
  constraint `user_role_ibfk_2` foreign key (`role_id`) references `role` (`id`) on delete cascade
) engine=innodb default charset=utf8mb4;

-- ----------------------------
-- records of user_role
-- ----------------------------
insert into `user_role` values ('0', '0');
