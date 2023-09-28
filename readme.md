### 模块说明
tulingmall-admin 后台管理程序  
tulingmall-authcenter 认证中心程序  
tulingmall-canal 数据同步程序  
tulingmall-cart 购物车程序  
tulingmall-common 通用模块，被其他程序以jar包形式使用 
tulingmall-core 主要包含model的声明，被其他程序以jar包形式使用 
tulingmall-gateway 网关程序  
tulingmall-member 用户管理程序  
tulingmall-order-curr 订单程序  
tulingmall-order-history 历史订单处理程序
tulingmall-portal 商城首页入口程序  
tulingmall-product 商品管理程序  
tulingmall-promotion 促销管理程序
tulingmall-redis-comm 缓存模块，被其他程序以jar包形式使用
tulingmall-redis-multi 多源缓存模块，被其他程序以jar包形式使用
tulingmall-search 商品搜索程序  
tulingmall-security 安全模块，被其他程序以jar包形式使用  
tulingmall-sk-cart 秒杀确认单处理
tulingmall-sk-order 秒杀订单处理
tulingmall-unqid 分布式ID生成程序  
### 关键服务建议启动顺序
tulingmall-unqid、tulingmall-member、tulingmall-product、tulingmall-cart
tulingmall-promotion、tulingmall-authcenter
tulingmall-order-curr、tulingmall-portal、tulingmall-gateway
### doc目录说明
htmljss 秒杀静态网页、JS文件、CSS文件等
nginx 秒杀nginx配置、Lua脚本、第三方Lua库
### 前台地址
http://mallv5.v2.idcfengye.com/mallv5-front/
注意下单支付的二维码需要沙箱环境的支付宝才能扫描
### 后台地址
http://mallv5.v2.idcfengye.com/mallv5-admin/
用户名：admin 密码：admin
### 系统整体架构图
见项目路径下'mallv5架构图.jpg'
### 基础设施层介绍
https://www.processon.com/mindmap/6511b14eb67650768b6fb521
### 微服务组件介绍
https://www.processon.com/mindmap/6511b18b85561b13415b62e3
### 前端效果展示说明
https://www.processon.com/mindmap/651511b467c1a752f0bf9175