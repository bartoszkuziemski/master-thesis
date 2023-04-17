echo "get_products" >> results.csv
echo "time,connections,rate,req_num,duration,err_connect,err_read,err_write,err_status,err_timeout,latency_avg,p50,p75,p90,p99,p99_9,p99_99,p99_999,p100" >> results.csv

time=30
connections=20
rate=200
command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/get_products.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
