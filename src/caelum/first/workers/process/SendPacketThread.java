package caelum.first.workers.process;

import caelum.first.Console;

import java.io.DataOutputStream;

public class SendPacketThread extends Thread {

    private DataOutputStream stream;

    private Console console;
    private String json;

    public SendPacketThread(Console console, String json, DataOutputStream stream){
        this.stream = stream;
        this.console = console;
        this.json = json;
    }

    @Override
    public void run() {
        try {
            sleep(1);
            stream.writeUTF(json);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
