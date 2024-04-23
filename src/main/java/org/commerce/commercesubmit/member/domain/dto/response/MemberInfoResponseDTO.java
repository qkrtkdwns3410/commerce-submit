package org.commerce.commercesubmit.member.domain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.commerce.commercesubmit.member.domain.entity.MemberEntity}
 */
@Value
public class MemberInfoResponseDTO {
    @ApiModelProperty(value = "생성일시", example = "2024-04-24T00:00:00")
    LocalDateTime createdDate;
    
    @ApiModelProperty(value = "수정일시", example = "2024-04-24T00:00:00")
    LocalDateTime lastModifiedDate;
    
    @ApiModelProperty(value = "회원번호", example = "1")
    Long id;
    
    @ApiModelProperty(value = "회원 ID", example = "qkrtkdwns3410")
    String memberId;
    
    @ApiModelProperty(value = "닉네임", example = "psjzzang")
    String nickname;
    
    @ApiModelProperty(value = "이름", example = "박상준")
    String name;
    
    @ApiModelProperty(value = "전화번호", example = "010-1234-5678")
    String phoneNumber;
    
    @ApiModelProperty(value = "이메일 주소", example = "qkrtkdwns3410@gmail.com")
    String email;
    
    @Builder
    private MemberInfoResponseDTO(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id, String memberId, String nickname, String name, String phoneNumber, String email) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.id = id;
        this.memberId = memberId;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}