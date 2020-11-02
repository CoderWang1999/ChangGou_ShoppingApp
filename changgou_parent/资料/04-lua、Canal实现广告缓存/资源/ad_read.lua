--设置响应头类型
ngx.header.content_type="application/json;charset=utf8"
--获取请求中的参数ID
local uri_args = ngx.req.get_uri_args();
local position = uri_args["position"];

--获取本地缓存
local cache_ngx = ngx.shared.dis_cache;
--根据ID 获取本地缓存数据
local adCache = cache_ngx:get('ad_cache_'..position);

if adCache == "" or adCache == nil then

--引入redis库
local redis = require("resty.redis");
--创建redis对象
local red = redis:new()
--设置超时时间
red:set_timeout(2000)
--连接
local ok, err = red:connect("192.168.200.128", 6379)
--获取key的值
local rescontent=red:get("ad_"..position)
--输出到返回响应中
ngx.say(rescontent)
--关闭连接
red:close()
--将redis中获取到的数据存入nginx本地缓存
cache_ngx:set('ad_cache_'..position, rescontent, 10*60);

else
 --nginx本地缓存中获取到数据直接输出
 ngx.say(adCache)
end
