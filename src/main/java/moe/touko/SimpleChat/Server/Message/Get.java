package moe.touko.SimpleChat.Server.Message;

import moe.touko.SimpleChat.Model.Message;
import moe.touko.SimpleChat.Server.Responser.ResponderMessage;

import java.util.List;

public class Get extends ResponderMessage {
    private List<Message> Dialogs;
}
