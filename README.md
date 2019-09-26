# English Version
## Introduction
The overall template is from https://github.com/sail-y/spring-boot-admin, and I made some changes myself.

Simple background template framework with user management, menu management, merchandise management, merchandise category management, order management, merchant management and role management, and access control to button level.


Permission verification and session persistence with JWT+Spring Security. The project is based on Springboot + Mybatis + BootStrap + DataTables


## Related Framework
* [Spring boot] (http://projects.spring.io/spring-boot/)
* [Mybatis] (http://www.mybatis.org/mybatis-3/en/index.html)
* [druid](https://github.com/alibaba/druid)
* [lombok](https://projectlombok.org/)
* [backbone.js] (http://backbonejs.org/)
* [bootstrap](http://getbootstrap.com/)
* [datatables](https://datatables.net/)



## Deployment Run
Modify the database connection in `application-dev.yml`
Execute the `mysql -uroot -p database < bmall.sql` to import the database script.
Direct Run`DMCApplication` access after startup: http://localhost:10000/web/views/login/login.html

Administrator account: admin
Password: 111111
Other 10,000+ user account & password can be found in the database `bmall.sql`.


## Page display
![image](https://github.com/keleqnma/bmall/blob/master/pictures/Category_Managemnt.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/buyerStatics.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/commodity_management.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/function_index1.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/function_index2.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/home.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/orderStatisticsByDay.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/resource_managemnt.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/role_management.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/user_management.png)

# 中文版 Chinese Version

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




## 部署运行
修改application-dev.yml里的数据库连接
执行`mysql -uroot -p 数据库 < bmall.sql`导入数据库脚本。
直接Run`DMCApplication`启动后访问：http://localhost:10000/web/views/login/login.html

管理员帐号：admin            
密码：111111
其他一万个商家账户账户密码见数据库



## 页面展示
![image](https://github.com/keleqnma/bmall/blob/master/pictures/Category_Managemnt.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/buyerStatics.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/commodity_management.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/function_index1.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/function_index2.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/home.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/orderStatisticsByDay.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/resource_managemnt.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/role_management.png)
![image](https://github.com/keleqnma/bmall/blob/master/pictures/user_management.png)
