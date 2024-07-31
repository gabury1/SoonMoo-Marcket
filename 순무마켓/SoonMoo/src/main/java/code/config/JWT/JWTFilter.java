package code.config.JWT;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import code.domain.User.UserEntity;
import code.dto.UserDto;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor @Slf4j
public class JWTFilter extends OncePerRequestFilter 
{
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        
        log.info("진입");
        // Cookie c = new Cookie("Authorization", null);
        // c.setMaxAge(0);
        // response.addCookie(c);

 		//request에서 Authorization 헤더를 찾음
        String authorization= request.getHeader("Authorization");
				
		//Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer")) {

            System.out.println("token null : " + authorization);
            filterChain.doFilter(request, response);
						
						//조건이 해당되면 메소드 종료 (필수)
            return;
        }
			
        System.out.println("authorization now");
				//Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split("???")[1];
			
				//토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {

            System.out.println("token expired");
            filterChain.doFilter(request, response);

						//조건이 해당되면 메소드 종료 (필수)
            return;
        }

				//토큰에서 username과 role 획득
        String username = jwtUtil.getName(token);
        String role = jwtUtil.getRole(token);
				
				//userEntity를 생성하여 값 set
        UserDto dto = new UserDto();
        dto.setUserName(username);
        //dto.setPassword("text");
        dto.setRole(role);
	

		//스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(dto, null, dto.getAuthorities());
		//세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
        log.info("JWTFilter :::::: doFilterInternal ::::::" + dto.getUserNo() + "번 유저 발급 완료");
    }
    
}
