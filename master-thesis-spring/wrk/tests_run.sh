# example without lua script. This runs a benchmark for 60 seconds, using 100 threads, keeping 1000 HTTP connections open, and a constant throughput of 2000 requests per second:
# ./wrk -t100 -c1000 -d60s -R2000 --latency http://localhost:8081/products

# --latency: provides detailed latency percentile information
# --u_latency: provides detailed uncorrected latency percentile information

# plain tests for post, get, delete
./wrk -t20 -c100 -d30m -R1000 -s post_product.lua --u_latency http://localhost:8081
./wrk -t20 -c100 -d30m -R100 -s get_products.lua --u_latency http://localhost:8081
./wrk -t1 -c100 -d30m -R1000 -s get_product_by_id.lua --u_latency http://localhost:8081
./wrk -t20 -c100 -d30m -R1000 -s delete_product_by_id.lua --u_latency http://localhost:8081

