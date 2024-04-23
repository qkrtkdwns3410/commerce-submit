package org.commerce.commercesubmit.member.service.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.BadRequestException;
import org.commerce.commercesubmit.member.repository.MemberEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
@Component
@RequiredArgsConstructor
@Slf4j
public class MemberSignInHelperService {
    
    public static void checkAlreadyExistMemberId(MemberEntityRepository repo, String memberId) {
        log.info("join request -- check already exist memberId: {}", memberId);
        
        if (repo.existsByMemberId(memberId)) {
            log.error("join request -- already exist memberId: {}", memberId);
            
            throw new BadRequestException(ErrorCode.ALREADY_EXIST_MEMBER_ID);
        }
    }
    
    public static String encodePassword(PasswordEncoder passwordEncoder, String oldPassword) {
        log.info("join request -- encode password");
        
        return passwordEncoder.encode(oldPassword);
    }
}