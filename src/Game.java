

/**
 * Created by MainW8 on 22/12/2015.
 */

public class Game {


    public static void main(String[] args) {
        Field field = new Field();


        field.showField();

        field.makeTurn(Up.push());
        field.showField();


    }
}

