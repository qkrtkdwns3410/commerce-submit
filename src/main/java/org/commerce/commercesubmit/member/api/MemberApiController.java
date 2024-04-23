package org.commerce.commercesubmit.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.common.response.ApiResponse;
import org.commerce.commercesubmit.member.domain.dto.request.SignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberEntityResponseDTO;
import org.commerce.commercesubmit.member.service.MemberSignInService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * packageName    : org.commerce.commercesubmit.member.api
 * fileName       : MemberApiController
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {
    private final MemberSignInService memberSignInService;
    
    @PostMapping("/join")
    public ApiResponse<Object> join(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        log.info("join request -- request memberId: {} ", signUpRequestDTO.getMemberId());
        
        MemberEntityResponseDTO joined = memberSignInService.join(signUpRequestDTO);
        
        return ApiResponse.success(HttpStatus.CREATED, joined);
    }
}