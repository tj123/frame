#!/usr/bin/env bash


server1="10.27.232.196"
server2="10.46.76.96"


target_path="/yuanben/fstip_back"
fstip_sh="/yuanben/fstip_sh/fstip.sh"

##
#  $1 ip  $2 文件
##
function upload()
{
    if [ ! -n $1 ]; then
        echo "没有 ip"
        exit 0
    fi
    if [ ! -f $target_path/$2 ]; then
        echo "没有 文件 $2 !"
        exit 1
    fi
    echo "开始上传文件 $2 到 $1"
    scp $target_path/$2 root@$1:$target_path
}

echo "开始部署正式环境"

ssh root@$server1 << EOF
  if [ ! -d $target_path ]; then
    rm -rf $target_path
    mkdir -p $target_path
  fi
EOF
ssh root@$server2 << EOF
  if [ ! -d $target_path ]; then
    rm -rf $target_path
    mkdir -p $target_path
  fi
EOF


## fstip 服务器
upload $server1 data_service.tar.gz
upload $server1 swb_service.tar.gz

ssh root@$server1 'bash' < $fstip_sh -s \
 data-service \
 swb-service

## yb 04 服务器
upload $server2 fstip_service.tar.gz
upload $server2 fstip_admin.tar.gz

ssh root@$server2 'bash' < $fstip_sh -s \
  fstip-service-proxy-simple \
  fstip-admin

upload $server1 fstip_job.tar.gz
upload $server1 fstip_web.tar.gz

sleep 20

ssh root@$server1 'bash' < $fstip_sh -s \
 fstip-job \
 fstip-web