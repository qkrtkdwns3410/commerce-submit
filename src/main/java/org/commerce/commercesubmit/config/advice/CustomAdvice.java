package org.commerce.commercesubmit.config.advice;

import org.commerce.commercesubmit.common.exception.sub_exceptions.data_exceptions.BadRequestException;
import org.commerce.commercesubmit.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * packageName    : com.st.eighteen_be.config.exception
 * fileName       : CustomeAdvice
 * author         : ipeac
 * date           : 2024-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27        ipeac       최초 생성
 */
@RestControllerAdvice
public class CustomAdvice {
    
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ApiResponse<Object>> badRequestHandler(BadRequestException e) {
        ApiResponse<Object> fail = ApiResponse.fail(e.getErrorCode());
        return ResponseEntity.status(fail.getStatus()).body(fail);
    }
}