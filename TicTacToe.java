// 狀態列舉：遊戲進行中 / X 贏 / O 贏 / 平手
enum GameState {
    IN_PROGRESS,
    X_WIN,
    O_WIN,
    DRAW
}

public class TicTacToe {

    private final char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private int moves = 0;
    private GameState state = GameState.IN_PROGRESS;

    public TicTacToe() {
        // 初始化空棋盤
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = ' ';
            }
        }
    }

    // 取得目前遊戲狀態（相當於 evaluate）
    public GameState getState() {
        return state;
    }

    // 取得目前輪到誰下（X 或 O）
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // 取得某格的內容（方便測試用）
    public char getCell(int row, int col) {
        checkRange(row, col);
        return board[row][col];
    }

    // 玩家下棋：row, col 介於 0~2
    public void set(int row, int col) {
        if (state != GameState.IN_PROGRESS) {
            throw new IllegalStateException("Game already finished: " + state);
        }

        checkRange(row, col);

        if (board[row][col] != ' ') {
            throw new IllegalArgumentException("Cell already taken at (" + row + "," + col + ")");
        }

        board[row][col] = currentPlayer;
        moves++;

        // 更新遊戲狀態
        updateState();

        // 如果遊戲還沒結束，換人
        if (state == GameState.IN_PROGRESS) {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    // ====== 內部工具方法 ======

    private void checkRange(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IllegalArgumentException("Row/Col must be in [0,2]");
        }
    }

    private void updateState() {
        if (hasWon('X')) {
            state = GameState.X_WIN;
        } else if (hasWon('O')) {
            state = GameState.O_WIN;
        } else if (moves == 9) {
            state = GameState.DRAW;
        } else {
            state = GameState.IN_PROGRESS;
        }
    }

    private boolean hasWon(char p) {
        // 檢查列
        for (int r = 0; r < 3; r++) {
            if (board[r][0] == p && board[r][1] == p && board[r][2] == p) {
                return true;
            }
        }
        // 檢查行
        for (int c = 0; c < 3; c++) {
            if (board[0][c] == p && board[1][c] == p && board[2][c] == p) {
                return true;
            }
        }
        // 主對角線
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) {
            return true;
        }
        // 副對角線
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) {
            return true;
        }
        return false;
    }
}
