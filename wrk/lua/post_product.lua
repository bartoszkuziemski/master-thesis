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
    local filename = "products.csv"

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

request = function()
	local url_path = "/products"
	local val = data[math.random(1, 1000)]

	local headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
	return wrk.format("POST", url_path, headers, cjson.encode(val))
end
