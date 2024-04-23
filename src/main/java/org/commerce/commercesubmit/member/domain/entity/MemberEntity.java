package org.commerce.commercesubmit.member.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.commerce.commercesubmit.common.basetime.BaseEntity;
import org.commerce.commercesubmit.member.domain.dto.response.MemberEntityResponseDTO;

import javax.persistence.*;

/**
 * packageName    : org.commerce.commercesubmit.member.domain.entity
 * fileName       : MemberEntity
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity extends BaseEntity {
    //MySQL 의 경우에는 IDENTITY 전략을 취하는 경우 bulk insert 가 되지 않는 다는 문제점은 인지하고 개발하였다는 점 고려해주세요!😢
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "MEMBER_ID", nullable = false, unique = true)
    private String memberId;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;
    
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;
    
    @Column(name = "EMAIL", nullable = false)
    private String email;
    
    @Builder
    private MemberEntity(Long id, String memberId, String password, String nickname, String name, String phoneNumber, String email) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    public MemberEntityResponseDTO toResponseDTO() {
        return MemberEntityResponseDTO.builder()
                .createdDate(this.getCreatedDate())
                .lastModifiedDate(this.getLastModifiedDate())
                .id(this.getId())
                .memberId(this.getMemberId())
                .build();
    }
}