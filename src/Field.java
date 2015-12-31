import java.util.Random;

/**
 * Created by MainW8 on 22/12/2015.
 */

public class Field {
    protected short[][] cells;

    private Random random;
    private MoveBuilder turnDirection;


     Field() {                       //  создаем первоначальное поле с двумя произвольными цифрами

        this.cells = new short[4][4];
        random = new Random();

        /* int x = 0;
        cells[0][x] = 0;
        cells[1][x] = 2;
        cells[2][x] = 0;
        cells[3][x] = 2;

         x=1;
        cells[0][x] = 0;
        cells[1][x] = 0;
        cells[2][x] = 0;
        cells[3][x] = 0;

         x=2;
        cells[0][x] = 2;
        cells[1][x] = 2;
        cells[2][x] = 4;
        cells[3][x] = 2;

         x=3;
        cells[0][x] = 2;
        cells[1][x] = 2;
        cells[2][x] = 2;
        cells[3][x] = 2;
*/



      for (int i = 0; i < 10; i++) {
            buildNewCipher();
        }

       }

    private void setTurnDirection(MoveBuilder turn) {

        this.turnDirection = turn;
    }

    void makeTurn(MoveBuilder moveMaker) {   // делает ход вверх

        setTurnDirection(moveMaker);
        turnDirection.performMove(this);
        //buildNewCipher();
    }





    private void buildNewCipher() {

        do {

            byte x = cipherGeneator(true);
            int y = cipherGeneator(true);
            int cell = cells[y][x];
            if (cell > 0) {
                continue;
            } else {
                cells[y][x] = cipherGeneator(false);
                break;
            }

        }
        while (true);
    }


    private void setCell(short value, byte cellX, byte cellY) {

        cells[cellY][cellX] = value;
    }

    public void showField() {

        for (short[] innerArr : cells) {
            System.out.println("");
            for (short value : innerArr) {
                System.out.print(value + "  ");
            }
        }
        System.out.println("");
    }

    private byte cipherGeneator(boolean isForCell) {
        if (isForCell) {                                       // вычисляем координаты ячейки
            byte cellXorY;
            cellXorY = ((byte) (Math.random() * 4));
            return cellXorY;
        } else {                                                //вычисляем значение ячейки
            byte cipher;


            do {
                cipher = (byte) (random.nextInt(2) + 1);// 1/2 от 1до 2
                //System.out.println(cipher + " Работает первый проход");
                if (cipher == 2) {

                    return cipher;
                }
                cipher = (byte) (random.nextInt(5) + 1);
                //System.out.println("Работает второй проход");
                if (cipher == 2) {
                    return cipher;
                }
                cipher = (byte) (random.nextInt(5) + 1);
                //System.out.println("Работает третий проход");
                if (cipher == 2 || cipher == 4) {
                    return cipher;
                }
            }
            while (true);

        }

    }

}

