package caelum.first.workers.process;

import caelum.first.Console;
import caelum.first.utils.Packet;
import caelum.first.utils.PacketIds;
import caelum.first.utils.Utils;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ServerProcess extends Thread implements PacketIds{

    private DataInputStream stream;
    private DataOutputStream output;

    public ServerProcess(DataInputStream stream, DataOutputStream output){
        this.stream = stream;
        this.output = output;
    }

    @Override
    public void run() {
        String utf = null;
        try {
            utf = stream.readUTF();
            Gson gson = new Gson();
            Packet packet = gson.fromJson(utf, Packet.class);

            switch(packet.getId()){
                case TYPE_BATCH:
                    String utfd = Utils.stream(TYPE_BATCH, NAME_BATCH, null, null);
                    output.writeUTF(utfd);
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
