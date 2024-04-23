package org.commerce.commercesubmit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.NotFoundException;
import org.commerce.commercesubmit.member.domain.dto.request.MemberUpdateRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.persistence.MemberPersistence;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : org.commerce.commercesubmit.member.service
 * fileName       : MemberInfoService
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberInfoService {
    private final MemberPersistence memberPersistence;
    private final MemberEntityRepository memberEntityRepository;
    
    public Page<MemberInfoResponseDTO> searchmembersByPaging(Pageable pageable) {
        log.info("member.list request pageSize : {} , pageNumber : {}", pageable.getPageSize(), pageable.getPageNumber());
        
        return memberPersistence.searchmembersByPaging(pageable);
    }
    
    @Transactional(readOnly = false)
    public MemberInfoResponseDTO update(String memberId, MemberUpdateRequestDTO updateRequestDTO) {
        log.info("update request -- request memberId: {} ", memberId);
        
        MemberEntity foundMember = memberEntityRepository.findByMemberId(memberId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_MEMBER));
        
        MemberEntity updated = foundMember.update(updateRequestDTO);
        
        return updated.toMemberInfoResponseDTO();
    }
}