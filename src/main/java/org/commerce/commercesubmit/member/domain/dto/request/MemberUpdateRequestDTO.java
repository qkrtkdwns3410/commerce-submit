package org.commerce.commercesubmit.member.domain.dto.request;

import lombok.Builder;
import lombok.Value;

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
@Value
public class MemberUpdateRequestDTO {
    
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    String password;
    
    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    String nickname;
    
    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "전화번호 형식이 유효하지 않습니다. 예) 010-1234-5678")
    String phoneNumber;
    
    @NotBlank(message = "이메일 주소는 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 유효하지 않습니다.")
    String email;
    
    
    @Builder
    private MemberUpdateRequestDTO(String password, String nickname, String phoneNumber, String email) {
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}