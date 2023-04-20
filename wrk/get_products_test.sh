echo "get_products" >> results.csv

time=30
connections=20
rate=200
command="./wrk -t1 -c$connections -d${time}m -R$rate -s ./lua/get_products.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
