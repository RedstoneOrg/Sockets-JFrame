package caelum.first.workers;

import caelum.first.Console;
import caelum.first.utils.PacketIds;
import caelum.first.utils.Utils;
import caelum.first.workers.process.SendPacketThread;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread implements PacketIds {

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

                        sleep(1000);

                        String popup = Utils.stream(TYPE_SHOW, TYPE_SHOW_DISPLAY, NAME_SHOW,
                                "Pequeno POPUP", "Request", console);
                        String popup2 = Utils.stream(TYPE_SHOW, TYPE_SHOW_CONSOLE, NAME_SHOW,
                                "Console POPUP", console);
                        String process = Utils.stream(TYPE_PROCESS, NAME_PROCESS,
                                null, 3, console);
                        String bytes = Utils.stream(TYPE_BYTES, NAME_BYTES,
                                "Hello".getBytes(), "Hello".getBytes().length, console);

                        DataOutputStream outstream = new DataOutputStream(socket.getOutputStream());

                        SendPacketThread[] packetThreads = new SendPacketThread[]{
                                new SendPacketThread(console, popup, outstream),
                                new SendPacketThread(console, popup2, outstream),
                                new SendPacketThread(console, process, outstream),
                                new SendPacketThread(console, bytes, outstream),
                        };

                        for(int i = 0; i < packetThreads.length; i++) {
                            sleep(1);
                            packetThreads[i].start();
                        }

                        do {
                            sleep(3 * 1000);
                            String connection = Utils.stream(TYPE_BATCH, NAME_BATCH, null, null);
                            new SendPacketThread(console, connection, outstream).start();
                        } while(socket.isConnected() && socket.getInputStream() != null);

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
