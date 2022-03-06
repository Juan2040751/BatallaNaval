package myProject;

public class ModelGame {
    private String[][] tableroPosUsuario;
    private String error;

    public ModelGame() {
        tableroPosUsuario= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosUsuario[i][j]="";
            }
        }

    }
    public boolean posicionarflota(int posicionHorizontal,int posicionVertical,String alineacion, String barco){
        boolean answer=false;
        int espacio=0;
        switch (barco){
            case "portaaviones"-> espacio=4;
            case "submarino"-> espacio=3;
            case "destructor"-> espacio=2;
            case "fragata"-> espacio=1;
        }
        if (!tableroPosUsuario[posicionHorizontal][posicionVertical].equals("")){
            answer=false;
            error="esta posicion ya esta en uso";
        }

        else if(alineacion.equals("horizontal")){
            if(posicionHorizontal+espacio>10){
                answer=false;
                error="el "+barco+" ocupa "+espacio+" espacios. Trata con al menos "+(posicionHorizontal+espacio-10)+" hacia la izquierda.";
            }else {
                for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                    if(!tableroPosUsuario[i][posicionVertical].equals("")){
                        answer=false;
                        error="una de las posiciones que ocuparia tu "+barco+" ya esta en uso.";
                    }
                    else if(i== posicionHorizontal + espacio-1){
                        answer=true;
                        setTableroPosUsuario(posicionHorizontal, posicionVertical, alineacion, barco, espacio);
                    }
                }
            }
        }
        else if(alineacion.equals("vertical")){
            if(posicionVertical+espacio>10){
                answer=false;
                error="el "+barco+" ocupa "+espacio+" espacios. Trata con al menos "+(posicionVertical+espacio-10)+" hacia arriba.";
            }else {
                for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                    if(!tableroPosUsuario[posicionHorizontal][i].equals("")){
                        answer=false;
                        error="una de las posiciones que ocuparia tu "+barco+" ya esta en uso.";
                    }
                    else if(i== posicionVertical + espacio-1){
                        answer=true;
                        setTableroPosUsuario(posicionHorizontal, posicionVertical, alineacion, barco, espacio);
                    }
                }
            }
        }
        return answer;
    }
    private void setTableroPosUsuario(int posicionHorizontal,int posicionVertical,String alineacion, String barco,int espacio) {
        int contador=1;
        if (alineacion.equals("horizontal")){
            for (int i = posicionHorizontal; i < posicionHorizontal + espacio; i++) {
                tableroPosUsuario[i][posicionVertical]=barco+".H."+contador;
                contador++;
            }
        }
        else{
            for (int i = posicionVertical; i < posicionVertical + espacio; i++) {
                tableroPosUsuario[posicionHorizontal][i]=barco+".V."+contador;
                contador++;
            }
        }
    }

    public String[][] getTableroPosUsuario() {
        return tableroPosUsuario;
    }
    public String getError(){
        return error;
    }
}
