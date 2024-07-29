package code.domain.User;

import code.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Table(name="user")
@RequiredArgsConstructor
@AllArgsConstructor
@Getter @Setter @Builder
@ToString @EqualsAndHashCode
public class UserEntity 
{
    @Id @Column(name="user_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userNo;
    String name;
    String userId;
    String password;
    String email;
    String role;

    String location;


    public UserDto toDto()
    {
        return UserDto.builder()
                                .userNo(getUserNo())
                                .userId(getUserId())
                                .password(getPassword())
                                .userName(getName())
                                .email(getEmail())
                                .location(getLocation())
                                .role(getRole())
                                .build();
    }

    
}
