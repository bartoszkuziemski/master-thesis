local require = require
local cjson = require("cjson")
math.randomseed(os.clock()*100000000000)

function ParseCSVLine(line,sep)
	local res = {}
	local pos = 1
	sep = sep or ','
	while true do
		local c = string.sub(line,pos,pos)
		if (c == "") then break end
		if (c == '"') then
			local txt = ""
			repeat
				local startp,endp = string.find(line,'^%b""',pos)
				txt = txt..string.sub(line,startp+1,endp-1)
				pos = endp + 1
				c = string.sub(line,pos,pos)
				if (c == '"') then txt = txt..'"' end

			until (c ~= '"')
			table.insert(res,txt)
			assert(c == sep or c == "")
			pos = pos + 1
		else
			local startp,endp = string.find(line,sep,pos)
			if (startp) then
				table.insert(res,string.sub(line,pos,startp-1))
				pos = endp + 1
			else
				table.insert(res,string.sub(line,pos))
				break
			end
		end
	end
	return res
end

loadFile = function()
    local filename = "./lua/products.csv"

    local data = {}
    local count = 0
    local sep = ","

    for line in io.lines(filename) do
        local values = ParseCSVLine(line,sep)
        data[count] = { name=values[1], price=values[2], description=values[3], rating=values[4], userId=values[5], categoryType=values[6], conditionType=values[7], offerType=values[8] }
        count = count + 1
    end

    return data
end

local data = loadFile()

-- Define the request functions
local function post_request()
    local url_path = "/products"
    local val = data[math.random(1, 1000)]

    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("POST", url_path, headers, cjson.encode(val))
end

local function delete_request()
    local path = "/products"
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("DELETE", path, headers)
end

local function get_request()
    local id = math.random(1, 1000)
    local url_path = "/products/" .. id
    local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
    return wrk.format("GET", url_path, headers)
end

-- Define the request function that will be called by wrk
request = function()
    local random_number = math.random(1, 100)

    if random_number <= 70 then
        return get_request()
    elseif random_number > 70 and random_number <= 85 then
        return delete_request()
    else
        return post_request()
    end
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
