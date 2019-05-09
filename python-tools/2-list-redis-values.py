#!/usr/bin/python
import redis, pprint, json, sys, time, datetime, pytz

#----------------------------------------------------------------------------------------------#
# print 'Number of arguments:', len(sys.argv), 'arguments.'
# print 'Argument List:', str(sys.argv)
tz = pytz.timezone('America/Los_Angeles')
#----------------------------------------------------------------------------------------------#
r = redis.Redis(host='lakana-devgreen-redis-test01-us-east-1.lakana-dev.com', port=6379)
"""
for key in r.scan_iter("*"):
    # print key , "," , r.get(key)
    print key
    data = r.hgetall(key)
    print(str(data))
tree = json.loads(data)
print json.dumps(data)
"""

#----------------------------------------------------------------------------------------------#
def data(key):

    data = r.hgetall(key)
    print "+---------------------------------------------------------------------------"
    print "|    " + key
    print "+---------------------------------------------------------------------------"
    for item in sorted(data):
        
        s = float(item) / 1000.0
        print datetime.datetime.fromtimestamp(s,tz).strftime('%Y-%m-%d %H:%M:%S'), "|", item, "|", data[item]
    
    #print "----------------------------------------------------------"


#----------------------------------------------------------------------------------------------#
if __name__ == "__main__":

    KEY = str(sys.argv[1])
data(KEY)

