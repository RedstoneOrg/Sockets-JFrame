package caelum.first.utils;

public interface PacketIds extends PacketSubIds{

    int TYPE_BATCH = -1;
    String NAME_BATCH = "BatchPacket";

    int TYPE_INFORMATION = 1;
    String NAME_INFORMATION = "InformationPacket";

    int TYPE_SHOW = 2;
    String NAME_SHOW = "ShowPacket";

    int TYPE_PROCESS = 3;
    String NAME_PROCESS = "ProcessPacket";

    int TYPE_BYTES = 4;
    String NAME_BYTES = "BytesPacket";

}
