package myProject;

import java.util.Random;

public class Machine {
    private String[] flota;
    private int estaFlota, disparoX,disparoY;
    private String[][] tableroInformacionMachine;


    /**
     * constructor of Machine class
     */
    public Machine() {
        flota = new String[]{"portaaviones","submarino","submarino","destructor","destructor","destructor","fragata","fragata","fragata","fragata"};
        estaFlota = 0;

        Random random = new Random();
        disparoX= random.nextInt(0,10);
        disparoY= random.nextInt(0,10);

        tableroInformacionMachine = new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroInformacionMachine[i][j] = "";
            }
        }
    }

    /**
     * to get the name (ship type) of each ship in the fleet
     * @return the type of boat
     */
    public String getBarco(){
        String barco="";
        if(estaFlota < 10) {
            barco = flota[estaFlota];
            estaFlota++;
        }
        return barco;
    }

    /**
     * randomly defines the orientation in which the ship will be located
     * @return a random orientation
     */
    public String getOrientacion(){
        Random random = new Random();
        String[] orientacionMaquina = {"horizontal","vertical"};
        return (orientacionMaquina[random.nextInt(0,2)]);
    }

    /**
     * defines a random number between 0 and 10 that represents the coordinate on the x-axis
     * @return coordinate on the x-axis
     */
    public int getCoordenadaX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    /**
     * defines a random number between 0 and 10 that represents the coordinate on the y-axis
     * @return coordinate on the y-axis
     */
    public int getCoordenadaY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    /**
     * defines a random number between 0 and 10 that represents the coordinate of the shot on the x-axis
     * @return coordinate of the shot on the x-axis
     */
    public int getDisparoX(){
        return disparoX;
    }

    /**
     * defines a random number between 0 and 10 that represents the coordinate of the shot on the y-axis
     * @return coordinate of the shot on the y-axis
     */
    public int getDisparoY(){
      return disparoY;
    }

    /**
     * set up a game strategy, shoot at random positions until you find a hit, then start shooting at the surrounding area to sink the enemy ship.
     * @param tableroInfPrincipalM receives the position matrix from the computer, from which it will build it's strategy.
     */
    public void prepararSiguienteDisparo(String[][] tableroInfPrincipalM) {
        tableroInformacionMachine=tableroInfPrincipalM;
        if(!hayObjetivo()){
            int disparoAnteriorX=disparoX,disparoAnteriorY=disparoY;

            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                int x= random.nextInt(0,10);
                int y= random.nextInt(0,10);
                if (tableroInformacionMachine[x][y].equals("")){
                    disparoX=x;
                    disparoY=y;
                    break;
                }
            }
            if(disparoAnteriorX==disparoX&&disparoAnteriorY==disparoY) {
                for (int i = 0; i < 10 && disparoAnteriorX==disparoX&&disparoAnteriorY==disparoY; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(tableroInformacionMachine[j][i].equals("")){
                            disparoX=j;
                            disparoY=i;
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * finds the headpiece and learns the next target to be fired at
     * @return if a new target was selected
     */
    private boolean hayObjetivo() {
        boolean objetivo=false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tableroInformacionMachine[i][j].equals("tocado")&&!objetivo){
                    if(i>0&&tableroInformacionMachine[i-1][j].equals("")){
                        disparoX=i-1;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i>1&&tableroInformacionMachine[i-2][j].equals("")&&tableroInformacionMachine[i-1][j].equals("tocado")){
                        disparoX=i-2;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i<9&&tableroInformacionMachine[i+1][j].equals("")){
                        disparoX=i+1;
                        disparoY=j;
                        objetivo=true;
                    }
                    else if(i<8&&tableroInformacionMachine[i+2][j].equals("")&&tableroInformacionMachine[i+1][j].equals("tocado")){
                        disparoX=i+2;
                        disparoY=j;
                        objetivo=true;
                    }

                    if(j>0&&tableroInformacionMachine[i][j-1].equals("")){
                        disparoX=i;
                        disparoY=j-1;
                        objetivo=true;
                    }
                    else if(j>1&&tableroInformacionMachine[i][j-2].equals("")&&tableroInformacionMachine[i][j-1].equals("tocado")){
                        disparoX=i;
                        disparoY=j-2;
                        objetivo=true;
                    }
                    else if(j<9&&tableroInformacionMachine[i][j+1].equals("")){
                        disparoX=i;
                        disparoY=j+1;
                        objetivo=true;
                    }
                    else if(j<8&&tableroInformacionMachine[i][j+2].equals("")&&tableroInformacionMachine[i][j+1].equals("tocado")){
                        disparoX=i;
                        disparoY=j+2;
                        objetivo=true;
                    }
                }
            }
        }
        return objetivo;
    }
}
