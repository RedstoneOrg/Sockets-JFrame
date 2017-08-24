package caelum.first.utils;

import caelum.first.Console;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String stream(int id, String nome, String information, Console console){
        return stream(id, 0, nome, information, null, null, 0, console);
    }

    public static String stream(int id, String nome, String information, Object object, Console console){
        return stream(id, 0, nome, information, object, null, 0, console);
    }

    public static String stream(int id, int subId, String nome, String information, Console console){
        return stream(id, subId, nome, information, null, null, 0, console);
    }

    public static String stream(int id, int subId, String nome, String information, Object object, Console console){
        return stream(id, subId, nome, information, object, null, 0, console);
    }

    public static String stream(int id, String nome, byte[] bytes, int length, Console console){
        return stream(id, 0, nome, null, null, bytes, length, console);
    }

    public static String stream(int id, int subId, String nome, String information, Object object, byte[] bytes, int length, Console console){
        Gson gson = new Gson();
        Packet packet = new Packet();

        packet.setId(id);
        packet.setSubId(subId);
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
