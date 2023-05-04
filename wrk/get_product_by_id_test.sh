echo "get_product_by_id" >> results.csv

time=30
connections=50
rate=500
command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/get_product_by_id.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
