package caelum.first;

import caelum.first.utils.PacketIds;
import caelum.first.utils.PacketSubIds;
import caelum.first.utils.Utils;
import caelum.first.workers.ClientThread;
import caelum.first.workers.ServerThread;
import caelum.first.workers.process.SendPacketThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Console extends JFrame {

    private JTextArea textArea;
    private Thread thread;

    private int type;
    private String area = "";

    public Console(int type){
        super("Console");
        this.type = type;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 300);
        setLocationRelativeTo(null);

        textArea = new JTextArea(area);
        textArea.setLineWrap(true); //quebra de linha automática
        textArea.setEditable(false);
        textArea.addKeyListener(new KeyT(this));
        textArea.setBackground(Color.BLACK);
        textArea.setFont(new Font("Consolas", Font.BOLD, 13));
        textArea.setForeground(Color.WHITE);


        JScrollPane scroll = new JScrollPane(textArea);

        //add(textArea);
        getContentPane().add(scroll);
    }

    public JTextArea getText(){
        return textArea;
    }

    public void gui(){
        switch(type){
            case 0:
                thread = new ClientThread(this);
                break;
            case 1:
                thread = new ServerThread(new InetSocketAddress("127.0.0.1", 24464), this);
                break;
            default:
                thread = null;
                break;
        }
        assert thread != null;
        thread.start();
        setTitle("Console: " + thread.getName().replace(":", ""));
    }

    public void addText(String text){
        addText(text, 1);
    }

    public void addText(String text, int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String addText = "";
        addText += textArea.getText();
        addText += "\n" + text;
        area = addText;
        textArea.setText(addText);
    }

    public class KeyT implements KeyListener {

        private Console frame;

        public KeyT(Console frame){
            this.frame = frame;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed (KeyEvent e){
            /*if((e.getModifiers() & KeyEvent.CTRL_MASK) != 0 && e.getKeyCode() == KeyEvent.VK_S){
                    try {
                        FileOutputStream out = new FileOutputStream(file);
                        out.write(texto.getBytes());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }*/
            if ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_C:
                        new Thread(() -> {
                            if(thread instanceof ClientThread){
                                this.frame.addText("Conexão Encerrando!");
                                try {
                                    Thread.sleep(250);
                                    ((ClientThread) thread).socket.close();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }


                                System.exit(0);


                            } else if (thread instanceof ServerThread){
                                this.frame.addText("Conexão Encerrando!");
                                try {
                                    Thread.sleep(250);
                                    ((ServerThread) thread).socket.close();
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }

                                System.exit(0);

                            }
                        }).start();
                        break;
                    case KeyEvent.VK_0:
                        if(thread instanceof ServerThread){
                            DataOutputStream out = ((ServerThread) thread).outstream;
                            int id = Integer.parseInt(JOptionPane.showInputDialog(null, "ID do Packet", "CustomPacket", 3));
                            int subid = Integer.parseInt(JOptionPane.showInputDialog(null, "SubID do Packet", "CustomPacket", 3));
                            String string = JOptionPane.showInputDialog(null, "Mensagem que irá aparecer", "CustomPacket", 3);

                            if(id > 2){
                                this.frame.addText("ID do Pacote acima de 2");
                                this.frame.addText("Só é aceito Pacote 1 e 2 (InformationPacket e ShowPackey)");
                                return;
                            }

                            String utf = Utils.stream(id, subid, "CustomPacket", string, this.frame);
                            new SendPacketThread(this.frame, utf, out).start();
                        }
                        break;
                    case KeyEvent.VK_D:
                        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Tamanho da Fonte(10 até 20)"));
                        if(id < 10 || id > 20){
                            this.frame.addText("Fonte menor que 10 ou maior que 20!");
                            return;
                        }
                        this.frame.setFont(new Font("Consolas", Font.BOLD, id));
                        break;
                }
            }
        }

        @Override
        public void keyReleased (KeyEvent e){

        }
    }

}
