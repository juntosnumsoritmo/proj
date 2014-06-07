package br.com.copa.juntosnumsoritmo.validador;

import java.util.LinkedList;
import java.util.List;

public class ValidadorMessage {

    private List<Mensagem> messages = new LinkedList<Mensagem>();

    public List<Mensagem> getMessages() {
        return messages;
    }

    public void setMessages(List<Mensagem> messages) {
        this.messages = messages;
    }

    public void addMessages(List<Mensagem> messages) {
        getMessages().addAll(messages);
    }

    public void addMessage(Mensagem message) {
        getMessages().add(message);
    }

    public Boolean existemErros() {
        return getMessages() != null && !getMessages().isEmpty();
    }

}
