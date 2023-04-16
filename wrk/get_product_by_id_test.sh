echo "get_product_by_id" >> results.csv
echo "time ,connections ,rate ,req_num, duration, err_connect, err_read, err_write, err_status, err_timeout, latency_avg, latency_50, latency_75, latency_90, latency_99, latency_99_9, latency_99_99, latency_99_999, latency_100" >> results.csv

time=1
connections=100
rate=1000
command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/get_product_by_id.lua --latency http://localhost:8081"
echo -n "$time, $connections, $rate," >> results.csv
$command
