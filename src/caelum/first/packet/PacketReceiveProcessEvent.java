package caelum.first.packet;

import caelum.first.Console;
import caelum.first.utils.*;

public class PacketReceiveProcessEvent extends PacketEvent implements PacketIds{

    private Console console;

    public PacketReceiveProcessEvent(Console console, Packet packet) {
        super(packet);
        this.console = console;
        onRun();
    }

    private void onRun(){
        new Thread(() -> {
            try {
                Thread.sleep(1);
            } catch (Exception e){
                console.addText("Erro: " + e.getMessage());
            }
            String recv = "";

            switch(getPacket().getId()) {
                case TYPE_BATCH:
                    recv = NAME_BATCH;
                    break;
                case TYPE_INFORMATION:
                    recv = NAME_INFORMATION;
                    break;
                case TYPE_SHOW:
                    recv = NAME_SHOW;
                    break;
                case TYPE_PROCESS:
                    recv = NAME_PROCESS;
                    break;
                case TYPE_BYTES:
                    recv = NAME_BYTES;
                    break;
            }
            console.addText("Recebido: " + recv);
        }).start();
    }
}
