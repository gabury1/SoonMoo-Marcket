package code.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import code.domain.user.UserEntity;
import code.domain.user.UserRepository;
import code.dto.SignUpDto;
import code.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
@RequiredArgsConstructor
public class UserService extends DefaultOAuth2UserService implements UserDetailsService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 일반 로그인 시 유저 로드
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        
        log.info("loadByUserName 진입 UserId :::: " + userId);
        var op = userRepository.findByUserId(userId);

        UserEntity user = op.orElseThrow(() -> new UsernameNotFoundException("ID가 틀렸습니다."));
        log.info("loadByUserName 진입 UserFound :::: " + true);
        return user.toDto();
    }

    // OAuth2 사용 시 유저 로드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        var oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> attr = oAuth2User.getAttribute("response");
        log.info("OAuth2 loadUser attr:::::: " + oAuth2User.toString());

        String name = attr.get("name").toString();
        String id = attr.get("id").toString();
        String email = attr.get("email").toString();

        // 네이버에서 받아온 정보를 바탕으로 유저를 찾는다.
        Optional<UserEntity> userOp = userRepository.findByUserId(id);

        // 해당 Id의 유저가 없다면, 새로운 회원으로 등록시킨다.
        if(userOp.isEmpty())
        {
            SignUpDto dto = SignUpDto.builder()
                                                .userId(id)
                                                .password(registrationId)
                                                .name(name)
                                                .email(email)
                                                .build();
            userCreate(dto);
            userOp = userRepository.findByUserId(id);
        }

        UserDto userDto = userOp.get().toDto();

        return userDto;
        
    }


    //User Create
    public String userCreate(SignUpDto dto)
    {
        if(!userRepository.findByUserId(dto.getUserId()).isEmpty()) return "중복된 아이디입니다.";
        
        var newUser = UserEntity.builder()
                                .name(dto.getName())
                                .userId(dto.getUserId())
                                .password(passwordEncoder.encode(dto.getPassword())) // 비밀번호 암호화할 것
                                .email(dto.getEmail())
                                .location("")
                                .role("USER")
                                .build();
        
        userRepository.save(newUser);

        return "success";
    }

    
}
  