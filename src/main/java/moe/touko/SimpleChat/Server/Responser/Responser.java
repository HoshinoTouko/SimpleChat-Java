package moe.touko.SimpleChat.Server.Responser;

public abstract class Responser {
    protected int status = 0;
    protected String msg = "";

    public String getMsg() {
        return msg;
    }

    public int getStatus() {
        return status;
    }

}
