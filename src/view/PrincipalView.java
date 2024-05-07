package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalView extends JFrame {
    public PrincipalView() throws HeadlessException {
        setTitle("Concesionario");
        setSize(640, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem itemCoches = new JMenuItem("Coches");
        JMenuItem itemClientes = new JMenuItem("Clientes");
        itemCoches.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoView cocheView = new ProductoView();
                add(cocheView);
                cocheView.setVisible(true);
                repaint();
            }
        });

        itemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getContentPane().getComponents().length > 0){
                    getContentPane().remove(0);
                }
                ProductoView clienteView = new ProductoView();
                add(clienteView, BorderLayout.CENTER);
                clienteView.revalidate();

            }
        });

        JMenuItem itemSalir = new JMenuItem("Salir");
        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        String itemProducto;
        menu.add(itemProducto);
        menu.add(itemClientes);
        menu.addSeparator();
        menu.add(itemSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setVisible(true);
    }
}