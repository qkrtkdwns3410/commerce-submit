package org.commerce.commercesubmit.member.service;

import org.commerce.commercesubmit.common.annotation.CustomedTestRunner;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.NotFoundException;
import org.commerce.commercesubmit.member.domain.dto.request.MemberUpdateRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.persistence.MemberPersistence;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
@DisplayName("회원 정보 서비스 테스트")
@CustomedTestRunner
@ExtendWith(MockitoExtension.class)
class MemberInfoServiceTest {
    
    @Autowired
    private TestEntityManager tem;
    
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    
    @MockBean
    private PasswordEncoder passwordEncoder;
    
    private static final int PAGE_SIZE = 10;
    
    private MemberPersistence memberPersistence;
    
    private MemberInfoService memberInfoService;
    
    private MemberInfoResponseDTO memberInfoResponseDTO;
    
    private MemberUpdateRequestDTO updateRequestDTO;
    
    private LocalDateTime now = LocalDateTime.now();
    
    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        memberPersistence = new MemberPersistence(memberEntityRepository);
        memberInfoService = new MemberInfoService(memberPersistence, memberEntityRepository, passwordEncoder);
        
        memberInfoResponseDTO = MemberInfoResponseDTO.builder()
                .memberId("qkrtkdwns3410")
                .nickname("개발은 항상 새로워")
                .name("유재석")
                .phoneNumber("010-1234-5678")
                .email("qkrtkdwns3410@gmail.com")
                .build();
        
        updateRequestDTO = MemberUpdateRequestDTO.builder()
                .password("TheCommerceTestcode!@#")
                .nickname("닉네임변경하고싶은 기분")
                .phoneNumber("010-9876-5432")
                .email("updated320@gmail.com")
                .build();
    }
    
    @Test
    @DisplayName("멤버 조회 테스트 - 멤버 페이지네이션 테스트")
    void When_SearchMembersByPaging_Expect_PageOfMembers() {
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
        assertThat(found.getTotalPages()).isEqualTo(2);
        
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getNickname()).isEqualTo(memberInfoResponseDTO.getNickname());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getName()).isEqualTo(memberInfoResponseDTO.getName());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getPhoneNumber()).isEqualTo(memberInfoResponseDTO.getPhoneNumber());
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getEmail()).isEqualTo(memberInfoResponseDTO.getEmail());
        
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getCreatedDate()).isNotNull();
        assertThat(found.getContent().get(PAGE_SIZE / 2 - 1).getLastModifiedDate()).isNotNull();
    }
    
    @Test
    @DisplayName("아무 회원도 없는 경우 - 빈 페이지 반환")
    void When_SearchMembersByPaging_Expect_EmptyPage() {
        // given
        PageRequest request = PageRequest.of(0, PAGE_SIZE / 2, Sort.by(Sort.Order.desc("createdDate")));
        
        // when
        Page<MemberInfoResponseDTO> found = memberInfoService.searchmembersByPaging(request);
        
        // then
        assertThat(found).isNotNull();
        
        assertThat(found.getTotalElements()).isEqualTo(0);
    }
    
    @Test
    @DisplayName("멤버 업데이트 테스트 - 정상 수정 테스트")
    void When_UpdateMember_Expect_Success() {
        // given
        MemberEntity existMember = MemberEntity.builder()
                .password("ajsdi*(qjlekldjj23LKKk")
                .memberId("qkrtkdwns3410")
                .nickname(memberInfoResponseDTO.getNickname())
                .name(memberInfoResponseDTO.getName())
                .phoneNumber(memberInfoResponseDTO.getPhoneNumber())
                .email(memberInfoResponseDTO.getEmail())
                .createdDate(LocalDateTime.now().minusDays(1))
                .lastModifiedDate(LocalDateTime.now().minusDays(1))
                .build();
        
        tem.persist(existMember);
        
        // when
        MemberInfoResponseDTO updated = memberInfoService.update(existMember.getMemberId(), updateRequestDTO);
        
        // then
        assertThat(updated).isNotNull();
        
        assertThat(updated.getMemberId()).isEqualTo(existMember.getMemberId());
        assertThat(updated.getNickname()).isEqualTo(updateRequestDTO.getNickname());
        assertThat(updated.getName()).isEqualTo(existMember.getName());
        assertThat(updated.getPhoneNumber()).isEqualTo(updateRequestDTO.getPhoneNumber());
        assertThat(updated.getEmail()).isEqualTo(updateRequestDTO.getEmail());
        
        assertThat(updated.getCreatedDate()).isNotNull();
        assertThat(updated.getLastModifiedDate()).isNotNull().isAfter(now);
    }
    
    @Test
    @DisplayName("수정할 회원이 없는 경우 NOTFOUNDEXCEPTION")
    void When_UpdateMember_Expect_NotFoundException() {
        assertThatThrownBy(() -> memberInfoService.update("qkrtkdwns3410", updateRequestDTO))
                .isInstanceOf(NotFoundException.class)
                .hasMessage(ErrorCode.NOT_FOUND_MEMBER.getMessage());
    }
}