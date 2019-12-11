package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        boolean valid = false;
        Cell[] steps = new Cell[0];
        int begin = Math.abs(source.x - dest.x);
        if (source.y == dest.y + begin && source.x == dest.x + begin){
            steps = new Cell[begin];
            for (int index = 0; index!=steps.length; index++){
                steps[index] = Cell.findCell(source.x - index -1, source.y - index -1 );
            }
            valid = true;
        }else  if(source.y == dest.y + begin && source.x == dest.x - begin){
            steps = new Cell[begin];
            for (int index = 0; index !=steps.length; index++){
                steps[index] = Cell.findCell(source.x + index + 1, source.y - index - 1);
            }
            valid = true;
        } else if (source.y == dest.y - begin && source.x == dest.x + begin){
            steps = new Cell[begin];
            for (int index = 0; index != steps.length; index++){
                steps[index] = Cell.findCell(source.x - index - 1, source.y + index + 1);
            }
            valid = true;
        }else  if(source.y == dest.y - begin && source.x == dest.x -begin){
            steps = new Cell[begin];
            for (int index = 0; index != steps.length; index++){
                steps[index] = Cell.findCell(source.x + index + 1, source.y + index +1 );
            }
            valid = true;
        }
        if (!valid){
            throw new ImpossibleMoveException();
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
