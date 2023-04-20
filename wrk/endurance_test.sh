echo "endurance_test" >> results.csv
echo "time,connections,rate,req_num,duration,err_connect,err_read,err_write,err_status,err_timeout,latency_avg,p50,p75,p90,p99,p99_9,p99_99,p99_999,p100" >> results.csv

time=8
connections=50
rate=500
command="./wrk -t1 -c$connections -d${time}h -R$rate -s ./lua/all_requests.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
