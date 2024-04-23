package org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions;

import lombok.Getter;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.commerce.commercesubmit.common.exception.base_exceptions.CustomRuntimeException;

/**
 * packageName    : com.st.eighteen_be.config.exception.sub_exceptions.data_exceptions
 * fileName       : NotFoundException
 * author         : ipeac
 * date           : 2024-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27        ipeac       최초 생성
 */
@Getter
public class NotFoundException extends CustomRuntimeException {
    
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}