package org.commerce.commercesubmit.member.domain.dto.response;

import lombok.Builder;
import lombok.Value;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;

import java.time.LocalDateTime;

/**
 * DTO for {@link org.commerce.commercesubmit.member.domain.entity.MemberEntity}
 */
@Value
public class MemberInfoResponseDTO {
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    Long id;
    String memberId;
    String nickname;
    String name;
    String phoneNumber;
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
    
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .id(id)
                .memberId(memberId)
                .nickname(nickname)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();
    }
}