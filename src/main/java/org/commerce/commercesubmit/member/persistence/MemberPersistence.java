package org.commerce.commercesubmit.member.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * packageName    : org.commerce.commercesubmit.member.persistence
 * fileName       : MemberPersistence
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberPersistence {
    private final MemberEntityRepository memberEntityRepository;
    
    public Page<MemberInfoResponseDTO> searchmembersByPaging(Pageable pageable) {
        log.info("member.list request pageSize : {} , pageNumber : {}", pageable.getPageSize(), pageable.getPageNumber());
        
        return memberEntityRepository.findAll(pageable).map(MemberEntity::toMemberInfoResponseDTO);
    }
}