package dev.orion.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
public class ExceptionMessageHolder{
    private List<Message> messages;

    public ExceptionMessageHolder(List<Message> messages){
        this.messages = messages;
    }

    public ExceptionMessageHolder(Message message){
        this.messages = Collections.singletonList(message);
    }

    public ExceptionMessageHolder(String code, Object ... params){
        messages = List.of(new Message(code, params));
    }

    @Getter
    public static class Message {
        private String code;
        private Object[] params;

        public Message(String code, Object[] params) {
            this.code = code;
            this.params = params;
        }

        public Message(String code){
            this.code = code;
        }

    }
}


