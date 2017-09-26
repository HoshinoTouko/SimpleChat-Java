package moe.touko.SimpleChat.Server.Responser;

import moe.touko.SimpleChat.Model.Message;

import java.util.ArrayList;
import java.util.List;

public abstract class ResponderMessage extends Responser {
    protected List<Message> messages = new ArrayList<>();

    public List<Message> getDialogs() {
        return messages;
    }
}
