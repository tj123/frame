#!/usr/bin/env bash

<%for(var i = 0;i < config.targetServer.length;i++){%>server<%=i+1%>="<%=config.targetServer[i]%>"
<%}%>

tmpDir="<%=tmpDir%>"
projectSh="<%=tmpDir%>/<%=project%>.sh"

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
    scp $tmpDir/$2 <%=config.user%>@$1:$tmpDir
}

echo "开始部署<%={prod:'正式',test:'测试'}[config.env]||config.env%>环境"
<%for(var i = 0;i < config.targetServer.length;i++){%>
ssh <%=config.user%>@$server<%=i+1%> << EOF
  if [ ! -d $tmpDir ]; then
    if [ -f $tmpDir ]; then
      rm -rf $tmpDir
    fi
    mkdir -p $tmpDir
  fi
EOF<%}%>
<%for(var i = 0;i < config.targetServer.length;i++){%>
## server<%=i+1%> 服务器
<%for(var file in files){%>
<%if(/service/.test(file))%> upload $server<%=i+1%> <%=file%>.tar.gz<%}%>
<%}%>

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