#!/usr/bin/env bash


tmpDir="/tmp/asfasdgasefdd"
deployDir="/yuanben/frame"

jar_frame-service="frame-service-1.0.0-SNAPSHOT.jar"
jar_frame-web="frame-web-1.0.0-SNAPSHOT.jar"


function check_file(){
  eval 'jar=$jar_'$1
  fil=$tmpDir/$jar.jar
  if [ -f $fil ]; then
    echo " 没有找到文件 $fil !"
    exit 1
  fi
}

function extract(){
  echo "开始解压 $1"
  tar -zxf $tmpDir/$1.tar.gz -C $deployDir
}

# $1 jar 包名称 $2 'test' 'prod'
function start(){
    echo "开始启动 $1"
    eval 'jar=$jar_'$1
    nohup java -Xms256m -Xmx512m -jar $deployDir/$1/$jar.jar --spring.profiles.active=$2 >/dev/null 2>&1 &
    if [ $? -eq 0 ]; then
      echo "启动 服务 $1 成功"
    else
      echo "启动 服务 $1 失败"
    fi
}

function stop(){
  echo "关闭 服务 $1"
  pid=`ps -ef|grep $1|grep -v grep|grep -v PPID|grep -v tail|awk '{print $2}'`
  if [ "$pid" == "" ]; then
    echo "服务 $1 未运行"
  else
    kill -9 $pid
    if [ $? -eq 0 ]; then
      echo "关闭 服务 $1 成功"
    else
      echo "关闭 服务 $1 失败"
    fi
  fi
}

function remove() {
  rm -rf $deployDir/$1
}

if [ ! -d $deployDir ]; then
  rm -rf $deployDir
  mkdir -p $deployDir
fi

parms=$@
for parm in ${parms[*]}; do

  case $parm in
  frame-service-test)
    check_file frame-service
    stop frame-service
    remove frame-service
    extract frame-service
    start frame-service test
    ;;
  frame-service)
    check_file frame-service
    stop frame-service
    remove frame-service
    extract frame-service
    start frame-service prod
    ;;
  frame-web-test)
    check_file frame-web
    stop frame-web
    remove frame-web
    extract frame-web
    start frame-web test
    ;;
  frame-web)
    check_file frame-web
    stop frame-web
    remove frame-web
    extract frame-web
    start frame-web prod
    ;;
  sleep)
    sleep 30
    ;;
  sleep20)
    sleep 20
    ;;
  *)
    echo "未知参数: $parm"
    ;;
  esac

done
