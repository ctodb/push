package cn.ctodb.push.dto;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
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
