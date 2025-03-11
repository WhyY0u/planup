package cc.whyy0u.security.auth.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cc.whyy0u.details.CustomUserDetails;
import cc.whyy0u.entity.user.UserEntity;
import cc.whyy0u.service.user.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String HEADER_NAME = "Authorization";

   private final JwtService jwtService;
   private final UserService userService;

@Autowired
public JwtAuthenticationFilter(JwtService jwtService, @Lazy UserService userService) {
    this.jwtService = jwtService;
    this.userService = userService;
}


    @Override
protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response,
        @NonNull FilterChain filterChain
) throws ServletException, IOException {

    var authHeader = request.getHeader(HEADER_NAME);
    if (StringUtils.isEmpty(authHeader) || 
        !authHeader.startsWith(BEARER_PREFIX)) { 
        filterChain.doFilter(request, response);
        return;
    }

    var jwt = authHeader.substring(BEARER_PREFIX.length());
    var login = jwtService.extractUserName(jwt);

    if (login != null && !login.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserEntity entity = userService.findByLogin(login);
        UserDetails userDetails = new CustomUserDetails(entity);
        if (jwtService.isTokenValid(jwt, userDetails)) {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        
    }
    }
    filterChain.doFilter(request, response);
}

}