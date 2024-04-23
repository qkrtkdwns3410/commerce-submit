package org.commerce.commercesubmit.member.service;

import org.commerce.commercesubmit.common.annotation.CustomedTestRunner;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.persistence.MemberPersistence;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : org.commerce.commercesubmit.member.service
 * fileName       : MemberSignInServiceTest
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@CustomedTestRunner
class MemberInfoServiceTest {
    
    @Autowired
    private TestEntityManager tem;
    
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    
    private static final int PAGE_SIZE = 10;
    
    private MemberPersistence memberPersistence;
    
    private MemberInfoService memberInfoService;
    
    private MemberInfoResponseDTO memberInfoResponseDTO;
    
    @BeforeEach
    void setUp() {
        memberPersistence = new MemberPersistence(memberEntityRepository);
        memberInfoService = new MemberInfoService(memberPersistence);
        
        memberInfoResponseDTO = MemberInfoResponseDTO.builder()
                .memberId("qkrtkdwns3410")
                .nickname("개발은 항상 새로워")
                .name("유재석")
                .phoneNumber("010-1234-5678")
                .email("qkrtkdwns3410@gmail.com")
                .build();
        
        
    }
    
    @Test
    @DisplayName("멤버 조회 테스트 - 멤버 페이지네이션 테스트")
    void test1() {
        // given
        for (int i = 0; i < PAGE_SIZE; i++) {
            //bulk insert 는 수행되지 않지만 성능에 영향이 거의 없기에 수행
            tem.persist(MemberEntity.builder()
                    .password("ajsdi*(qjlekldjj23LKKk")
                    .memberId("qkrtkdwns3410" + i)
                    .nickname(memberInfoResponseDTO.getNickname())
                    .name(memberInfoResponseDTO.getName())
                    .phoneNumber(memberInfoResponseDTO.getPhoneNumber())
                    .email(memberInfoResponseDTO.getEmail())
                    .build());
        }
        
        PageRequest request = PageRequest.of(0, PAGE_SIZE / 2, Sort.by(Sort.Order.desc("createdDate")));
        
        // when
        Page<MemberInfoResponseDTO> found = memberInfoService.searchmembersByPaging(request);
        
        // then
        assertThat(found).isNotNull();
        
        assertThat(found.getTotalElements()).isEqualTo(PAGE_SIZE);
        
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getMemberId()).isEqualTo("qkrtkdwns3410" + (PAGE_SIZE / 2));
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getNickname()).isEqualTo(memberInfoResponseDTO.getNickname());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getName()).isEqualTo(memberInfoResponseDTO.getName());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getPhoneNumber()).isEqualTo(memberInfoResponseDTO.getPhoneNumber());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getEmail()).isEqualTo(memberInfoResponseDTO.getEmail());
        
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getCreatedDate()).isNotNull();
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getLastModifiedDate()).isNotNull();
    }
    
    @Test
    @DisplayName("아무 회원도 없는 경우 - 빈 페이지 반환")
    void test2() {
        // given
        PageRequest request = PageRequest.of(0, PAGE_SIZE / 2, Sort.by(Sort.Order.desc("createdDate")));
        
        // when
        Page<MemberInfoResponseDTO> found = memberInfoService.searchmembersByPaging(request);
        
        // then
        assertThat(found).isNotNull();
        
        assertThat(found.getTotalElements()).isEqualTo(0);
    }
}