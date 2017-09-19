package moe.touko.SimpleChat.Model.Conversation;

public abstract class ConversationEntity {
    protected long id;
    protected long room = 10000;
    protected String text;
    protected String username;
    protected String ip;
    protected String time;

    public boolean check(){
        return true;
    }
}
