package moe.touko.SimpleChat.Database;

import java.util.HashMap;
import java.util.Map;

public class databaseTests {

    public static void main(String[] args) throws Exception {
        Database database = new Database(
                "C:\\Projects\\LearnJava\\SimpleChatService\\data\\database.db"
        );

        System.out.println("GetAllColumn...");

        System.out.println("Select...");
        System.out.println(database.select("Conversation"));

        System.out.println("Insert...");
        HashMap<String, Object> conversationData = new HashMap<>();
        conversationData.put("id", 3);
        conversationData.put("text", "Test TEXT");
        conversationData.put("people", "Touko3333");
        conversationData.put("ip", "127.0.0.1");
        conversationData.put("time", "2017-09-10 12:23:34");
        database.insert("Conversation", conversationData, false);

        System.out.println("Delete...");
        HashMap<String, Object> deleteData = new HashMap<>();
        deleteData.put("id", 1);
        database.delete("Conversation", deleteData, false);

        System.out.println("Update...");
        HashMap<String, Object> updateData = new HashMap<>();
        HashMap<String, Object> whereData = new HashMap<>();
        whereData.put("id", 3);
        updateData.put("people", "Touko_AfterChange");
        database.update("Conversation", updateData, whereData, true);

        System.out.println("Select2...");
        System.out.println(database.select("Conversation"));

        System.out.println("Update2...");
        updateData.put("people", "Touko_NoChange");
        database.update("Conversation", updateData, whereData, true);

        System.out.println("Select2...");
        System.out.println(database.select("Conversation"));

    }

}
