package org.commerce.commercesubmit.member.service;

import org.commerce.commercesubmit.common.annotation.CustomedTestRunner;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.BadRequestException;
import org.commerce.commercesubmit.member.domain.dto.request.MemberSignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberJoinResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.commerce.commercesubmit.member.service.helper.MemberSignInHelperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mockStatic;

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
@DisplayName("회원 가입 서비스 테스트")
@CustomedTestRunner
@ExtendWith(MockitoExtension.class)
class MemberSignInServiceTest {
    
    @Autowired
    private TestEntityManager tem;
    
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    private MemberSignInService memberSignInService;
    
    private MemberSignUpRequestDTO correctUserSignUpDTO;
    
    @BeforeEach
    void setUp() {
        memberSignInService = new MemberSignInService(memberEntityRepository, passwordEncoder);
        correctUserSignUpDTO = MemberSignUpRequestDTO.builder()
                .memberId("qkrtkdwns3410")
                .password("23ia*923oSDHnK#")
                .nickname("김밥천국지배자")
                .phoneNumber("010-1234-5678")
                .name("박상준")
                .email("qkrtkdwns@naver.com")
                .build();
    }
    
    @Test
    @DisplayName("회원가입이 정상적으로 이루어지는 경우 정보 확인")
    void When_JoinMember_Expect_MemberInfo() {
        try (MockedStatic<MemberSignInHelperService> mockedStatic = mockStatic(MemberSignInHelperService.class)) {
            //given
            mockedStatic.when(() -> MemberSignInHelperService.encodePassword(passwordEncoder, correctUserSignUpDTO.getPassword()))
                    .thenReturn(correctUserSignUpDTO.getPassword());
            
            //when
            MemberJoinResponseDTO joined = memberSignInService.join(correctUserSignUpDTO);
            
            //then
            MemberEntity found = tem.find(MemberEntity.class, joined.getId());
            assertThat(found.getMemberId()).isEqualTo(correctUserSignUpDTO.getMemberId());
            assertThat(found.getNickname()).isEqualTo(correctUserSignUpDTO.getNickname());
            assertThat(found.getPhoneNumber()).isEqualTo(correctUserSignUpDTO.getPhoneNumber());
            assertThat(found.getName()).isEqualTo(correctUserSignUpDTO.getName());
            assertThat(found.getEmail()).isEqualTo(correctUserSignUpDTO.getEmail());
        }
    }
    
    @Test
    @DisplayName("이미 해당 회원이 존재하는 경우 회원가입 실패 예외 발생")
    void When_JoinMember_Expect_Exception() {
        //given
        tem.persist(correctUserSignUpDTO.toEntity());
        MemberSignUpRequestDTO alreadyExistUserSignUpDTO = correctUserSignUpDTO;
        
        //when - then
        assertThatThrownBy(() -> memberSignInService.join(alreadyExistUserSignUpDTO))
                .isInstanceOf(BadRequestException.class)
                .hasMessage(ErrorCode.ALREADY_EXIST_MEMBER_ID.getMessage());
    }
}