package org.commerce.commercesubmit.member.domain.dto.response;

import lombok.Builder;
import lombok.Value;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link MemberEntity}
 */
@Value
public class MemberEntityResponseDTO implements Serializable {
    Long id;
    String memberId;
    LocalDateTime createdDate;
    LocalDateTime lastModifiedDate;
    
    @Builder
    private MemberEntityResponseDTO(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id, String memberId) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.id = id;
        this.memberId = memberId;
    }
}