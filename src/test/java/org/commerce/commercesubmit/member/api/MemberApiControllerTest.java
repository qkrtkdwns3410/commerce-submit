package org.commerce.commercesubmit.member.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.BadRequestException;
import org.commerce.commercesubmit.member.domain.dto.request.MemberSignUpRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.request.MemberUpdateRequestDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberInfoResponseDTO;
import org.commerce.commercesubmit.member.domain.dto.response.MemberJoinResponseDTO;
import org.commerce.commercesubmit.member.service.MemberInfoService;
import org.commerce.commercesubmit.member.service.MemberSignInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("회원 API 컨트롤러 테스트")
@WebMvcTest(MemberApiController.class)
public class MemberApiControllerTest {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private MemberSignInService memberSignInService;
    
    @MockBean
    private MemberInfoService memberInfoService;
    
    private MemberSignUpRequestDTO memberSignUpRequestDTO;
    
    @BeforeEach
    void setUp() {
        memberSignUpRequestDTO = MemberSignUpRequestDTO.builder()
                .memberId("qkrtkdwns3410")
                .password("ai1239Odj")
                .nickname("김밥천국")
                .name("박상준")
                .phoneNumber("010-3367-1123")
                .email("qkrtkdwns3410@gmail.com")
                .build();
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보가 없는 자가 회원가입을 시도하는 경우에 대한 정상 응답")
    void When_RequestJoin_Then_ReturnCreated() throws Exception {
        // given
        MemberJoinResponseDTO memberJoinResponseDTO = MemberJoinResponseDTO.builder()
                .memberId("qkrtkdwns3410")
                .build();
        
        given(memberSignInService.join(any(MemberSignUpRequestDTO.class))).willReturn(memberJoinResponseDTO);
        
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(memberSignUpRequestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.memberId").value("qkrtkdwns3410"));
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보가 존재하는 자가 회원가입을 시도하는 경우 BadRequest 반환")
    public void testJoinWhenInvalidRequestThenReturnBadRequest() throws Exception {
        // given
        given(memberSignInService.join(any(MemberSignUpRequestDTO.class)))
                .willThrow(new BadRequestException(ErrorCode.ALREADY_EXIST_MEMBER_ID));
        
        //when & then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(memberSignUpRequestDTO)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보 리스트 출력 - 회원정보가 있는 경우")
    void When_RequestList_Then_ReturnOk() throws Exception {
        //given
        List<MemberInfoResponseDTO> members = Arrays.asList(
                MemberInfoResponseDTO.builder()
                        .memberId("qkrtkdwns3410")
                        .build(),
                MemberInfoResponseDTO.builder()
                        .memberId("qkrtkdwns3411")
                        .build()
        );
        
        Page<MemberInfoResponseDTO> page = new PageImpl<>(members, PageRequest.of(0, 10), members.size());
        
        given(memberInfoService.searchmembersByPaging(any(Pageable.class))).willReturn(page);
        
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content[0].memberId").value("qkrtkdwns3410"))
                .andExpect(jsonPath("$.data.content[1].memberId").value("qkrtkdwns3411"));
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보 리스트 출력 - 회원정보가 없는 경우")
    void When_RequestList_Then_ReturnOk_EmptyList() throws Exception {
        //given
        List<MemberInfoResponseDTO> members = Collections.emptyList();
        
        Page<MemberInfoResponseDTO> page = new PageImpl<>(members, PageRequest.of(0, 10), 0);
        
        given(memberInfoService.searchmembersByPaging(any(Pageable.class))).willReturn(page);
        
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content").isEmpty());
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보 수정 - 정상 수정")
    void When_RequestUpdate_Then_ReturnOk() throws Exception {
        // given
        MemberInfoResponseDTO updatedMemberInfo = MemberInfoResponseDTO.builder()
                .memberId("qkrtkdwns3410")
                .nickname("수정된 김밥천국")
                .name("뉴박상준")
                .phoneNumber("010-1234-9999")
                .email("qkrtkdwns3410IsChanged@naver.com")
                .build();
        
        given(memberInfoService.update(any(String.class), any(MemberUpdateRequestDTO.class))).willReturn(updatedMemberInfo);
        
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{memberId}", "qkrtkdwns3410")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(memberSignUpRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.memberId").value("qkrtkdwns3410"))
                .andExpect(jsonPath("$.data.nickname").value("수정된 김밥천국"))
                .andExpect(jsonPath("$.data.name").value("뉴박상준"))
                .andExpect(jsonPath("$.data.phoneNumber").value("010-1234-9999"))
                .andExpect(jsonPath("$.data.email").value("qkrtkdwns3410IsChanged@naver.com"));
    }
    
    @Test
    @WithMockUser(username = "qkrtkdwns3410", roles = "USER")
    @DisplayName("회원정보 수정 - 존재하지 않는 회원의 정보 수정 요청")
    void When_RequestUpdate_Then_ReturnBadRequest() throws Exception {
        // given
        given(memberInfoService.update(any(String.class), any(MemberUpdateRequestDTO.class)))
                .willThrow(new BadRequestException(ErrorCode.NOT_FOUND_MEMBER));
        
        // when & then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{memberId}", "qkrtkdwns3410")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(memberSignUpRequestDTO)))
                .andExpect(status().isNotFound());
    }
}