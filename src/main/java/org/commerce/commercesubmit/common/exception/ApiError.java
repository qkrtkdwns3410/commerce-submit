package org.commerce.commercesubmit.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * packageName    : com.st.eighteen_be.config.exception
 * fileName       : ApiError
 * author         : ipeac
 * date           : 2024-03-27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-03-27        ipeac       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiError {
    private HttpStatus status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    
    @Builder
    private ApiError(HttpStatus status, String error, String message, String path, LocalDateTime timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }
}