trap "cleanup" SIGINT

function cleanup() {
    echo "Script terminated." >&2
    exit 1
}

command="./wrk -t1 -c50 -d5m -R500 -s ./lua/get_product_by_id.lua --latency http://localhost:8081"
$command

echo "stress_test" >> results.csv

for i in {1..20}
do
    rate=$((i * 100))
    connections=$((i * 10))
    time=3
    command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/all_requests.lua --latency http://localhost:8081"
    echo -n "$time,$connections,$rate," >> results.csv
    $command || cleanup
done
