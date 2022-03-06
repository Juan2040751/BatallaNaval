package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to graph the game
 * @autor Fabian Lopez
 * @autor Juan Jose Viafara
 * @autor Willian Velasco
 * @version V.2.0.0 date 26/01/2022
 */
public class GUIGridBagLayout extends JFrame {
    private Header headerProject;
    private JPanel panelIzquierdo, panelDerecho, tableroPosicion, panelEleccion;
    private ModelGame modelGame;
    private Escucha escucha;
    private JButton horizontal, vertical;
    private int interfaz, posicionFlota;
    private JButton[][] tableroPosicionU;
    private JTextArea cantidadFlotas;
    private int [] cantidadFlota;
    private String [] nombreFlota;
    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout(){
        interfaz=0;
        tableroPosicionU= new JButton[10][10];
        posicionFlota=0;
        nombreFlota= new String[]{"Portaaviones", "Submarinos", "Destructores", "Fragatas"};
        cantidadFlota= new int[]{1, 2, 3, 4};
        initGUI();
        //setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constrains = new GridBagConstraints();

        modelGame = new ModelGame();

        escucha = new Escucha();

        cantidadFlotas = new JTextArea(1, 5);


        headerProject = new Header("Posiciona tu flota de barcos", new Color(30, 101, 238));
        headerProject.setPreferredSize(new Dimension(11,40));
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constrains);

        panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setPreferredSize(new Dimension(480, 500));
        panelIzquierdo.setBackground(Color.lightGray);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelIzquierdo, constrains);

        tableroPosicion= new JPanel(new GridBagLayout());
        tableroPosicion.setPreferredSize(new Dimension(400, 400));
        tableroPosicion.setBackground(null);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelIzquierdo.add(tableroPosicion,constrains);
        pintarTableroPosicion();

        panelDerecho = new JPanel(new GridBagLayout());
        panelDerecho.setPreferredSize(new Dimension(360, 500));
        panelDerecho.setBackground(Color.lightGray);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelDerecho, constrains);

        panelEleccion= new JPanel(new GridBagLayout());
        panelEleccion.setPreferredSize(new Dimension(300, 350));
        panelEleccion.setBackground(new Color(55, 119, 54));
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(panelEleccion,constrains);

        pintarCantidadFlotas();
        cantidadFlotas.setFont(new Font(Font.DIALOG,Font.BOLD,20));
        cantidadFlotas.setBackground(null);
        cantidadFlotas.setEditable(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(cantidadFlotas,constrains);

        horizontal.addActionListener(escucha);
        horizontal.setBackground(null);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(horizontal, constrains);

        vertical.addActionListener(escucha);
        vertical.setBackground(null);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(vertical, constrains);
    }
    private void pintarTableroPosicion(){
        if(interfaz==0){
            GridBagConstraints constrainsPosicion = new GridBagConstraints();
            constrainsPosicion.weightx=40;
            constrainsPosicion.weighty=40;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionU[i][j] = new JButton();
                    tableroPosicionU[i][j].setBackground(new Color(30, 124, 236));
                    tableroPosicionU[i][j].addActionListener(escucha);
                    tableroPosicionU[i][j].setPreferredSize(new Dimension(40, 40));
                    tableroPosicionU[i][j].setFocusable(false);
                    constrainsPosicion.gridx = i;
                    constrainsPosicion.gridy = j;
                    constrainsPosicion.gridwidth = 1;
                    constrainsPosicion.fill = GridBagConstraints.NONE;
                    constrainsPosicion.anchor = GridBagConstraints.CENTER;
                    tableroPosicion.add(tableroPosicionU[i][j], constrainsPosicion);
                }
            }
            interfaz=1;
        }
    }
    private void pintarCantidadFlotas(){
        if (posicionFlota<4) {
            cantidadFlotas.setText("Tienes " + cantidadFlota[posicionFlota] + " " + nombreFlota[posicionFlota]);
            cantidadFlota[posicionFlota]--;
            if (cantidadFlota[posicionFlota] == 0) {
                posicionFlota++;
                if (posicionFlota<4){
                    pintarEleccion();
                }
            }
        }
    }
    private void pintarEleccion(){
        String direccion="";
        switch (nombreFlota[posicionFlota]){
            case "Portaaviones"-> direccion = "/resources/portaaviones.";
            case "Submarinos"  -> direccion = "/resources/submarino.";
            case "Destructores"-> direccion = "/resources/destructor.";
            case "Fragatas"    -> direccion = "/resources/fragata.";
        }
        horizontal= new JButton(new ImageIcon(getClass().getResource(direccion+"H.png")));
        vertical= new JButton(new ImageIcon(getClass().getResource(direccion+"V.png")));
    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class, in charge of monitoring the user's actions with the program and updating the interface and the game accordingly.
     */

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("hello");
        }
    }
}


