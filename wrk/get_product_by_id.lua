math.randomseed(os.clock()*100000000000)
json = require("json")

request = function()
    local id = math.random(1, 10000)
    local url_path = "/products/" .. id
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("GET", url_path, headers)
end

done = function(summary, latency, requests)
    local data = {
        requests = summary.requests,
        latency_avg = latency.mean,
        latency_50 = latency:percentile(50.0),
        latency_75 = latency:percentile(75.0),
        latency_90 = latency:percentile(90.0),
        latency_99 = latency:percentile(99.0),
      }
    save_results("results.json", data)
end

function save_results(filename, data)
    local file = io.open(filename, "a")
    file:write(json.encode(data) .. ",\n")
    file:close()
end