## 简介
模板源于https://github.com/sail-y/spring-boot-admin, 自己做了一些改动

简单的后台模板框架，具备用户管理，菜单管理，商品管理，商品分类管理，订单管理，商家管理和角色管理, 权限控制到按钮层级。       


采用JWT+Spring Security进行权限验证和会话保持
项目基于Spring boot+Mybatis+BootStrap+DataTables


## 相关框架
* [Spring boot](http://projects.spring.io/spring-boot/)
* [Mybatis](http://www.mybatis.org/mybatis-3/zh/index.html)
* [druid](https://github.com/alibaba/druid)
* [lombok](https://projectlombok.org/)
* [backbone.js](http://backbonejs.org/)
* [bootstrap](http://getbootstrap.com/)
* [datatables](https://datatables.net/)




## 修改application-dev.yml里的数据库连接

执行`mysql -uroot -p 数据库 < bmall.sql`导入数据库脚本。

直接Run`DMCApplication`启动后访问：http://localhost:10000/web/views/login/login.html

帐号：admin            
密码：111111




## 页面展示

![](img/login.png)
![](img/page1.png)
![](img/page2.png)
