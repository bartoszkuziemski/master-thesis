trap "cleanup" SIGINT

function cleanup() {
    echo "Script terminated." >&2
    exit 1
}

echo "get_product_by_id" >> results.csv

for i in {1..20}
do
    rate=$((i * 1000))
    connections=$((i * 100))
    time=1
    command="./wrk -t1 -c$connections -d${time}m -R$rate -s get_product_by_id.lua --latency http://localhost:8081"
    echo -n "$time, $connections, $rate," >> results.csv
    $command || cleanup
done
