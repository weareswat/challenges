package pt.rubenmarques.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api/*")
public class SecurityFilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        log.trace("Validating TokenSecurityFilter: {}", request.getRequestURL());

        if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

//        String token = request.getHeader("Authorization");
//        try {
//            if (Objects.isNull(StringUtils.trimToNull(token))) { // Or getUsernameFromToken is null
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            }
//        } catch (ExpiredJwtException e) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getLocalizedMessage());
//
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
