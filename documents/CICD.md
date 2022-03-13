# Jenkins 
Jenkins服务器地址： http://124.222.135.47:8081/

# server
* 使用了杨骁文的服务器， 请注意，Jenkins以用户lyk来与杨骁文的服务器进行ssh免密通信， 因此请不要删除服务器上的ssh公钥， 这会使ssh失效
* 杨骁文服务器上要执行项目的编译，并将编译后的jar包打成docker镜像， 这意味着服务器上需要有正确版本的jdk（jdk11），且正确安装了docker, 请不要擅自卸载这些软件
  * 为了正确启动docker,我创建了docker群组，并将用户lyk加入了该群组， 请不要改动服务器的zh群组设置
* 镜像打包完毕会被push到lyk的dockerhub, 然后服务器会将镜像从dockerhub pull下来执行
  * 可以使用`docker container ls -a`查看全部容器， 请不要删除该项目的容器