math.randomseed(os.clock()*100000000000)

request = function()
    local id = math.random(1, 10000)
    local url_path = "/products/" .. id
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("GET", url_path, headers)
end
