package org.example.config.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    public AuthenticationEntryPointImpl() {}
    /**
     * @param request 遇到了认证异常authException用户请求
     * @param response 将要返回给客户的相应
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.debug("预认证入口被调用。拒绝访问，AuthenticationException={}", exception.getMessage());
        // 没有权限，返回403
        response.sendError(403, "Access Denied");
    }
}
