package caelum.first.workers;

import caelum.first.Console;
import caelum.first.utils.Packet;
import caelum.first.utils.PacketIds;
import caelum.first.utils.Utils;
import caelum.first.workers.process.ProcessThread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.net.Socket;

public class ClientThread extends Thread implements PacketIds{

    private Console console;
    public Socket socket;

    public ClientThread(Console console){
        this.console = console;
        this.setName("Thread - Client: ");
    }

    @Override
    public void run() {
        try {
            console.addText("Conectando...");
            socket = new Socket("127.0.0.1", 24464);
            console.addText("Conectado.");

            while (socket.isConnected() && socket.getInputStream() != null){
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                String utf = inputStream.readUTF();
                Gson gson = new Gson();
                Packet packet = gson.fromJson(utf, Packet.class);

                switch(packet.getId()){
                    case PacketIds.TYPE_BATCH:
                        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                        String utd = Utils.stream(TYPE_BATCH, NAME_BATCH, null, null);
                        output.writeUTF(utd);
                        break;
                }

                sleep(1);
                new ProcessThread(console, packet).start();
            }
        } catch (Exception e){
            console.addText("Erro!\n" + e.getMessage());
        }
    }
}
