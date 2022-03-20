package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class is used to graph the game
 * @autor Fabian Lopez
 * @autor Juan Jose Viafara
 * @autor William Velasco
 * @version V.2.0.0 date 26/01/2022
 */
public class GUIGridBagLayout extends JFrame {
    private Header headerProject;
    private JPanel panelIzquierdo;
    private JPanel panelDerecho;
    private JPanel tableroPosicion;
    private JPanel tableroPrincipal;
    private ModelGame modelGame;
    private Escucha escucha;
    private JButton horizontal, vertical, iniciar, territorioEnemigo, volver;
    private int interfaz, posicionFlota, disparoX, disparoY;
    private JButton[][] tableroPosicionU, tableroPrincipalU;
    private JTextArea cantidadFlotas, ayuda, ayudaSecundaria;
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
        tableroPrincipalU = new JButton[10][10];
        posicionFlota = 0;
        nombreFlota = new String[]{"Portaaviones", "Submarinos", "Destructores", "Fragatas"};
        cantidadFlota = new int[]{1, 2, 3, 4};
        initGUI();
        //setIconImage(new ImageIcon(getClass().getResource("/resources/logo.png")).getImage());
        //Default JFrame configuration
        this.setTitle("Batalla Naval");
        this.pack();
        this.setResizable(false);
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
        ayuda = new JTextArea(2, 5);
        ayudaSecundaria = new JTextArea(2, 5);


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

        JPanel panelEleccion = new JPanel(new GridBagLayout());
        panelEleccion.setPreferredSize(new Dimension(300, 300));
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
        horizontal.setBackground(new Color(221, 244, 252));

        horizontal.setBorderPainted(false);
        constrains.gridx = 0;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(horizontal, constrains);


        ayuda.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        ayuda.setBackground(null);
        ayuda.setEditable(false);
        constrains.gridx = 0;
        constrains.gridy = 2;
        constrains.gridwidth =2;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(ayuda, constrains);

