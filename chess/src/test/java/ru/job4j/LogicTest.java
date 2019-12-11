package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;
import ru.job4j.chess.firuges.black.PawnBlack;
import ru.job4j.chess.firuges.exceptions.FigureNotFoundException;
import ru.job4j.chess.firuges.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.white.BishopWhite;
import ru.job4j.chess.firuges.Cell;

import java.util.concurrent.Callable;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LogicTest {
    @Test
    public void positionTest() {
        BishopBlack position = new BishopBlack(Cell.C8);
        assertThat(position.position(), is(Cell.C8));
    }

    @Test
    public void wayTest() {
        BishopBlack place = new BishopBlack(Cell.C1);
        Cell[] expected = new Cell[]{Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(place.way(Cell.C1, Cell.G5), is(expected));
    }


    @Test
    public void bishopMoveDiagonal() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C8));
        boolean move = logic.move(Cell.C8, Cell.A6);
        assertThat(move, is(true));
    }

    @Test
    public void bishopMoveUpImpossible() {
        Logic logic = new Logic();
        boolean error = false;
        logic.add(new BishopBlack(Cell.C8));
        try {
            logic.move(Cell.C8, Cell.C7);
        } catch (ImpossibleMoveException ime) {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void emptyCellFigure () {
        Logic logic = new Logic();
        boolean error = false;
        logic.add(new BishopBlack(Cell.C8));
        try{
            logic.move(Cell.C7, Cell.C4);
        } catch (FigureNotFoundException fnfe){
            error = true;
        }
        assertTrue(error);
    }

}
