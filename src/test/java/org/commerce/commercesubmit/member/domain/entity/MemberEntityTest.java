package org.commerce.commercesubmit.member.domain.entity;

import org.commerce.commercesubmit.member.domain.dto.request.MemberUpdateRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("회원엔티티 테스트")
public class MemberEntityTest {
    
    private MemberEntity me;
    
    @BeforeEach
    void setUp() {
        me = MemberEntity.builder()
                .id(1L)
                .memberId("qkrtkdwns3410")
                .password("askjqi1230(*@83")
                .nickname("김철수봇")
                .name("김철수")
                .phoneNumber("010-1234-1234")
                .email("qkrtkdwns3410@gmail.com")
                .build();
    }
    
    @Test
    @DisplayName("회원 엔티티 업데이트 메서드  - 정상 테스트")
    void When_UpdateMemberEntity_Then_Success() {
        //given
        MemberUpdateRequestDTO updateRequestDTO = MemberUpdateRequestDTO.builder()
                .password("newPasswordHackingNoNo!")
                .nickname("새로태어난 김철수")
                .phoneNumber("010-4321-4321")
                .email("isNew9551103@gmai.com")
                .build();
        
        // when
        me.update(updateRequestDTO);
        
        // then
        assertThat(me.getPassword()).isEqualTo(updateRequestDTO.getPassword());
        assertThat(me.getNickname()).isEqualTo(updateRequestDTO.getNickname());
        assertThat(me.getPhoneNumber()).isEqualTo(updateRequestDTO.getPhoneNumber());
        assertThat(me.getEmail()).isEqualTo(updateRequestDTO.getEmail());
    }
    
    @Test
    @DisplayName("DTO 객체가 null일 경우 업데이트 메서드 호출 시 NullPointerException 발생")
    void When_UpdateMemberEntity_Then_NullPointerException() {
        assertThatThrownBy(() -> me.update(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("updateRequestDTO must not be null");
    }
}