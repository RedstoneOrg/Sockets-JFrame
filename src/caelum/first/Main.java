package caelum.first;


import caelum.first.utils.Utils;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public final static String PATH = System.getProperty("user.dir") + "/";

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
            console2.addButtons();
        } else {
            Console console = new Console((int) type);
            console.setVisible(true);
            console.gui();
            if((int) type == 1){
                console.addButtons();
            }
        }
    }



}