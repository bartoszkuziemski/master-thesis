request = function()
   path = "/products"
   headers = { ["Content-Type"] = "application/json;charset=UTF-8" }
   return wrk.format("DELETE", path, headers)
end
