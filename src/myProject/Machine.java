package myProject;

import java.util.Random;

public class Machine {
    private String[] flota;
    private int estaFlota;

    public Machine() {
        flota = new String[]{"portaaviones","submarino","submarino","destructor","destructor","destructor","fragata","fragata","fragata","fragata"};
        estaFlota = 0;
    }

    public String getBarco(){
        String barco="";
        if(estaFlota < 10) {
            barco = flota[estaFlota];
            estaFlota++;
        }
        return barco;
    }

    public String getOrientacion(){
        Random random = new Random();
        String[] orientacionMaquina = {"horizontal","vertical"};
        return (orientacionMaquina[random.nextInt(0,2)]);
    }

    public int getCoordenadaX(){
        Random random = new Random();
        return random.nextInt(0,10);
    }

    public int getCoordenadaY(){
        Random random = new Random();
        return random.nextInt(0,10);
    }
}
