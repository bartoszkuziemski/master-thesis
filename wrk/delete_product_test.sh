echo "delete_product" >> results.csv

time=30
connections=100
rate=1000
command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/delete_product.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
