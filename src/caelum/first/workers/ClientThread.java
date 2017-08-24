package caelum.first.workers;

import caelum.first.Console;
import caelum.first.utils.Packet;
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

public class ClientThread extends Thread {

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

                sleep(1);
                new ProcessThread(console, packet).start();
            }
        } catch (Exception e){
            console.addText("Erro!\n" + e.getMessage());
        }
    }
}
