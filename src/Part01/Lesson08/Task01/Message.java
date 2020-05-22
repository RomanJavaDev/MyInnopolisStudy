package Part01.Lesson08.Task01;

import java.io.Serializable;

/**
 * Message.
 * Сообщение Message - это данные, которые одна сторона отправляет, а вторая принимает
 *
 * @author Roman Khokhlov
 */
public class Message implements Serializable {

    private final MessageType type;  // тип сообщения
    private final String data;  // данные сообщения

    public Message(MessageType type) {
        this.type = type;
        this.data = null;
    }

    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }

}
