#!/usr/bin/env bash

EXCUTOR="com.osms.monitoring.monitor.os.OsMonitor"
REFS="./libs/*:./conf"
JAVA_OPTS="-Xmx64m -cp ${REFS} ${EXCUTOR}"
JAVA_REMOTE_DEBUG_PORT=10382
JAVA_REMOTE_DEBUG_OPTS="-Xdebug -Xnoagent -Xrunjdwp:transport=dt_socket,address=${JAVA_REMOTE_DEBUG_PORT},server=y,suspend=y"
CMD="java ${JAVA_OPTS}"

case $1 in
start)
nohup $CMD > /dev/null 2>&1&
;;
start-foreground)
$CMD
;;
debug)
java $JAVA_REMOTE_DEBUG_OPTS $JAVA_OPTS
;;
stop)
ps -ef|grep $EXCUTOR | grep -v grep | awk '{print "kill -9 "$2}' | sh -x
;;
restart)
$0 stop
$0 start
;;
*)
echo "Invalid mode. Usage: start, start-foreground, stop, restart"
esac