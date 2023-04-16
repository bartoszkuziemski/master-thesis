math.randomseed(os.clock()*100000000000)

request = function()
    local id = math.random(1, 10000)
    local url_path = "/products/" .. id
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("GET", url_path, headers)
end

done = function(summary, latency, requests)
    local data = {
        {
            summary.requests,
            summary.duration,
            summary.errors.connect,
            summary.errors.read,
            summary.errors.write,
            summary.errors.status,
            summary.errors.timeout,
            latency.mean,
            latency:percentile(50.0),
            latency:percentile(75.0),
            latency:percentile(90.0),
            latency:percentile(99.0),
            latency:percentile(99.9),
            latency:percentile(99.99),
            latency:percentile(99.999),
            latency:percentile(100.0)
        }
    }
    save_results("results.csv", data)
end

function save_results(filename, data)
    local file = io.open(filename, "a")
    for _, row in ipairs(data) do
        file:write(table.concat(row, ",") .. ",\n")
    end
    file:close()
end