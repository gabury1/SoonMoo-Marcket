package code.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.*;

@RequiredArgsConstructor @AllArgsConstructor
@Getter @Setter  @EqualsAndHashCode
@ToString @Builder
public class UserDto implements UserDetails, OAuth2User
{
    Long userNo;
    String userId;
    String password;

    String userName;
    String email;
    String location;

    String role;

    // UserDetails 오버라이드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<GrantedAuthority> auth = new ArrayList<>();
        auth.add((GrantedAuthority) () -> role); // 람다로 깔끔하게

        return auth;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }


    // OAuth2User 오버라이드
    @Override
    public Map<String, Object> getAttributes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAttributes'");
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return userId;
    }





    
}
