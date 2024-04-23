package org.commerce.commercesubmit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.member.domain.dto.request.MemberSignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberJoinResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.commerce.commercesubmit.member.service.helper.MemberSignInHelperService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

/**
 * packageName    : org.commerce.commercesubmit.member.service
 * fileName       : MemberSignInService
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberSignInService {
    private final MemberEntityRepository memberEntityRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional(readOnly = false)
    public MemberJoinResponseDTO join(@Valid MemberSignUpRequestDTO memberSignUpRequestDTO) {
        log.info("join request -- request memberId: {} ", memberSignUpRequestDTO.getMemberId());
        
        //exist 성능 관련 문서 - https://jojoldu.tistory.com/516
        MemberSignInHelperService.checkAlreadyExistMemberId(memberEntityRepository, memberSignUpRequestDTO.getMemberId());
        
        String encodePassword = MemberSignInHelperService.encodePassword(passwordEncoder, memberSignUpRequestDTO.getPassword());
        memberSignUpRequestDTO.setPassword(encodePassword);
        
        MemberEntity saved = memberEntityRepository.save(memberSignUpRequestDTO.toEntity());
        
        return saved.toMemberJoinResponseDTO();
    }
}