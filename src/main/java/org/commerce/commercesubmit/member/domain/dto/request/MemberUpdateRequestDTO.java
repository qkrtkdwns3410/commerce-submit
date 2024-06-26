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
 * fileName       : MemberUpdateRequestDTO
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateRequestDTO {
    
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @ApiModelProperty(value = "비밀번호", required = true, example = "c82hndkja#2233")
    String password;
    
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    @Size(min = 2, message = "닉네임은 최소 2자 이상이어야 합니다.")
    @ApiModelProperty(value = "닉네임", required = true, example = "newNickname")
    String nickname;
    
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다. 예) 010-1234-5678")
    @ApiModelProperty(value = "전화번호", required = true, example = "010-9999-9999")
    String phoneNumber;
    
    @NotBlank(message = "이메일 주소는 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    @ApiModelProperty(value = "이메일 주소", required = true, example = "newQkrtkdwns3410@gmail.com")
    String email;
    
    
    @Builder
    private MemberUpdateRequestDTO(String password, String nickname, String phoneNumber, String email) {
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .password(password)
                .nickname(nickname)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}