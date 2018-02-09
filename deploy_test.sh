#!/usr/bin/env bash

server1="192.168.1.25"


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

echo "开始部署测试环境"

ssh root@$server1 << EOF
  if [ ! -d $tmpDir ]; then
    if [ -f $tmpDir ]; then
      rm -rf $tmpDir
    fi
    mkdir -p $tmpDir
  fi
EOF

## server1 服务器
upload $server1 frame-service.tar.gz

ssh root@$server1 'bash' < $projectSh -s \
  frame-service-test

sleep 20

## server1 服务器
upload $server1 frame-web.tar.gz

ssh root@$server1 'bash' < $projectSh -s \
  frame-web-test

