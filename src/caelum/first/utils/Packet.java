package caelum.first.utils;

public class Packet {

    private int id;
    private int subId;

    private String name;
    private String information;

    private Object object;

    private byte[] bytes;
    private int length;

    public void setId(int id) {
        this.id = id;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public int getSubId() {
        return subId;
    }

    public String getName() {
        return name;
    }

    public String getInformation() {
        return information;
    }

    public Object getObject() {
        return object;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public int getLength() {
        return length;
    }
}
