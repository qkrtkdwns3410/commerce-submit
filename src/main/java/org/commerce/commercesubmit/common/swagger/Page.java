package org.commerce.commercesubmit.common.swagger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * packageName    : org.commerce.commercesubmit.common.swagger
 * fileName       : Page
 * author         : ipeac
 * date           : 24. 4. 24.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 24.        ipeac       최초 생성
 */
@Getter
@Setter
@ApiModel
public class Page {
    @ApiModelProperty(value = "페이지 번호", example = "0")
    private int page;
    
    @ApiModelProperty(value = "페이지 크기", example = "10")
    private int size;
    
    @ApiModelProperty(value = "정렬 ( 컬럼명,ASC/DESC )", example = "name,ASC")
    private List<String> sort;
}