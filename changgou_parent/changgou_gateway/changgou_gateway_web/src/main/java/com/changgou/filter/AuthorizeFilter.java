package com.changgou.filter;

import com.changgou.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {
    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    /***
     *全局过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request，response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //获取请求的RUI
        String path = request.getURI().getPath();
        //如果是登录、goods等开放的微服务则直接放行
        if (path.startsWith("/api/user/login") || path.startsWith("/api/brand")) {
            //放行
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }
        //获取请求头中的令牌信息
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //如果头文件中没有令牌信息，则从请求参数中获取令牌信息
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }
        //如果请求参数中没有获取到令牌信息,则从cookie中获取
        if (StringUtils.isEmpty(token)) {
            HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            token = first.getValue();
        }
        //如果为空，则输出错误代码
        if (StringUtils.isEmpty(token)) {
            //设置方法不允许被访问，405错误代码
            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
            return response.setComplete();
        }
        //如果token不为空,解析令牌数据
        try {
            Claims claims = JwtUtil.parseJWT(token);
            //将令牌信息添加到头文件中
            request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，返回401状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //放行
        return chain.filter(exchange);
    }


    /***
     *过滤器执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
