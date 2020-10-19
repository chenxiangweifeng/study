
### 静态文件的存在位置，/resources/static 只能叫这个文件夹，其他名称的都不行
### 用idea打开springboot项目时，最好点击open-然后点击pom文件以项目形式打开，避免出现项目找不到依赖库的情况。
很多的依赖类报红的情况
.gitignore只会忽略.gitignore编写之后的未跟踪的（untrack）文件，而在编写.gitignore之前已经add 和 commit的文件则不会被忽略


### git的使用
文件状态的初始状态为：untracked

###QuweyPlanCache内存泄漏解决办法
产生原因： hibernate中的QueryPlanCache会缓存SQL，已方便以后相同的SQL重复编译。如果in后的参数不同，hibernate会把其当成不同的sql
进行缓存，从而缓存大量的SQL导致heap内存溢出
解决方法：通过设置缓存最大值来进行限制，不设置默认是2048
spring:
  jpa:
    properties:
      hibernate:
        query:
          plan_cache_max_size: 64
          plan_parameter_metadata_max_size: 32
          plan_cache_max_soft_references: 1024
          plan_cache_max_strong_references: 64

application.yaml配置文件更改如下：
jpa:
    properties:
      hibernate:
        query:
          plan_cache_max_soft_references: 512   # The default soft reference count = 2048.
          plan_cache_max_strong_references: 64  # The default strong reference count = 128.
        hbm2ddl:
          auto: update
        temp:
          use_jdbc_metadata_defaults: false
        dialect: org.hibernate.dialect.PostgreSQLDialect
    show-sql: true