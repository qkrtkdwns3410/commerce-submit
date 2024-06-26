package org.commerce.commercesubmit.member.domain.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * packageName    : org.commerce.commercesubmit.member.domain.dto.request
 * fileName       : SignUpRequestDTO
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberSignUpRequestDTO {
    @NotBlank(message = "회원 ID는 필수 입력 값입니다.")
    @Size(min = 4, message = "회원 ID는 최소 4자 이상이어야 합니다.")
    @ApiModelProperty(value = "회원 ID", required = true, example = "qkrtkdwns3410")
    String memberId;
    
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @ApiModelProperty(value = "비밀번호", required = true, example = "c82hndkja#2")
    String password;
    
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    @ApiModelProperty(value = "닉네임", required = true, example = "psjzzang")
    String nickname;
    
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(min = 2, message = "이름은 최소 2자 이상이어야 합니다.")
    @ApiModelProperty(value = "이름", required = true, example = "박상준")
    String name;
    
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다. 예) 010-1234-5678")
    @ApiModelProperty(value = "전화번호", required = true, example = "010-1234-5678")
    String phoneNumber;
    
    @NotBlank(message = "이메일 주소는 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    @ApiModelProperty(value = "이메일 주소", required = true, example = "qkrtkdwns3410@gmail.com")
    String email;
    
    @Builder
    private MemberSignUpRequestDTO(String memberId, String password, String nickname, String name, String phoneNumber, String email) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .memberId(memberId)
                .password(password)
                .nickname(nickname)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}