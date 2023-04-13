math.randomseed(os.clock()*100000000000)

loadFile = function()
    local filename = "urls.txt"

    local data = {}
    local count = 0

    for line in io.lines(filename) do
        data[count] = line
        count = count + 1
    end

    return data
end

local urls = loadFile()

request = function()
    local url_path = urls[math.random(1, 1000)]
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("GET", url_path, headers)
end
