package org.commerce.commercesubmit.member.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.common.response.ApiResponse;
import org.commerce.commercesubmit.member.domain.dto.request.MemberSignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.request.MemberUpdateRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberJoinResponseDTO;
import org.commerce.commercesubmit.member.service.MemberInfoService;
import org.commerce.commercesubmit.member.service.MemberSignInService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    private final MemberInfoService memberInfoService;
    
    @PostMapping("/join")
    public ApiResponse<MemberJoinResponseDTO> join(@Valid @RequestBody MemberSignUpRequestDTO memberSignUpRequestDTO) {
        log.info("member.join request -- request memberId: {} ", memberSignUpRequestDTO.getMemberId());
        
        MemberJoinResponseDTO joined = memberSignInService.join(memberSignUpRequestDTO);
        
        return ApiResponse.success(HttpStatus.CREATED, joined);
    }
    
    @GetMapping("/list")
    public ApiResponse<Page<MemberInfoResponseDTO>> list(Pageable pageable) {
        log.info("member.list request pageSize : {} , pageNumber : {}", pageable.getPageNumber(), pageable.getPageNumber());
        
        Page<MemberInfoResponseDTO> foundMembers = memberInfoService.searchmembersByPaging(pageable);
        
        return ApiResponse.success(HttpStatus.OK, foundMembers);
    }
    
    @PutMapping("/{memberId}")
    public ApiResponse<MemberInfoResponseDTO> update(@PathVariable String memberId, @Valid @RequestBody MemberUpdateRequestDTO updateRequestDTO) {
        log.info("member.update request -- request memberId: {} ", memberId);
        
        MemberInfoResponseDTO updated = memberInfoService.update(memberId, updateRequestDTO);
        
        return ApiResponse.success(HttpStatus.OK, updated);
    }
}