package cn.ctodb.push.dto;

/**
 * Created by cc on 2017/7/7.
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
