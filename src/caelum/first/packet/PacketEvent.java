package caelum.first.packet;

import caelum.first.utils.Packet;

public class PacketEvent {

    private Packet packet;

    public PacketEvent(Packet packet){
        this.packet = packet;
    }

    public Packet getPacket() {
        return packet;
    }
}
