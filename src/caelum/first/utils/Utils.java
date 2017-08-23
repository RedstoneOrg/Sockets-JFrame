package caelum.first.utils;

import caelum.first.Console;
import com.google.gson.Gson;

public class Utils {

    public static String stream(int id, String nome, String information, Console console){
        return stream(id, nome, information, null, null, 0, console);
    }

    public static String stream(int id, String nome, String information, Object object, Console console){
        return stream(id, nome, information, object, null, 0, console);
    }

    public static String stream(int id, String nome, byte[] bytes, int length, Console console){
        return stream(id, nome, null, null, bytes, length, console);
    }

    public static String stream(int id, String nome, String information, Object object, byte[] bytes, int length, Console console){
        Gson gson = new Gson();
        Packet packet = new Packet();
        packet.setId(id);
        packet.setName(nome);
        packet.setInformation(information);
        packet.setObject(object);
        packet.setBytes(bytes);
        packet.setLength(length);
        String json = gson.toJson(packet);
        if(console != null) console.addText("Gerado: " + json);
        return json;
    }


}
