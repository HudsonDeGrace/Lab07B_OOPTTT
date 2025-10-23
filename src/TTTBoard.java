import javax.swing.*;

public class TTTBoard extends JFrame {
    public TTTBoard() {
        TTTTile[][] board = new TTTTile[3][3];

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                board[row][col] = new TTTTile(row, col);
            }
        }
    }
}
