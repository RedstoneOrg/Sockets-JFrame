package caelum.first.workers.process;

import caelum.first.Console;
import caelum.first.Main;
import caelum.first.packet.PacketProcessEvent;
import caelum.first.packet.PacketReceiveProcessEvent;
import caelum.first.utils.Packet;
import caelum.first.utils.PacketIds;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;

public class ProcessThread extends Thread{

    private int id;
    private int subId;
    private String name;
    private Packet packet;

    private Object information;
    private Object object;
    private byte[] bytes;

    private Console console;

    public ProcessThread(Console console, Packet packet){
        this.console = console;

        this.id = packet.getId();
        this.subId = packet.getSubId();
        this.name = packet.getName();
        this.packet = packet;
    }

    @Override
    public void run() {
        try {

            information = packet.getInformation();
            object = packet.getObject();
            bytes = packet.getBytes();

            new PacketReceiveProcessEvent(console, packet);

            sleep(20);
            switch (id) {
                case PacketIds.TYPE_INFORMATION:
                    console.addText("ID: " + packet.getId() + " Nome: " + packet.getName() +
                            " " + "Informação: " + String.valueOf(information) +
                            " " + "Objeto: " + object, 2);

                    break;
                case PacketIds.TYPE_SHOW:
                    switch(subId){
                        case PacketIds.TYPE_SHOW_DISPLAY:
                            console.addText("Request de " + PacketIds.NAME_SHOW, 2);
                            JOptionPane.showMessageDialog(null, information, (String) object, 1);
                            break;
                        case PacketIds.TYPE_SHOW_CONSOLE:
                            console.addText("Request de " + PacketIds.NAME_SHOW, 2);
                            console.addText("Info: " + information, 2);
                            break;
                    }
                    break;
                case PacketIds.TYPE_PROCESS:
                    new PacketProcessEvent(console, packet);
                    break;
                case PacketIds.TYPE_BYTES:
                    String x = new String(bytes, "UTF-8");
                    console.addText("Bytes: " + x, 2);
                    break;
                case PacketIds.TYPE_FILE:
                    break;
            }
        } catch (Exception e){
            console.addText("Erro: " + e.getMessage());
            console.addText("Localized: " + e.getLocalizedMessage());
        }
    }
}
