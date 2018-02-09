#!/usr/bin/env bash

server1="192.168.1.25"
server2="192.168.1.23"


tmpDir="/tmp/asfasdgasefdd"
projectSh="/tmp/asfasdgasefdd/frame.sh"

##
#  $1 ip  $2 文件
##
function upload()
{
    if [ ! -n $1 ]; then
        echo "没有 ip"
        exit 0
    fi
    if [ ! -f $tmpDir/$2 ]; then
        echo "没有 文件 $2 !"
        exit 1
    fi
    echo "开始上传文件 $2 到 $1"
    scp $tmpDir/$2 root@$1:$tmpDir
}

echo "开始部署正式环境"

ssh root@$server1 << EOF
  if [ ! -d $tmpDir ]; then
    if [ -f $tmpDir ]; then
      rm -rf $tmpDir
    fi
    mkdir -p $tmpDir
  fi
EOF
ssh root@$server2 << EOF
  if [ ! -d $tmpDir ]; then
    if [ -f $tmpDir ]; then
      rm -rf $tmpDir
    fi
    mkdir -p $tmpDir
  fi
EOF

## server1 服务器
upload $server1 frame-service.tar.gz

## server2 服务器
upload $server2 frame-service.tar.gz

ssh root@$server1 'bash' < $projectSh -s \
  frame-service

ssh root@$server2 'bash' < $projectSh -s \
  frame-service

sleep 20

## server1 服务器
upload $server1 frame-web.tar.gz

## server2 服务器
upload $server2 frame-web.tar.gz

ssh root@$server1 'bash' < $projectSh -s \
  frame-web

ssh root@$server2 'bash' < $projectSh -s \
  frame-web

