package cn.ctodb.push.dto;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @Copyright: 2018 www.ctodb.cn Inc. All rights reserved.
 */
@org.msgpack.annotation.Message
public class TextMessage extends Message {

    private String content;

    @Override
    public String toString() {
        return null;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Command getCmd() {
        return Command.TEXT_MESSAGE;
    }
}
