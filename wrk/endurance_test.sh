echo "endurance_test" >> results.csv

time=8
connections=50
rate=500
command="./wrk -t1 -c$connections -d${time}h -R$rate -s ./lua/all_requests.lua --latency http://localhost:8081"
echo -n "$time,$connections,$rate," >> results.csv
$command
