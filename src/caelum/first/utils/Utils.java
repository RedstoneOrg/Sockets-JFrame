package caelum.first.utils;

import caelum.first.Console;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

public class Utils {

    public static String stream(int id, String nome, String information, Console console){
        Gson gson = new Gson();
        Packet packet = new Packet();
        packet.setId(id);
        packet.setName(nome);
        packet.setInformation(information);
        String json = gson.toJson(packet);
        if(console != null) console.addText("Gerado: " + json);
        return json;
    }


}
