package org.commerce.commercesubmit.member.domain.dto.response;

import lombok.Builder;
import lombok.Value;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;

import java.time.LocalDateTime;

/**
 * DTO for {@link MemberEntity}
 */
@Value
public class MemberJoinResponseDTO {
    Long id;
    String memberId;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    
    @Builder
    private MemberJoinResponseDTO(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id, String memberId) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.id = id;
        this.memberId = memberId;
    }
}