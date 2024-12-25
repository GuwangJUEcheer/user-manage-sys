package hokumei.sys.usermanagesys.exception;

import hokumei.sys.usermanagesys.common.ErrorCode;
import lombok.Getter;

/**
 * 自定义异常类
 *
 * @author guwangjue
 */
@Getter
public class BusinessException extends RuntimeException {

    private final int code;
    private final String description;
    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode,String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}
