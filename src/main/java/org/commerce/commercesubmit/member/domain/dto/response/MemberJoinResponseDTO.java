package org.commerce.commercesubmit.member.domain.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;

import java.time.LocalDateTime;

/**
 * DTO for {@link MemberEntity}
 */
@Value
public class MemberJoinResponseDTO {
    @ApiModelProperty(value = "회원번호", example = "1")
    Long id;
    
    @ApiModelProperty(value = "회원 ID", example = "qkrtkdwns3410")
    String memberId;
    
    @ApiModelProperty(value = "생성일시", example = "2024-04-24T00:00:00")
    LocalDateTime createdDate;
    
    @ApiModelProperty(value = "수정일시", example = "2024-04-24T00:00:00")
    LocalDateTime lastModifiedDate;
    
    @Builder
    private MemberJoinResponseDTO(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id, String memberId) {
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.id = id;
        this.memberId = memberId;
    }
}