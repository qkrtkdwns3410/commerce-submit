package org.commerce.commercesubmit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.member.domain.dto.request.SignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberEntityResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.commerce.commercesubmit.member.service.helper.MemberSignInHelperService;
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
    
    @Transactional(readOnly = false)
    public MemberEntityResponseDTO join(@Valid SignUpRequestDTO signUpRequestDTO) {
        log.info("join request -- request memberId: {} ", signUpRequestDTO.getMemberId());
        
        //exist 성능 관련 문서 - https://jojoldu.tistory.com/516
        MemberSignInHelperService.checkAlreadyExistMemberId(memberEntityRepository, signUpRequestDTO.getMemberId());
        
        MemberEntity saved = memberEntityRepository.save(signUpRequestDTO.toEntity());
        
        return saved.toResponseDTO();
    }
}