package org.commerce.commercesubmit.common.response;

import lombok.Getter;
import org.commerce.commercesubmit.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * packageName    : org.commerce.commercesubmit.common.exception
 * fileName       : ApiResponse
 * author         : ipeac
 * date           : 24. 4. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 12.        ipeac       최초 생성
 */
@Getter
public class ApiResponse<T> {
    private final HttpStatus status;
    private final T data;
    private final String message;
    
    public ApiResponse(HttpStatus status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
    
    public static <T> ApiResponse<T> success(HttpStatus status, T data) {
        return new ApiResponse<>(status, data, "success");
    }
    
    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse<>(errorCode.getStatus(), null, errorCode.getMessage());
    }
}