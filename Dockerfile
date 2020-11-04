# node环境镜像
FROM node:latest AS build-env
# 创建hexo-blog文件夹且设置成工作文件夹
RUN mkdir -p /home/q/hexo-blog
WORKDIR /home/q/hexo-blog
# 复制当前文件夹下面的所有文件到hexo-blog中
COPY . .
# 安装 hexo-cli
RUN npm --registry=https://registry.npm.taobao.org install hexo-cli -g && npm install

EXPOSE 4000

RUN hexo server >> /home/q/hexo-blog/logs