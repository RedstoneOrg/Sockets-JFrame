package caelum.first.packet;

import caelum.first.Console;
import caelum.first.utils.Packet;

// Evento do Packet "TYPE_PROCESS"
public class PacketProcessEvent extends PacketEvent {

    private Console console;

    public PacketProcessEvent(Console console, Packet packet) {
        super(packet);
        this.console = console;
        onRun();
    }

    private void onRun(){
        new Thread(() -> {
            console.addText("Thread > PacketProcess is Running");
        }).start();
    }
}
