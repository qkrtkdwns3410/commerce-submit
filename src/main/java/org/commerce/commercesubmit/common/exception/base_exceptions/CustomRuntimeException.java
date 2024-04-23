package org.commerce.commercesubmit.common.exception.base_exceptions;

import lombok.Getter;
import org.commerce.commercesubmit.common.exception.ErrorCode;

/**
 * packageName    : com.st.eighteen_be.config.exception
 * fileName       : CustomeRuntimeException
 * author         : ipeac
 * date           : 2024-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27        ipeac       최초 생성
 */
@Getter
public class CustomRuntimeException extends RuntimeException {
    private final ErrorCode errorCode;
    
    public CustomRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}