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
    private JPanel panelIzquierdo, panelDerecho, tableroPosicion, panelEleccion, tableroPrincipal;
    private ModelGame modelGame;
    private Escucha escucha;
    private JButton horizontal, vertical, iniciar, territorioEnemigo;
    private int interfaz, posicionFlota;
    private JButton[][] tableroPosicionU, tableroPosicionM;
    private JTextArea cantidadFlotas;
    private int[] cantidadFlota;
    private String[] nombreFlota;
    private String orientacion, tipoFlota;
    private GridBagConstraints constrains;

    /**
     * Constructor of GUI class
     */
    public GUIGridBagLayout() {
        interfaz = 0;
        tableroPosicionU = new JButton[10][10];
        tableroPosicionM = new JButton[10][10];
        posicionFlota = 0;
        nombreFlota = new String[]{"Portaaviones", "Submarinos", "Destructores", "Fragatas"};
        cantidadFlota = new int[]{1, 2, 3, 4};
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
        constrains = new GridBagConstraints();

        modelGame = new ModelGame();

        escucha = new Escucha();

        cantidadFlotas = new JTextArea(1, 5);

        horizontal = new JButton();
        vertical = new JButton();

        headerProject = new Header("Posiciona tu flota de barcos", new Color(30, 101, 238));
        headerProject.setPreferredSize(new Dimension(11, 40));
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constrains);

        panelIzquierdo = new JPanel(new GridBagLayout());
        panelIzquierdo.setPreferredSize(new Dimension(480, 500));
        panelIzquierdo.setBackground(Color.lightGray);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        add(panelIzquierdo, constrains);

        tableroPosicion = new JPanel(new GridBagLayout());
        tableroPosicion.setPreferredSize(new Dimension(400, 400));
        tableroPosicion.setBackground(null);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelIzquierdo.add(tableroPosicion, constrains);
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

        panelEleccion = new JPanel(new GridBagLayout());
        panelEleccion.setPreferredSize(new Dimension(300, 350));
        panelEleccion.setBackground(new Color(55, 119, 54));
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelDerecho.add(panelEleccion, constrains);

        pintarPanelEleccion();
        cantidadFlotas.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        cantidadFlotas.setBackground(null);
        cantidadFlotas.setEditable(false);
        constrains.gridx = 0;
        constrains.gridy = 0;
        constrains.gridwidth = 2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(cantidadFlotas, constrains);

        pintarOpcionAlineacion();
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

        /**
         territorioEnemigo = new JButton("Territorio enemigo");
         territorioEnemigo.addActionListener(escucha);
         */

    }

    private void pintarTableroPosicion() {
        if (interfaz == 0) {
            GridBagConstraints constrainsPosicion = new GridBagConstraints();
            constrainsPosicion.weightx = 40;
            constrainsPosicion.weighty = 40;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    tableroPosicionU[i][j] = new JButton();
                    tableroPosicionU[i][j].setBackground(new Color(30, 124, 236));
                    tableroPosicionU[i][j].setPreferredSize(new Dimension(40, 40));
                    constrainsPosicion.gridx = i;
                    constrainsPosicion.gridy = j;
                    constrainsPosicion.gridwidth = 1;
                    constrainsPosicion.fill = GridBagConstraints.NONE;
                    constrainsPosicion.anchor = GridBagConstraints.CENTER;
                    tableroPosicion.add(tableroPosicionU[i][j], constrainsPosicion);
                }
            }
        }
    }

    private void pintarTableroPosicion(String[][] matrix) {
        if (interfaz == 1) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matrix[i][j] != "") {
                        tableroPosicionU[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/barcos.fraccion/" + matrix[i][j] + ".png")));
                    }
                }
            }
        }
        repaint();
        revalidate();
    }

    private void pintarTableroPrincipal(){
        if(interfaz == 2){
            GridBagConstraints constrainsPosicionDerecha = new GridBagConstraints();
            constrainsPosicionDerecha.weightx = 40;
            constrainsPosicionDerecha.weighty = 40;
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    tableroPosicionM[i][j] = new JButton();
                    tableroPosicionM[i][j].setBackground(new Color(30, 124, 236));
                    tableroPosicionM[i][j].setPreferredSize(new Dimension(40, 40));
                    constrainsPosicionDerecha.gridx = i;
                    constrainsPosicionDerecha.gridy = j;
                    constrainsPosicionDerecha.gridwidth = 1;
                    constrainsPosicionDerecha.fill = GridBagConstraints.NONE;
                    constrainsPosicionDerecha.anchor = GridBagConstraints.CENTER;
                    tableroPrincipal.add(tableroPosicionM[i][j], constrainsPosicionDerecha);
                }
            }
        }
    }

    private void pintarTableroPrincipal(String[][] matrixTabPrincipal){
        if (interfaz == 1) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matrixTabPrincipal[i][j] != "") {
                        tableroPosicionM[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/disparos/" + matrixTabPrincipal[i][j] + ".png")));
                    }
                }
            }
        }
        repaint();
        revalidate();
    }

    private void pintarPanelEleccion() {
        if (posicionFlota < 4) {
            switch (nombreFlota[posicionFlota]) {
                case "Portaaviones" -> tipoFlota = "portaaviones";
                case "Submarinos" -> tipoFlota = "submarino";
                case "Destructores" -> tipoFlota = "destructor";
                case "Fragatas" -> tipoFlota = "fragata";
            }
            cantidadFlotas.setText("Tienes " + cantidadFlota[posicionFlota] + " " + nombreFlota[posicionFlota]);
            cantidadFlota[posicionFlota]--;
        }
    }

    private void pintarOpcionAlineacion() {
        String direccion = "";
        switch (nombreFlota[posicionFlota]) {
            case "Portaaviones" -> direccion = "/resources/portaaviones.";
            case "Submarinos" -> direccion = "/resources/submarino.";
            case "Destructores" -> direccion = "/resources/destructor.";
            case "Fragatas" -> direccion = "/resources/fragata.";
        }
        if (interfaz == 0) {
            interfaz = 1;
        }
        horizontal.setIcon(new ImageIcon(getClass().getResource(direccion + "H.png")));
        vertical.setIcon(new ImageIcon(getClass().getResource(direccion + "V.png")));
    }

    private void addEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setEnabled(true);
                matrix[i][j].addActionListener(escucha);
            }
        }
    }

    private void removeEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setEnabled(false);
                matrix[i][j].removeActionListener(escucha);
            }
        }
    }

    /**
     * Main process of the Java program
     *
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args) {
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

            if (interfaz == 1) {
                if (posicionFlota < 4) {
                    if (e.getSource() == horizontal) {
                        orientacion = "horizontal";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);

                    } else if (e.getSource() == vertical) {
                        orientacion = "vertical";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);

                    } else {
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (tableroPosicionU[i][j] == e.getSource()) {
                                    if (modelGame.posicionarflota(i, j, orientacion, tipoFlota)) {
                                        if (cantidadFlota[posicionFlota] == 0) {
                                            posicionFlota++;
                                            if (posicionFlota < 4) {
                                                pintarOpcionAlineacion();
                                            } else {
                                                iniciar = new JButton("Iniciar");
                                                iniciar.addActionListener(escucha);
                                                constrains.gridx = 0;
                                                constrains.gridy = 1;
                                                constrains.gridwidth = 1;
                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelIzquierdo.add(iniciar, constrains);
                                                interfaz = 2;
                                            }
                                        }
                                        pintarTableroPosicion(modelGame.getTableroPosUsuario());
                                        pintarPanelEleccion();
                                        removeEscucha(tableroPosicionU);
                                        horizontal.addActionListener(escucha);
                                        vertical.addActionListener(escucha);
                                    } else {
                                        JOptionPane.showMessageDialog(tableroPosicion, "No se pudo posicionar la flota porque "+modelGame.getError(), "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if (e.getSource() == iniciar) {
                iniciar.setVisible(false);
                panelDerecho.removeAll();
                headerProject.setText("Selecciona a donde apuntas tu cañones");

                panelIzquierdo.setPreferredSize(new Dimension(420, 500));
                panelDerecho.setPreferredSize(new Dimension(420, 500));

                tableroPrincipal = new JPanel(new GridBagLayout());
                tableroPrincipal.setPreferredSize(new Dimension(400, 400));
                tableroPrincipal.setBackground(null);
                constrains.gridx = 0;
                constrains.gridy = 0;
                constrains.gridwidth = 1;
                constrains.fill = GridBagConstraints.NONE;
                constrains.anchor = GridBagConstraints.CENTER;
                panelDerecho.add(tableroPrincipal, constrains);
                pintarTableroPrincipal();

                territorioEnemigo = new JButton("Territorio enemigo");
                constrains.gridx = 0;
                constrains.gridy = 1;
                constrains.gridwidth = 1;
                constrains.fill = GridBagConstraints.NONE;
                constrains.anchor = GridBagConstraints.CENTER;
                //panelIzquierdo.add(iniciar, constrains);
                territorioEnemigo.addActionListener(escucha);

            }
        }
    }
}


