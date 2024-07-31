package code.config.JWT;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import code.dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component @RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler
{
    private final JWTUtil jwtUtil;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException 
    {
        //OAuth2User
        UserDto customUserDetails = (UserDto) authentication.getPrincipal();

        String username = customUserDetails.getName();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();  

        String token = jwtUtil.createJwt(username, role, 60*60*60L);

        //response.addCookie(jwtUtil.createJwt("장은수", "USER", 3600L));
        try {
            response.sendRedirect("http://localhost:3000/");
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
    }

}
