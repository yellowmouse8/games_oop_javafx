package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.exceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.exceptions.OccupiedWayException;

import java.util.Arrays;
import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException
            ,FigureNotFoundException {
        boolean rst = false;
        int index = this.findBy(source);
        int index2 = this.findBy(dest);
                if (index == -1) {
                    throw new FigureNotFoundException();
                }
                Cell[] steps = this.figures[index].way(source, dest);
                for (int ind = 0; ind != steps.length; ind++) {
                    int empty = findBy(steps[ind]);
                    if (empty != -1 || index2 != -1) {
                        throw new OccupiedWayException();
                    }
                }
                    if (steps.length>0 && steps[steps.length-1].equals(dest)){
                        rst = true;
                        this.figures[index] = this.figures[index].copy(dest);
                    }

        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    @Override
    public String toString() {
        return "Logic{" +
                "figures=" + Arrays.toString(this.figures) +
                '}';
    }
}
