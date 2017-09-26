package moe.touko.SimpleChat.Common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Common {

    public static String byte2Hex(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        String temp = null;
        for (byte byt: bytes){
            temp = Integer.toHexString(byt & 0xFF);
            if (temp.length()==1){
                stringBuilder.append("0");
            }
            stringBuilder.append(temp);
        }
        return stringBuilder.toString();
    }

    public static String BeautifyDictionary(Map<String, Object> data){
        ArrayList<String> resultList = new ArrayList<>();
        String resultString;
        for (Map.Entry<String, Object> entry: data.entrySet()){
            String formatString = Common.isNumber(entry.getValue()) ? "%s" : "\"%s\"";
            resultList.add(
                    entry.getKey() + "=" + String.format(formatString, entry.getValue())
            );
        }
        resultString = String.join(", ", resultList);
        return resultString;
    }

    public static String BeautifyDictionary(Map<String, Object> data, ArrayList<String> keys){
        ArrayList<String> resultList = new ArrayList<>();
        String resultString;
        for (Iterator<String> iter = keys.iterator(); iter.hasNext(); ){
            String column = (String)iter.next();
            try{
                data.get(column);
                if (Common.isNumber(data.get(column))) {
                    resultList.add(String.format("%s", data.get(column)));
                }
                else{
                    resultList.add(String.format("\"%s\"", data.get(column)));
                }
            }
            catch (Exception e){
                System.out.println(String.format("Error! Info is %s", e.getMessage()));
                resultList.add("");
            }
        }
        resultString = String.join(", ", resultList);
        return resultString;
    }

    public static Boolean isNumber(Object object){
        return (
                object instanceof Short ||
                        object instanceof Integer ||
                        object instanceof Long ||
                        object instanceof Float ||
                        object instanceof Double
                );
    }
}
