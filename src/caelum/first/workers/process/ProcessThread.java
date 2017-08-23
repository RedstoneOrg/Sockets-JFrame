package caelum.first.workers.process;

import caelum.first.Console;
import caelum.first.packet.PacketProcessEvent;
import caelum.first.packet.PacketReceiveProcessEvent;
import caelum.first.utils.Packet;
import caelum.first.utils.PacketIds;

import javax.swing.*;
import java.awt.*;

public class ProcessThread extends Thread{

    private int id;
    private String name;
    private Packet packet;

    private Object information;
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

            information = packet.getInformation();
            object = packet.getObject();

            new PacketReceiveProcessEvent(console, packet);

            sleep(4);
            switch (id) {
                case PacketIds.TYPE_INFORMATION:
                    console.addText("ID: " + packet.getId() + " Nome: " + packet.getName() +
                            " " + "Informação: " + String.valueOf(information) +
                            " " + "Objeto: " + object);

                    break;
                case PacketIds.TYPE_SHOW:
                    console.addText("Request de " + PacketIds.NAME_SHOW);
                    JOptionPane.showMessageDialog(null, information, (String) object, 1);
                    sleep(3);
                    break;
                case PacketIds.TYPE_PROCESS:
                    new PacketProcessEvent(console, packet);
                    break;
                case PacketIds.TYPE_BYTES:
                    String x = new String(packet.getBytes(), "UTF-8");
                    console.addText("Bytes: " + x);
                    break;
            }
        } catch (Exception e){
            console.addText("Erro: " + e.getMessage());
            console.addText("Localized: " + e.getLocalizedMessage());
        }
    }
}
