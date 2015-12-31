import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by MainW8 on 23/12/2015.
 */

abstract class MoveBuilder {
    protected Field field;
    //  чтобы оставить поле field приватным
    private byte filledCellsAmount;         // чтобы знать размер массива заполненных ячеек
    protected ArrayList<FilledCell> filledCells;


    public class FilledCell {//     контейнер для параметров заполненной ячейки

        int x;
        int y;
        short value;


        public FilledCell(int y, int x, short value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    protected void setFilledCells() {

        filledCells = makeFilledCellsArray();
    }

    public void performMove(Field field) {
        updateFieldCondition(field);
    }



    private void updateFieldCondition(Field field) {
        this.field = field;
        this.field.cells = field.cells;
        filledCells = makeFilledCellsArray();
        filledCellsAmount = (byte) filledCells.size();
    }

    protected boolean isColumnShouldProcessed(int x) {
        if (isColumnFree(x)) {
           // System.out.println("Column" + x+" is free");
            return false;
        } else {
            if (hasColumnTheSameValue(x)) {
               // System.out.println("Column" + x+"  has same value");
                return true;
            }
            //System.out.println("Column" + x+" doesn't have same value");
            return false;
        }
    }


    protected boolean isColumnFree(int x) {// проверяет колонку на наличие каких либо значений
        if (x > 3) {
            return true;
        }
        for (FilledCell filledCell : filledCells) {
            if (filledCell.x == x) {

                return false;
            }
        }

        return true;
    }

    private boolean hasColumnTheSameValue(int x) {// проверяет наличие одинаковых значений в колонке по вертикале
        if (x > 3) {
            return false;
        }
        for (FilledCell filledCell : filledCells) {
            for (int y = field.cells.length-1; y > 0; y--) {
                if (filledCell.y != y) {
                    if (filledCell.value == field.cells[y][x] && field.cells[y][x] != 0 && filledCell.value !=0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private ArrayList<FilledCell> makeFilledCellsArray() {    // создает Лист заполненных ячеек
        filledCells = null;
        ArrayList<FilledCell> arr = new ArrayList<FilledCell>();


        byte xForCell = -1;
        byte yForCell = -1;
        short value = -1;


        for (int y = 0; y < field.cells.length; y++) {

            for (int x = 0; x < field.cells[y].length; x++) {

                if (field.cells[y][x] == 0) {
                    continue;
                } else {
                    xForCell = (byte) x;
                    yForCell = (byte) y;
                    value = field.cells[y][x];
                    if (value > 0) {
                        arr.add(new FilledCell(yForCell, xForCell, value));

                    } else continue;
                }
            }

        }
        return arr;
    }
}





