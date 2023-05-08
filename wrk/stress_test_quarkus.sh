trap "cleanup" SIGINT

function cleanup() {
    echo "Script terminated." >&2
    exit 1
}

echo "stress_test" >> results.csv
values=(5 6 10 15 18 20 21 22 23 24 25 26 27 28)

for i in "${values[@]}"
do
    rate=$((i * 100))
    connections=$((i * 10))
    time=10
    command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/all_requests.lua --latency http://localhost:8081"
    echo -n "$time,$connections,$rate," >> results.csv
    $command || cleanup
done
