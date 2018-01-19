#!/usr/bin/env bash


tgt="/yuanben/fstip_back"
deploy_path="/yuanben/fstip_d"

jar_fstip_service="fstip-service-0.0.1-Release"
jar_fstip_web="fstip-web-0.0.1-SNAPSHOT"
jar_fstip_admin="fstip-admin-0.0.1-SNAPSHOT"
jar_fstip_job="fstip-job-0.0.1-Release"
jar_data_service="data-service-0.0.1-Release"
jar_swb_service="swb-service-0.0.1-Release"

proxy_host="10.24.19.37"
proxy_port="3128"
proxy_opts="-Xms256m -Xmx512m -DproxySet=true -Dhttp.proxyHost=$proxy_host -Dhttp.proxyPort=$proxy_port -Dhttps.proxyHost=$proxy_host -Dhttps.proxyPort=$proxy_port"
opts="-Xms256m -Xmx512m"

function check_file(){
  eval 'jar=$jar_'$1
  fil=$tgt/$jar.jar
  if [ -f $fil ]; then
    echo " 没有找到文件 $fil !"
    exit 1
  fi
}

function extract(){
  echo "开始解压 $1"
  tar -zxf $tgt/$1.tar.gz -C $deploy_path
}

function starWithProxy() {
    echo "开始启动 $1 并打开网络代理"
    eval 'jar=$jar_'$1
    nohup java $proxy_opts -jar $deploy_path/$1/$jar.jar >/dev/null 2>&1 &
    if [ $? -eq 0 ]; then
      echo "启动 服务 $1 成功"
    else
      echo "启动 服务 $1 失败"
    fi
}

function startWithProxySimple() {
    echo "开始启动 $1 并打开简单模式网络代理"
    eval 'jar=$jar_'$1
    nohup java -Xms256m -Xmx512m -DproxySet=true -jar $deploy_path/$1/$jar.jar >/dev/null 2>&1 &
    if [ $? -eq 0 ]; then
      echo "启动 服务 $1 成功"
    else
      echo "启动 服务 $1 失败"
    fi
}

# $1 jar 包名称
function start(){
    echo "开始启动 $1"
    eval 'jar=$jar_'$1
    nohup java -Xms256m -Xmx512m -jar $deploy_path/$1/$jar.jar >/dev/null 2>&1 &
    if [ $? -eq 0 ]; then
      echo "启动 服务 $1 成功"
    else
      echo "启动 服务 $1 失败"
    fi
}

# $1 jar 包名称 $2 'test' 'prod'
function start_web(){
    echo "开始启动 $1"
    eval 'jar=$jar_'$1
    nohup java -Xms256m -Xmx512m -jar $deploy_path/$1/$jar.jar --spring.profiles.active=$2 >/dev/null 2>&1 &
}

# $1 jar 包名称 $2 'test' 'prod'
function start_web_proxy(){
    echo "开始启动 $1"
    eval 'jar=$jar_'$1
    nohup java -Xms256m -Xmx512m -DproxySet=true -Dhttp.proxyHost=10.24.19.37 -Dhttp.proxyPort=3128 -Dhttps.proxyHost=10.24.19.37 -Dhttps.proxyPort=3128 -jar $deploy_path/$1/$jar.jar --spring.profiles.active=$2 >/dev/null 2>&1 &
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
  rm -rf $deploy_path/$1
}

if [ ! -d $deploy_path ]; then
  rm -rf $deploy_path
  mkdir -p $deploy_path
fi

parms=$@
for parm in ${parms[*]}; do

  case $parm in
  fstip-service)
    check_file fstip_service
    stop fstip_service
    remove fstip_service
    extract fstip_service
    start fstip_service
    ;;
  fstip-service-proxy)
    check_file fstip_service
    stop fstip_service
    remove fstip_service
    extract fstip_service
    starWithProxy fstip_service
    ;;
  fstip-service-proxy-simple)
    check_file fstip_service
    stop fstip_service
    remove fstip_service
    extract fstip_service
    startWithProxySimple fstip_service
    ;;
  fstip-web)
    check_file fstip_web
    stop fstip_web
    remove fstip_web
    extract fstip_web
    start_web fstip_web prod
    ;;
  fstip-web-test)
    check_file fstip_web
    stop fstip_web
    remove fstip_web
    extract fstip_web
    start_web fstip_web test
    ;;
  fstip-admin-test)
    check_file fstip_admin
    stop fstip_admin
    remove fstip_admin
    extract fstip_admin
    start_web fstip_admin test
    ;;
  fstip-admin)
    check_file fstip_admin
    stop fstip_admin
    remove fstip_admin
    extract fstip_admin
    start_web fstip_admin prod
    ;;
  fstip-job)
    check_file fstip_job
    stop fstip_job
    remove fstip_job
    extract fstip_job
    start_web_proxy fstip_job prod
    ;;
  fstip-job-test)
    check_file fstip_job
    stop fstip_job
    remove fstip_job
    extract fstip_job
    start_web fstip_job test
    ;;
  data-service)
    check_file data_service
    stop data_service
    remove data_service
    extract data_service
    start data_service
    ;;
  swb-service)
    check_file swb_service
    stop swb_service
    remove swb_service
    extract swb_service
    start swb_service
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
