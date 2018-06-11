#!/bin/bash
build_img(){
   env=$1
   cd .. && \
   mvn clean && \
   mvn compile && \
   mvn package && \
   if test $env = "dev"
   then
       java -jar target/blog.jar --spring.profiles.active
       echo "构建完成！"
   elif test $env = "test"
   then
      java -jar target/blog.jar --spring.profiles.active=${env}
      echo "构建完成！"
   elif test $env = "prod"
   then
      nohup java -jar target/blog.jar --spring.profiles.active=${env}&
      echo "构建完成！"
   else
      echo '参数错误!'
   fi
}
echo "********************开始构建Blog项目${1}模式************************"
ENV=$1
case ${ENV} in
    "dev")
        build_img dev
        ;;
    "test")
        build_img test
        ;;
    "prod")
        build_img prod
        ;;
    *)
        build_img dev
        ;;
esac
