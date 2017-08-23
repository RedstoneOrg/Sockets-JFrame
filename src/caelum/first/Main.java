package caelum.first;


import javax.swing.*;
import java.util.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        String texto;

        Object type = Integer.parseInt(JOptionPane.showInputDialog(null, "Iniciar\nCliente: 0\nServidor: 1\nIniciar os dois: 2"));

        if((int) type > 2 || (int) type < 0 || type == null) return;

        if((int) type == 2){
            Console console = new Console(0);
            console.setVisible(true);
            console.gui();
            Console console2 = new Console(1);
            console2.setVisible(true);
            console2.gui();
        } else {
            Console console = new Console((int) type);
            console.setVisible(true);
            console.gui();
        }
    }



}