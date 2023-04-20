trap "cleanup" SIGINT

function cleanup() {
    echo "Script terminated." >&2
    exit 1
}

echo "stress_test" >> results.csv
echo "time,connections,rate,req_num,duration,err_connect,err_read,err_write,err_status,err_timeout,latency_avg,p50,p75,p90,p99,p99_9,p99_99,p99_999,p100" >> results.csv

for i in {1..20}
do
    rate=$((i * 100))
    connections=$((i * 10))
    time=1
    command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/all_requests.lua --latency http://localhost:8081"
    echo -n "$time, $connections, $rate," >> results.csv
    $command || cleanup
done
