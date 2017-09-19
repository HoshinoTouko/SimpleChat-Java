package moe.touko.SimpleChat.Database;

import java.util.HashMap;

public class databaseTests {

    public static void main(String[] args) throws Exception {
        Database database = new Database(
                "C:\\Projects\\LearnJava\\SimpleChatService\\data\\database.db"
        );

        System.out.println("GetAllColumn...");

        System.out.println("Select...");
        System.out.println(database.select("ConversationEntity"));

        System.out.println("Insert...");
        HashMap<String, Object> conversationData = new HashMap<>();
        conversationData.put("id", 3);
        conversationData.put("text", "Test TEXT");
        conversationData.put("username", "Touko3333");
        conversationData.put("ip", "127.0.0.1");
        conversationData.put("time", "2017-09-10 12:23:34");
        database.insert("ConversationEntity", conversationData, false);

        System.out.println("Delete...");
        HashMap<String, Object> deleteData = new HashMap<>();
        deleteData.put("id", 1);
        database.delete("ConversationEntity", deleteData, false);

        System.out.println("Update...");
        HashMap<String, Object> updateData = new HashMap<>();
        HashMap<String, Object> whereData = new HashMap<>();
        whereData.put("id", 3);
        updateData.put("username", "Touko_AfterChange");
        database.update("ConversationEntity", updateData, whereData, true);

        System.out.println("Select2...");
        System.out.println(database.select("ConversationEntity"));

        System.out.println("Update2...");
        updateData.put("username", "Touko_NoChange");
        database.update("ConversationEntity", updateData, whereData, true);

        System.out.println("Select2...");
        System.out.println(database.select("ConversationEntity"));

    }

}
