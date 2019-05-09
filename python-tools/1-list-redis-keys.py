#!/bin/bash
#-------------------------------------------------------------------------------------------#

HOST='lakana-devgreen-redis-test01-us-east-1.lakana-dev.com'

#-------------------------------------------------------------------------------------------#
# aws elasticache describe-cache-clusters --cache-cluster-id ${HOST} --show-cache-node-info
# redis-cli -h ${HOST}  --scan --pattern 'stage*'|awk -F: '{print $3}'|sort|uniq -c
# redis-cli -h ${HOST} --scan --pattern '*'|awk -F: '{print $3}'|sort|uniq -c
# redis-cli -h ${HOST} --scan --pattern '*'|awk -F: '{print $3}'|sort|uniq -c

#-------------------------------------------------------------------------------------------#
redis-cli -h ${HOST} --scan --pattern '*'

