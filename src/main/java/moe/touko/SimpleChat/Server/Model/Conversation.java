package moe.touko.SimpleChat.Server.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Conversation {

    private Long id;
    private String text;
    private String people;
    private String ip;
    private String time;


}
