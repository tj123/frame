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
<%for(var file in files){if(/service/.test(file)){%>upload $server<%=i+1%> <%=file%>.tar.gz
<%}}}%><%for(var i = 0;i < config.targetServer.length;i++){%>
ssh <%=config.user%>@$server<%=i+1%> 'bash' < $projectSh -s \
  <%const fls=[];for(let file in files){if(config.env != 'prod'){file = file + '-' + config.env};fls.push(file)}%><%=fls.filter(val => /service/.test(val)).join('\\\n  ')%>
<%}%>
sleep 20
<%for(var i = 0;i < config.targetServer.length;i++){%>
## server<%=i+1%> 服务器
<%for(var file in files){if(!/service/.test(file)){%>upload $server<%=i+1%> <%=file%>.tar.gz
<%}}}%><%for(var i = 0;i < config.targetServer.length;i++){%>
ssh <%=config.user%>@$server<%=i+1%> 'bash' < $projectSh -s \
  <%const fls=[];for(let file in files){if(config.env != 'prod'){file = file + '-' + config.env};fls.push(file)}%><%=fls.filter(val => !/service/.test(val)).join('\\\n  ')%>
<%}%>
