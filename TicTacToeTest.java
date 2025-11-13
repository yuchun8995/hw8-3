import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TicTacToeTest {

    @Test
    void gameStartsInProgressAndXFirst() {
        TicTacToe game = new TicTacToe();
        assertEquals(GameState.IN_PROGRESS, game.getState());
        assertEquals('X', game.getCurrentPlayer());
    }

    @Test
    void xWinsOnTopRow() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0); // X
        game.set(1, 0); // O
        game.set(0, 1); // X
        game.set(1, 1); // O
        game.set(0, 2); // X wins

        assertEquals(GameState.X_WIN, game.getState());
    }

    @Test
    void oWinsOnFirstColumn() {
        TicTacToe game = new TicTacToe();
        game.set(0, 1); // X
        game.set(0, 0); // O
        game.set(1, 1); // X
        game.set(1, 0); // O
        game.set(2, 2); // X
        game.set(2, 0); // O wins

        assertEquals(GameState.O_WIN, game.getState());
    }

    @Test
    void xWinsOnDiagonal() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0); // X
        game.set(0, 1); // O
        game.set(1, 1); // X
        game.set(0, 2); // O
        game.set(2, 2); // X wins

        assertEquals(GameState.X_WIN, game.getState());
    }

    @Test
    void drawGame() {
        TicTacToe game = new TicTacToe();
        // 一種平手盤面
        game.set(0, 0); // X
        game.set(0, 1); // O
        game.set(0, 2); // X
        game.set(1, 1); // O
        game.set(1, 0); // X
        game.set(1, 2); // O
        game.set(2, 1); // X
        game.set(2, 0); // O
        game.set(2, 2); // X

        assertEquals(GameState.DRAW, game.getState());
    }

    @Test
    void cannotPlaceOnTakenCell() {
        TicTacToe game = new TicTacToe();
        game.set(0, 0); // X already here

        assertThrows(IllegalArgumentException.class, () -> {
            game.set(0, 0); // 再下同一格
        });
    }
}
