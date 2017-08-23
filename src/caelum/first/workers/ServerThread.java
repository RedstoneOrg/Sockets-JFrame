package caelum.first.workers;

import caelum.first.Console;
import caelum.first.utils.PacketIds;
import caelum.first.utils.Utils;
import caelum.first.workers.process.SendPacketThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {

    private InetSocketAddress address;
    private Console console;
    public Socket socket;

    public ServerThread(InetSocketAddress inetSocketAddress, Console console) {
        this.address = inetSocketAddress;
        this.console = console;
        this.setName("Thread - Server: ");
    }

    @Override
    public void run() {
        try{
            try(ServerSocket server = new ServerSocket()){
                server.bind(this.address);
                while(!server.isClosed()){
                    try {
                        console.addText("Conexão criada, a espera de um Client");
                        socket = server.accept();
                        console.addText("Client: " + socket.getInetAddress().getHostAddress());
                        sleep(2 * 1000);

                        String json = Utils.stream(1, PacketIds.NAME_INFORMATION,"Olá", console);
                        String json2 = Utils.stream(1, PacketIds.NAME_INFORMATION,"Eu sou", console);
                        String json3 = Utils.stream(1, PacketIds.NAME_INFORMATION,"o " + PacketIds.NAME_INFORMATION, console);
                        String objected = Utils.stream(1, PacketIds.NAME_INFORMATION,"o " + PacketIds.NAME_INFORMATION, 3, console);

                        DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());

                        SendPacketThread[] packetThreads = new SendPacketThread[]{
                                new SendPacketThread(console, json, outstream),
                                new SendPacketThread(console, json2, outstream),
                                new SendPacketThread(console, json3, outstream),
                                new SendPacketThread(console, objected, outstream),
                        };

                        for(int i = 0; i < packetThreads.length; i++){
                            sleep(1);
                            packetThreads[i].start();
                        }

                        sleep(2 * 1000);

                    } catch (Exception e){
                        console.addText("Erro!\n" + e.getMessage());
                    }

                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
