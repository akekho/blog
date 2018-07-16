#!/bin/bash

PID=$(netstat -anp|grep 18123|awk '{printf $7}'|cut -d/ -f1)
echo "Killing PID ${PID}"
exec kill ${PID}
echo "Done!"
