package org.commerce.commercesubmit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.persistence.MemberPersistence;
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
    
    public Page<MemberInfoResponseDTO> searchmembersByPaging(Pageable pageable) {
        log.info("member.list request pageSize : {} , pageNumber : {}", pageable.getPageSize(), pageable.getPageNumber());
        
        return memberPersistence.searchmembersByPaging(pageable);
    }
}