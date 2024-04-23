package org.commerce.commercesubmit.member.repository;

import org.commerce.commercesubmit.member.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberEntityRepository extends JpaRepository<MemberEntity, Long> {
    boolean existsByMemberId(String memberId);
}