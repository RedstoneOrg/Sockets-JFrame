package caelum.first.workers.process;

import caelum.first.Console;
import caelum.first.utils.Packet;
import caelum.first.utils.PacketIds;

public class ProcessThread extends Thread{

    private int id;
    private String name;
    private Packet packet;

    private Object object;

    private Console console;

    public ProcessThread(Console console, Packet packet){
        this.console = console;

        this.id = packet.getId();
        this.name = packet.getName();
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch(id){
            case PacketIds.TYPE_INFORMATION:

                object = packet.getInformation();

                console.addText("ID: " + packet.getId());
                console.addText("Nome: " + packet.getName());
                console.addText("Informação: " + String.valueOf(object));

                break;
        }
    }
}
