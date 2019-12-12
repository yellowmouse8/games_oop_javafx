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

    private boolean isDiagonal(Cell source, Cell dest) {
        return Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y);
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (!this.isDiagonal(source, dest)) {
            throw new ImpossibleMoveException();
        }
        Cell[] steps = new Cell[Math.max(source.x, dest.x) - Math.min(source.x, dest.x)];
        int deltaX = dest.x > source.x ? 1 : -1;
        int deltaY = dest.y > source.y ? 1 : -1;
        for (int index = 1; index <= steps.length; index++) {
            steps[index - 1] = Cell.findCell(source.x + index * deltaX, source.y + index * deltaY);
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
