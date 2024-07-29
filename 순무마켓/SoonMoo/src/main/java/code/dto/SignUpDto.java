package code.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.validator.routines.EmailValidator;

import code.domain.User.UserEntity;



@Getter @Setter @ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder @EqualsAndHashCode
public class SignUpDto 
{
    static final EmailValidator validator = EmailValidator.getInstance();
    
    @Size(min=2, max=21, message="이름은 2자에서 21자로 지어주세요.")
    @NotEmpty(message="이름을 입력해주세요.")
    String name;

    @Size(min=2, max=21, message="아이디는 5자에서 21자로 지어주세요.")
    @NotEmpty(message="아이디를 입력해주세요.")
    String userId;

    @Size(min=2, max=21, message="비밀번호는 5자에서 21자로 지어주세요.")
    @NotEmpty(message="비밀번호를 입력해주세요.")    
    String password;

    @Size(min=2, max=21, message="비밀번호는 5자에서 21자로 지어주세요.")
    @NotEmpty(message="비밀번호를 재입력해주세요.")    
    String rePassword;

    @Size(min=2, max=21, message="이메일은 5자에서 21자로 지어주세요.")
    @NotEmpty(message="이메일 입력해주세요.")    
    String email;

    // 유효한 이메일인지 확인
    public boolean mailCheck()
    {
        return validator.isValid(email);
    }

    // 비밀번호 재입력을 확인
    public boolean rePwCheck(){ return password.equals(rePassword);}

}
