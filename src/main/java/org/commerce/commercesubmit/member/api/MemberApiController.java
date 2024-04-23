package org.commerce.commercesubmit.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.commerce.commercesubmit.common.response.ApiHttpResponse;
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
@Api(tags = "회원 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class MemberApiController {
    private final MemberSignInService memberSignInService;
    private final MemberInfoService memberInfoService;
    
    @ApiOperation(value = "회원 가입", notes = "회원 가입 API")
    @PostMapping("/join")
    public ApiHttpResponse<MemberJoinResponseDTO> join(@ApiParam(value = "회원 가입 정보", required = true) @Valid @RequestBody MemberSignUpRequestDTO memberSignUpRequestDTO) {
        log.info("member.join request -- request memberId: {} ", memberSignUpRequestDTO.getMemberId());
        
        MemberJoinResponseDTO joined = memberSignInService.join(memberSignUpRequestDTO);
        
        return ApiHttpResponse.success(HttpStatus.CREATED, joined);
    }
    
    @ApiOperation(value = "회원 목록 조회", notes = "회원 목록 조회 API")
    @GetMapping("/list")
    public ApiHttpResponse<Page<MemberInfoResponseDTO>> list(Pageable pageable) {
        log.info("member.list request pageSize : {} , pageNumber : {}", pageable.getPageNumber(), pageable.getPageNumber());
        
        Page<MemberInfoResponseDTO> foundMembers = memberInfoService.searchmembersByPaging(pageable);
        
        return ApiHttpResponse.success(HttpStatus.OK, foundMembers);
    }
    
    @ApiOperation(value = "회원 정보 수정", notes = "회원 정보 수정 API")
    @PutMapping("/{memberId}")
    public ApiHttpResponse<MemberInfoResponseDTO> update(@ApiParam(value = "회원ID", required = true, example = "qkrtkdwns3410") @PathVariable String memberId, @ApiParam(value = "회원 정보 수정 데이터", required = true) @Valid @RequestBody MemberUpdateRequestDTO updateRequestDTO) {
        log.info("member.update request -- request memberId: {} ", memberId);
        
        MemberInfoResponseDTO updated = memberInfoService.update(memberId, updateRequestDTO);
        
        return ApiHttpResponse.success(HttpStatus.OK, updated);
    }
}