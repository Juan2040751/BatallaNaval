package myProject;

import java.util.Random;

public class Machine {
    private String[] flota;
    private int estaFlota;

    /**
     * constructor of Machine class
     */
    public Machine() {
        flota = new String[]{"portaaviones","submarino","submarino","destructor","destructor","destructor","fragata","fragata","fragata","fragata"};
        estaFlota = 0;
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
        Random random = new Random();
        return random.nextInt(0,10);
    }

    /**
     * defines a random number between 0 and 10 that represents the coordinate of the shot on the y-axis
     * @return coordinate of the shot on the y-axis
     */
    public int getDisparoY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }
}
