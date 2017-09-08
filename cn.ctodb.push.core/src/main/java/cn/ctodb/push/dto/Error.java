package cn.ctodb.push.dto;

/**
 * Created by cc on 2017/8/11.
 */
@org.msgpack.annotation.Message
public class Error {

    public byte code;
    public String msg;

    public void setCode(ErrorCode errorCode, String msg) {
        code = errorCode.errorCode;
        this.msg = msg;
    }

    public ErrorCode getErrorCode() {
        return ErrorCode.toEnum(code);
    }
}
