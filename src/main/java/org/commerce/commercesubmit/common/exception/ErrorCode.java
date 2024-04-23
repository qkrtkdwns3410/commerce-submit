package org.commerce.commercesubmit.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * packageName    : com.st.eighteen_be.config.exception
 * fileName       : ErrorCode
 * author         : ipeac
 * date           : 2024-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27        ipeac       최초 생성
 */
@Getter
public enum ErrorCode {
    //common
    NOT_NULL(HttpStatus.BAD_REQUEST, "필수 값이 누락되었습니다."),
    ALREADY_EXIST_MEMBER_ID(HttpStatus.BAD_REQUEST, "이미 존재하는 회원 ID 입니다."),
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
    private final HttpStatus status;
    private final String message;
    
    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}