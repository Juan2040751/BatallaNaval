package myProject;

public class ModelGame {
    private String[][] tableroPosUsuario, tableroPosMaquina, tableroInfPrincipalU, tableroInfPrincipalM;
    private String error, ganador="";
    private Machine machine;
    private int contadorMaquina=0, contadorUsuario=0;

    /**
     * Constructor of ModelGame class
     */
    public ModelGame() {
        /**initializes the matrix to "" to paint the representing button as water*/
        tableroPosUsuario = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosUsuario[i][j] = "";
            }
        }

        tableroPosMaquina = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosMaquina[i][j] = "";
            }
        }

        machine = new Machine();

        tableroInfPrincipalU = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInfPrincipalU[i][j] = "";
            }
        }

        tableroInfPrincipalM = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInfPrincipalM[i][j] = "";
            }
        }
    }

    /**
     * checks if it's posible to enter the ship in the position chosen by the user
     *
     * @param posicionHorizontal
     * @param posicionVertical
     * @param alineacion
     * @param barco
     * @return whether it's possible to enter the ship or not
     */
    public boolean ingresarBarcoUsuario(int posicionHorizontal, int posicionVertical, String alineacion, String barco) {
        return posicionarflota(tableroPosUsuario, posicionHorizontal, posicionVertical, alineacion, barco);
    }

    /**
     * checks if the vessel can be added to the underlying positions
     *
     * @param posicionHorizontal the horizontal position where you want to start painting the boat
     * @param posicionVertical   the vertical position where you want to start painting the boat
     * @param alineacion         "horizontal" or "vertical"
     * @param barco              vessel name to add
     * @return whether it is possible or not
     */
    private boolean posicionarflota(String[][] matrix, int posicionHorizontal, int posicionVertical, String alineacion, String barco) {
        boolean answer = false;
        int espacio = getEspacio(barco);
        /**checks if the initial position (marked by the user) is empty*/
        if (!matrix[posicionHorizontal][posicionVertical].equals("")) {
            answer = false;
            error = "esta posicion ya esta en uso";
        } else if (alineacion.equals("horizontal")) {
            /**check that the initial position marked by the user part of the fleet is not left out.*/
            if (posicionHorizontal + espacio > 10) {
                answer = false;
                error = "el " + barco + " ocupa " + espacio + " espacios. Trata con al menos " + (posicionHorizontal + espacio - 10) + " posiciones hacia la izquierda.";
            } else {
                for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                    /**check that all positions where the boat would be are empty.*/
                    if (!matrix[i][posicionVertical].equals("")) {
                        answer = false;
                        error = "una de las posiciones que ocuparia tu " + barco + " ya esta en uso.";
                    }
                    /**after having carried out all the revisions, it is verified that the boat can be added.*/
                    else if (i == posicionHorizontal + espacio - 1) {
                        answer = true;
                        setTableroPosicion(matrix, posicionHorizontal, posicionVertical, alineacion, barco, espacio);
                    }
                }
            }
        }
        /**check that the initial position marked by the user part of the fleet is not left out.*/
        else if (alineacion.equals("vertical")) {
            /**check that the initial position marked by the user part of the fleet is not left out.*/
            if (posicionVertical + espacio > 10) {
                answer = false;
                error = "el " + barco + " ocupa " + espacio + " espacios. Trata con al menos " + (posicionVertical + espacio - 10) + " posiciones hacia arriba.";
            } else {
                for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                    /**check that all positions where the boat would be are empty.*/
                    if (!matrix[posicionHorizontal][i].equals("")) {
                        answer = false;
                        error = "una de las posiciones que ocuparia tu " + barco + " ya esta en uso.";
                    }
                    /**after having carried out all the revisions, it is verified that the boat can be added.*/
                    else if (i == posicionVertical + espacio - 1) {
                        answer = true;
                        setTableroPosicion(matrix, posicionHorizontal, posicionVertical, alineacion, barco, espacio);
                    }
                }
            }
        }
        return answer;
    }


    /**
     * start adding the boat in the indicated position and alignment
     *
     * @param posicionHorizontal the horizontal position where you want to start painting the boat
     * @param posicionVertical   the vertical position where you want to start painting the boat
     * @param alineacion         "horizontal" or "vertical"
     * @param barco              vessel name to add
     * @param espacio            space occupied by the ship
     */
    private void setTableroPosicion(String[][] matrix, int posicionHorizontal, int posicionVertical, String alineacion, String barco, int espacio) {
        int contador = 1;
        if (alineacion.equals("horizontal")) {
            for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                matrix[i][posicionVertical] = barco + ".H." + contador;
                contador++;
            }
        } else {
            for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                matrix[posicionHorizontal][i] = barco + ".V." + contador;
                contador++;
            }
        }
    }

    /**
     * @return the matrix with the information of where the user's fleet is positioned.
     */
    public String[][] getTableroPosUsuario() {
        return tableroPosUsuario;
    }

    /**
     * @return the reason why the boat could not be added
     */
    public String getError() {
        return error;
    }

    /**
     * Enter each ship of the machine in its respective matrix
     */
    public void ingresarBarcosMaquina() {
        for (int i = 0; i < 10; i++) {
            String barcoMaquina = machine.getBarco();
            while (!posicionarflota(tableroPosMaquina, machine.getCoordenadaX(), machine.getCoordenadaY(), machine.getOrientacion(), barcoMaquina)) {
            }
        }
    }

    /**
     * to obtain the positions of the machine fleet
     * @return tableroPosMaquina
     */
    public String[][] getTableroPosMaquina() {
        return tableroPosMaquina;
    }

    /**
     * checks the moment when a ship goes from being touched to being sunk
     *
     * @param matrixPosEnemigo
     * @param disparoX
     * @param disparoY
     * @param matrixPrinJugador
     * @return whether it's marked as sunk or not
     */
    private boolean hundimiento(String[][] matrixPosEnemigo,int disparoX, int disparoY, String[][] matrixPrinJugador) {
        boolean hundido = false;
        String informacion = matrixPosEnemigo[disparoX][disparoY];
        String tipoBarco = informacion.substring(0, informacion.indexOf("."));
        String tipoAlineacion = informacion.substring(informacion.indexOf(".") + 3, informacion.indexOf(".") + 4);
        int parteBarco= Integer.valueOf(informacion.substring(informacion.lastIndexOf(".")+1));
        int espacio = getEspacio(tipoBarco);

        if (espacio == 1) {
            matrixPosEnemigo[disparoX][disparoY] = "hundido";
            matrixPrinJugador[disparoX][disparoY] = "hundido";
            hundido = true;
        } else {
            int ultimaPos;
            hundido = true;
            String esteDato;

            if (tipoAlineacion.equals("H")) {
                if (parteBarco==espacio){
                    ultimaPos=disparoX;
                }
                else {
                    ultimaPos=disparoX+espacio-parteBarco;
                }
                for (int i = 1; i <= espacio; i++) {
                    esteDato = matrixPosEnemigo[ultimaPos - espacio + i][disparoY];
                    if (!esteDato.substring(esteDato.indexOf(".") + 1, esteDato.indexOf(".") + 2).equals("T")) {
                        hundido = false;
                        break;
                    }
                }
            }
            else {
                if (parteBarco==espacio){
                    ultimaPos=disparoY;
                }
                else {
                    ultimaPos=disparoY+espacio-parteBarco;
                }
                for (int i = 1; i <= espacio; i++) {
                    esteDato = matrixPosEnemigo[disparoX][ultimaPos - espacio + i];
                    if (!esteDato.substring(esteDato.indexOf(".") + 1, esteDato.indexOf(".") + 2).equals("T")) {
                        hundido = false;
                        break;
                    }
                }
            }
        }
        return hundido;
    }

    /**
     * to obtain the number of squares that each ship occupies within the matrix
     * @param tipoBarco
     * @return space used by each boat
     */
    private int getEspacio(String tipoBarco) {
        int espacio = 0;
        switch (tipoBarco) {
            case "portaaviones" -> espacio = 4;
            case "submarino" -> espacio = 3;
            case "destructor" -> espacio = 2;
            case "fragata" -> espacio = 1;
        }
        return espacio;
    }

    /**
     * to establish in which of the three options the user made the shot (agua, tocado o si ya  lo marca como hundido)
     * check if the user can keep shooting or not
     *
     * @param disparoX
     * @param disparoY
     * @return whether the user continues with the turn or not
     */
    public boolean setTableroInfPrincipalU(int disparoX, int disparoY){
        boolean sePuedeDisparar=true;
        if(tableroPosMaquina[disparoX][disparoY].equals("")){
            tableroInfPrincipalU[disparoX][disparoY]="agua";
            tableroPosMaquina[disparoX][disparoY] = "agua";
        }
        else{
            String primerClick =tableroPosMaquina[disparoX][disparoY];
            if (primerClick.substring(primerClick.indexOf(".")+1,primerClick.indexOf(".")+2).equals("T")){
                sePuedeDisparar=false;
            }
            else if(!primerClick.equals("agua") && !primerClick.equals("hundido")){
                tableroPosMaquina[disparoX][disparoY] = primerClick.substring(0,primerClick.indexOf("."))+".T"+primerClick.substring(primerClick.indexOf("."));
                primerClick =tableroPosMaquina[disparoX][disparoY];
                if (hundimiento(tableroPosMaquina,disparoX,disparoY,tableroInfPrincipalU)){
                    String tipoBarco = primerClick.substring(0,primerClick.indexOf("."));
                    int espacio = getEspacio(tipoBarco);
                    int ultimaPos;
                    int parteBarco= Integer.valueOf(primerClick.substring(primerClick.lastIndexOf(".")+1));

                    if (primerClick.substring(primerClick.indexOf(".") + 3, primerClick.indexOf(".") + 4).equals("H")){
                        if (parteBarco==espacio){
                            ultimaPos=disparoX;
                        }else {
                            ultimaPos=disparoX+espacio-parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosMaquina[ultimaPos - espacio + i][disparoY]= "hundido";
                            tableroInfPrincipalU[ultimaPos - espacio + i][disparoY]= "hundido";
                            contadorUsuario++;
                        }
                    }else {
                        if (parteBarco==espacio){
                            ultimaPos=disparoY;
                        }else {
                            ultimaPos=disparoY+espacio-parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosMaquina[disparoX][ultimaPos - espacio + i]= "hundido";
                            tableroInfPrincipalU[disparoX][ultimaPos - espacio + i]= "hundido";
                            contadorUsuario++;
                        }
                    }
                }
                else {
                    tableroInfPrincipalU[disparoX][disparoY]="tocado";
                }
            }
            else{
                sePuedeDisparar=false;
            }
        }

        if (sePuedeDisparar){
            setTableroInfPrincipalM(machine.getDisparoX(),machine.getDisparoY());
            machine.prepararSiguienteDisparo(tableroInfPrincipalM);
        }

        return sePuedeDisparar;
    }

    /**
     * performs computer triggering
     * @param disparoX horizontal position where the shot will be fired
     * @param disparoY vertical position where the shot will be fired
     * @return if the shot could be fired
     */
    private boolean setTableroInfPrincipalM(int disparoX, int disparoY) {
        boolean sePuedeDisparar=true;
        if(tableroPosUsuario[disparoX][disparoY].equals("")){
            tableroInfPrincipalM[disparoX][disparoY]="agua";
            tableroPosUsuario[disparoX][disparoY] = "agua";
        }
        else{
            String primerClick =tableroPosUsuario[disparoX][disparoY];
            if (primerClick.substring(primerClick.indexOf(".")+1,primerClick.indexOf(".")+2).equals("T")){
                sePuedeDisparar=false;
            }
            else if(!primerClick.equals("agua") && !primerClick.equals("hundido")) {
                tableroPosUsuario[disparoX][disparoY] = primerClick.substring(0,primerClick.indexOf("."))+".T"+primerClick.substring(primerClick.indexOf("."));
                primerClick = tableroPosUsuario[disparoX][disparoY];
                if (hundimiento(tableroPosUsuario,disparoX,disparoY,tableroInfPrincipalM)){
                    String tipoBarco = primerClick.substring(0,primerClick.indexOf("."));
                    int espacio = getEspacio(tipoBarco);
                    int ultimaPos;
                    int parteBarco = Integer.valueOf(primerClick.substring(primerClick.lastIndexOf(".")+1));

                    if (primerClick.substring(primerClick.indexOf(".") + 3, primerClick.indexOf(".") + 4).equals("H")){
                        if (parteBarco==espacio){
                            ultimaPos=disparoX;
                        }else {
                            ultimaPos=disparoX+espacio-parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosUsuario[ultimaPos - espacio + i][disparoY]= "hundido";
                            tableroInfPrincipalM[ultimaPos - espacio + i][disparoY]= "hundido";
                            contadorMaquina++;
                        }
                    }else {
                        if (parteBarco==espacio){
                            ultimaPos=disparoY;
                        }else {
                            ultimaPos=disparoY+espacio-parteBarco;
                        }
                        for (int i = 1; i <= espacio; i++) {
                            tableroPosUsuario[disparoX][ultimaPos - espacio + i]= "hundido";
                            tableroInfPrincipalM[disparoX][ultimaPos - espacio + i]= "hundido";
                            contadorMaquina++;
                        }
                    }
                }
                else {
                    tableroInfPrincipalM[disparoX][disparoY]="tocado";
                }
            }
            else{
                sePuedeDisparar=false;
            }
        }
        return sePuedeDisparar;
    }

    /**
     * to get the matrix where the user's trigger destination is saved (agua, tocado, hundido)
     * @return matrix tableroInfPrincipalU
     */
    public String[][] getTableroInfPrincipalU() {
        return tableroInfPrincipalU;
    }

    /**
     * defines is there player who sank all the rival ships
     * @return if there is a winner or not
     */
    public Boolean hayGanador(){
        boolean answer=false;
        if(contadorUsuario == 20){
            answer = true;
        }else{
            if(contadorMaquina == 20){
                answer = true;
            }
        }
        return answer;
    }

    /**
     * defines the winner of the game
     * @return winner
     */
    public String getGanador(){
        if(contadorMaquina == 20){
            ganador = "maquina";
        }else{
            ganador = "usuario";
        }
        return ganador;
    }
}
