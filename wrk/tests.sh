for i in {1..20}
do
    rate=$((i * 1000))
    connections=$((i * 100))
    command="./wrk -t1 -c$connections -d1m -R$rate -s get_product_by_id.lua --u_latency http://localhost:8081"
    $command
done
