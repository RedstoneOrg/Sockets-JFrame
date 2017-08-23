package caelum.first.utils;

import caelum.first.Console;
import com.google.gson.Gson;

public class Utils {

    public static String stream(int id, String nome, String information, Console console){
        return stream(id, nome, information, null, console);
    }

    public static String stream(int id, String nome, String information, Object object, Console console){
        Gson gson = new Gson();
        Packet packet = new Packet();
        packet.setId(id);
        packet.setName(nome);
        packet.setInformation(information);
        packet.setObject(object);
        String json = gson.toJson(packet);
        if(console != null) console.addText("Gerado: " + json);
        return json;
    }


}