        vertical.addActionListener(escucha);
        vertical.setBackground(new Color(221, 244, 252));
        vertical.setBorderPainted(false);
        constrains.gridx = 1;
        constrains.gridy = 1;
        constrains.gridwidth = 1;
        constrains.fill = GridBagConstraints.NONE;
        constrains.anchor = GridBagConstraints.CENTER;
        panelEleccion.add(vertical, constrains);

    }

    /**
     * add the 100 buttons to tableroPosicion for the first time, when the interface is being created
     */
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
        } else if(interfaz == 2){
            tableroPosicion.removeAll();
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

    /**
     * modifies the images displayed by the buttons with respect to what is in the matrix parameter
     * @param matrix with the changes to be made
     */
    private void pintarTableroPosicion(String[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!matrix[i][j].equals("")) {
                    tableroPosicionU[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/barcos.fraccion/" + matrix[i][j] + ".png")));
                }
            }
        }
        repaint();
        revalidate();
    }

    /**
     * add the 100 buttons to tableroPrincipalU for the first time, when the interface is being created
     */
    private void pintarTableroPrincipal(){
        if(interfaz == 2){
            GridBagConstraints constrainsPosicionDerecha = new GridBagConstraints();
            constrainsPosicionDerecha.weightx = 40;
            constrainsPosicionDerecha.weighty = 40;
            for(int i=0; i<10; i++){
                for(int j=0; j<10; j++){
                    tableroPrincipalU[i][j] = new JButton();
                    tableroPrincipalU[i][j].setBackground(new Color(30, 124, 236));
                    tableroPrincipalU[i][j].setPreferredSize(new Dimension(40, 40));
                    constrainsPosicionDerecha.gridx = i;
                    constrainsPosicionDerecha.gridy = j;
                    constrainsPosicionDerecha.gridwidth = 1;
                    constrainsPosicionDerecha.fill = GridBagConstraints.NONE;
                    constrainsPosicionDerecha.anchor = GridBagConstraints.CENTER;
                    tableroPrincipal.add(tableroPrincipalU[i][j], constrainsPosicionDerecha);
                }
            }
        }
    }

    /**
     * modifies and updated the images displayed by the buttons with respect to what is in the matrix parameter
     * @param matrixTabPrincipal
     */
    public void pintarTableroPrincipal(String[][] matrixTabPrincipal){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (matrixTabPrincipal[i][j] != "") {
                    tableroPrincipalU[i][j].setIcon(new ImageIcon(getClass().getResource("/resources/disparos/" + matrixTabPrincipal[i][j] + ".png")));
                }
            }
        }
        repaint();
        revalidate();
    }

    /**
     * load the information to be displayed in panelEleccion
     */
    private void pintarPanelEleccion() {
        if (posicionFlota < 4) {
            switch (nombreFlota[posicionFlota]) {
                case "Portaaviones" -> tipoFlota = "portaaviones";
                case "Submarinos" -> tipoFlota = "submarino";
                case "Destructores" -> tipoFlota = "destructor";
                case "Fragatas" -> tipoFlota = "fragata";
            }
            int espacio=0;
            switch (tipoFlota){
                case "portaaviones"-> espacio=4;
                case "submarino"-> espacio=3;
                case "destructor"-> espacio=2;
                case "fragata"-> espacio=1;
            }
            cantidadFlotas.setText("      Tienes " + cantidadFlota[posicionFlota] + " " + nombreFlota[posicionFlota]+"\nCada uno ocupa "+espacio+" posiciones.");
            ayuda.setText("Selecciona la orientacion de tu\n                  "+tipoFlota);
            cantidadFlota[posicionFlota]--;
        }
    }

    /**
     * show the images to choose the alignment
     */
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

    /**
     * adds the listener to the 100 buttons
     * @param matrix where the buttons are
     */
    private void addEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setBackground(new Color(16, 92, 234));
                matrix[i][j].addActionListener(escucha);
            }
        }
    }

    /**
     * remove the listener to the 100 buttons
     * @param matrix where the buttons are
     */
    private void removeEscucha(JButton[][] matrix) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                matrix[i][j].setBackground(new Color(30, 124, 236));
                matrix[i][j].removeActionListener(escucha);
            }
        }
    }

    /**
     * Main process of the Java program
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
                    /**
                     * in the first interface there are only 102 buttons, the two to select the alignment and the 100 to choose the position.
                     */
                    if (e.getSource() == horizontal) {
                        orientacion = "horizontal";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);
                        ayuda.setText("Selecciona donde deseas\n posicionar tu "+tipoFlota);
                    } else if (e.getSource() == vertical) {
                        orientacion = "vertical";
                        addEscucha(tableroPosicionU);
                        horizontal.removeActionListener(escucha);
                        vertical.removeActionListener(escucha);
                        ayuda.setText("Selecciona donde deseas\n posicionar tu "+tipoFlota);
                    } else {
                        /**check which of the 100 buttons was clicked*/
                        for (int i = 0; i < 10; i++) {
                            for (int j = 0; j < 10; j++) {
                                if (tableroPosicionU[i][j] == e.getSource()) {
                                    /**once found, it is checked to see if it can be added to the underlying positions*/
                                    if (modelGame.ingresarBarcoUsuario(i, j, orientacion, tipoFlota)) {
                                        pintarTableroPosicion(modelGame.getTableroPosUsuario());
                                        if (cantidadFlota[posicionFlota] == 0) {
                                            posicionFlota++;
                                            if (posicionFlota < 4) {
                                                pintarOpcionAlineacion();
                                            }
                                            /**when the entire fleet was positioned*/
                                            else {
                                                iniciar = new JButton("Iniciar");
                                                iniciar.addActionListener(escucha);
                                                constrains.gridx = 0;
                                                constrains.gridy = 1;
                                                constrains.gridwidth = 1;
                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelIzquierdo.add(iniciar, constrains);

                                                panelDerecho.removeAll();
                                                ayuda.setText("Bienvenid@ a Batalla Naval\n         Presiona 'Iniciar'    ");
                                                ayuda.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
                                                constrains.gridx = 0;
                                                constrains.gridy = 0;
                                                constrains.gridwidth =2;
                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelDerecho.add(ayuda, constrains);

                                                ayudaSecundaria.setText("Tu misión es derribar los diez barcos enemigos\n" +
                                                                        "haciendo uso del panel Derecho, cuando logres\n" +
                                                                        "impactar pero no hundir algún barco enemigo\n" +
                                                                        "podras volver a jugar, en cualquier otro caso\n" +
                                                                        "Deberás esperar tu turno.\n" +
                                                                        "Si tu contrincante impacta alguno de tus barcos\n" +
                                                                        "también podra volver a jugar.");
                                                ayudaSecundaria.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
                                                ayudaSecundaria.setBackground(new Color(255, 87, 51 ));
                                                ayudaSecundaria.setEditable(false);
                                                constrains.gridx = 0;
                                                constrains.gridy = 1;
                                                constrains.gridwidth =2;

                                                constrains.fill = GridBagConstraints.NONE;
                                                constrains.anchor = GridBagConstraints.CENTER;
                                                panelDerecho.add(ayudaSecundaria, constrains);

                                                interfaz = 2;

                                                modelGame.ingresarBarcosMaquina();
                                            }
                                        }

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
                panelIzquierdo.remove(iniciar);
                panelDerecho.remove(ayuda);
                panelDerecho.remove(ayudaSecundaria);
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

                JButton espacio = new JButton();
                espacio.setBackground(null);
                espacio.setPreferredSize(iniciar.getPreferredSize());
                espacio.setBorderPainted(false);
                constrains.gridx = 0;
                constrains.gridy = 1;
                constrains.gridwidth = 1;
                constrains.fill = GridBagConstraints.NONE;
                constrains.anchor = GridBagConstraints.CENTER;
                panelDerecho.add(espacio,constrains);

                territorioEnemigo = new JButton("Ver territorio enemigo");
                constrains.gridx = 0;
                constrains.gridy = 1;
                constrains.gridwidth = 1;
                constrains.fill = GridBagConstraints.NONE;
                constrains.anchor = GridBagConstraints.CENTER;
                territorioEnemigo.addActionListener(escucha);
                panelIzquierdo.add(territorioEnemigo,constrains);

                volver = new JButton("Volver a tu territorio");
                volver.setVisible(false);
                constrains.gridx = 0;
                constrains.gridy = 1;
                constrains.gridwidth = 1;
                constrains.fill = GridBagConstraints.NONE;
                constrains.anchor = GridBagConstraints.CENTER;
                volver.addActionListener(escucha);
                panelIzquierdo.add(volver,constrains);

                addEscucha(tableroPrincipalU);
            }
            else if (interfaz == 2){
                if (e.getSource() == territorioEnemigo){
                    territorioEnemigo.setVisible(false);
                    volver.setVisible(true);
                    pintarTableroPosicion();
                    pintarTableroPosicion(modelGame.getTableroPosMaquina());
                    interfaz=3;
                }
                else{
                    setDisparo(e);
                    pintarTableroPosicion();
                    pintarTableroPosicion(modelGame.getTableroPosUsuario());
                }

                if(modelGame.hayGanador()){
                    int respuesta;
                    if(modelGame.getGanador().equals("maquina") ){
                        respuesta =JOptionPane.showConfirmDialog(panelIzquierdo,"Perdiste, la Maquina ha ganado","Termino el juego",JOptionPane.DEFAULT_OPTION);
                    }else{
                        respuesta= JOptionPane.showConfirmDialog(panelDerecho,"Ganaste!!!","Termino el juego",JOptionPane.DEFAULT_OPTION);
                    }
                    if(respuesta==0){
                        System.exit(0);
                    }
                }
            }
            else if (interfaz==3){
                if(e.getSource() == volver){
                    interfaz=2;
                    volver.setVisible(false);
                    territorioEnemigo.setVisible(true);
                    pintarTableroPosicion();
                    pintarTableroPosicion(modelGame.getTableroPosUsuario());

                }else{
                    setDisparo(e);
                    pintarTableroPosicion();
                    pintarTableroPosicion(modelGame.getTableroPosMaquina());
                }

                if(modelGame.hayGanador()){
                    int respuesta;
                    if(modelGame.getGanador() == "maquina"){
                        interfaz=2;
                        volver.setVisible(false);
                        territorioEnemigo.setVisible(true);
                        pintarTableroPosicion();
                        pintarTableroPosicion(modelGame.getTableroPosUsuario());

                        respuesta= JOptionPane.showConfirmDialog(panelIzquierdo, "Perdiste, la Maquina ha ganado", "Termino el juego", JOptionPane.DEFAULT_OPTION);
                    }else{
                        respuesta= JOptionPane.showConfirmDialog(panelDerecho,"Ganaste!!!","Termino el juego",JOptionPane.DEFAULT_OPTION);
                    }
                    if(respuesta==0){
                        System.exit(0);
                    }
                }
            }
        }

        /**
         * Sets the position where the shot was made by the user
         * @param disparo
         */
        private void setDisparo(ActionEvent disparo){
            for (int i = 0; i < 10 ; i++) {
                for (int j = 0; j < 10; j++) {
                    if(disparo.getSource() == tableroPrincipalU[i][j]){
                        modelGame.setTableroInfPrincipalU(i,j);
                        pintarTableroPrincipal(modelGame.getTableroInfPrincipalU());
                    }

                }
            }
        }
    }
}


