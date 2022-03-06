package myProject;

public class ModelGame {
    private String[][] tableroPosUsuario;

    public ModelGame() {
        tableroPosUsuario= new String[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tableroPosUsuario[i][j]="";
            }
        }

    }


}
