import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by MainW8 on 23/12/2015.
 */
class Up extends MoveBuilder {


    @Override
    public void performMove(Field field) {//  ход ячейки вверх
        super.performMove(field);

        /*int x= 0;
        sumCiphersInColumnUp(x);
        sumCiphersInColumnUp(x);
        sumCiphersInColumnUp(x);
        moveCiphersInColumnUp(x);*/



       for (int i = 0; i < field.cells.length; i++) {

            //проводим сложение несколько раз, на случай если в одной колонке 4 одинаковых числа, кол-во итерации = высота колонки - 2 крайних числа

            for (int j = 0; j < field.cells.length - 2; j++) {
                sumCiphersInColumnUp(i);

            }
            moveCiphersInColumnUp(i);

        }

    }


    private void moveCiphersInColumnUp(int x) {
        if (isColumnFree(x)) {                                      // если колонка пустая

            return;
        }

        for (FilledCell filledCell : filledCells) {
            if (filledCell.x != x) {                     // заполненные ячейки не из нашей колонки не нужны
                continue;
            }


            if (filledCell.y == 0) {                          // если ячейка самая высокая
                continue;
            }
            if (field.cells[filledCell.y - 1][x] != 0) { // если ячейка выше занята
                continue;
            }
            if (filledCell.y - 2 < 0) {//
                field.cells[filledCell.y - 1][x] = field.cells[filledCell.y][x];
                field.cells[filledCell.y][x] = 0;

                continue;
            }
            if (field.cells[filledCell.y - 2][x] != 0) {
                field.cells[filledCell.y - 1][x] = field.cells[filledCell.y][x];
                field.cells[filledCell.y][x] = 0;

                continue;
            }
            if (filledCell.y - 3 < 0) {
                field.cells[filledCell.y - 2][x] = field.cells[filledCell.y][x];
                field.cells[filledCell.y][x] = 0;

                continue;
            }
            if (field.cells[filledCell.y - 3][x] != 0) {
                field.cells[filledCell.y - 2][x] = field.cells[filledCell.y][x];
                field.cells[filledCell.y][x] = 0;

                continue;
            }
            if (filledCell.y - 4 < 0) {
                field.cells[filledCell.y - 3][x] = field.cells[filledCell.y][x];
                field.cells[filledCell.y][x] = 0;

                continue;
            }


        }

    }


    private void sumCiphersInColumnUp(int x) {

        if (!isColumnShouldProcessed(x)) {                  //  если колонка пустая или в ней нет похожих цифр
            return;
        }

        for (FilledCell filledCell : filledCells) {         // берем каждую заполненную  ячейку из всех колонок
            if (filledCell.x != x) {                      // заполненные ячейки не из нашей колонки не нужны
                continue;
            }
            if (filledCell.y == 0) {                        //ячейка самая высокая
                continue;
            }
            // берем каждую ячейку колонки из массива поля и сравниваем с заполненной ячейкой взятой в line86
            for (int i = 1; i < field.cells.length; i++) {
                if (filledCell.y - i < 0) {         // исключение выхода за границы массива field.cells[-1][x]  при итерации
                    break;
                }
                if (field.cells[filledCell.y - i][x] != 0) { // если выше y-1 ячейка полная

                    if (filledCell.value != field.cells[filledCell.y - i][x]) {             //  значение вышестоящей ячейки не схоже
                        break;
                    } else {                                 // если значений ячейки ниже схоже с ячейкой выше
                        field.cells[filledCell.y - i][x] *= 2;
                        field.cells[filledCell.y][x] = 0;
                        setFilledCells();
                        break;
                    }
                }
            }
        }
    }


    public static Up push() {
        return new Up();
    }
}


