import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class TTTBoard extends JFrame {

    JPanel MainPnl, TitlePnl, ContentPnl, BtnPnl;
    JLabel TitleLbl;
    JButton ResetBtn, QuitBtn;

    TTTTile[][] board = new TTTTile[3][3];
    String player = "X";
    int gameTurns = 0;
    boolean playing = true;

    public TTTBoard() {
        super("Tic Tac Toe");

        MainPnl = new JPanel();
        MainPnl.setLayout(new BorderLayout());
        add(MainPnl);
        createContentPnl();
        createTitlePnl();
        createBtnPnl();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setVisible(true);
    }

    private void createBtnPnl() {
        BtnPnl = new JPanel();
        BtnPnl.setLayout(new GridLayout(1,2));
        ResetBtn = new JButton("Reset");
        QuitBtn = new JButton("Quit");
        BtnPnl.add(ResetBtn);
        BtnPnl.add(QuitBtn);
        QuitBtn.addActionListener(_ -> System.exit(0));
        ResetBtn.addActionListener(_ -> resetGame());
        MainPnl.add(BtnPnl, BorderLayout.SOUTH);
    }

    private void createTitlePnl() {
        TitlePnl = new JPanel();
        TitleLbl = new JLabel("Tic Tac Toe");
        TitleLbl.setFont(new Font("Times New Roman", Font.BOLD, 24));
        TitleLbl.setHorizontalAlignment(JLabel.CENTER);
        TitleLbl.setVerticalAlignment(JLabel.CENTER);
        TitlePnl.add(TitleLbl);
        MainPnl.add(TitlePnl, BorderLayout.NORTH);
    }

    private void createContentPnl() {
        ContentPnl = new JPanel();
        ContentPnl.setLayout(new GridLayout(3, 3));

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                board[row][col] = new TTTTile(row, col);
                ContentPnl.add(board[row][col]);
                board[row][col].setText(" ");
                board[row][col].setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                board[row][col].setBorder(BorderFactory.createLineBorder(Color.black, 1));
                board[row][col].addActionListener(new ButtonListener());
            }
        }

        MainPnl.add(ContentPnl, BorderLayout.CENTER);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(playing) {
                for (TTTTile[] row : board) {
                    for (TTTTile tile : row) {
                        if (e.getSource() == tile) {
                            if (tile.getText().equals(" ")) {
                                tile.setText(player);
                                gameTurns++;
                                if (gameTurns >= 5) {
                                    isWin();
                                }
                                if(gameTurns >= 7){
                                    if(isTie()){
                                        JOptionPane.showMessageDialog(MainPnl, "Both players tie!", "Tie", JOptionPane.INFORMATION_MESSAGE);
                                        if(JOptionPane.showConfirmDialog(MainPnl, "Do you want to continue?", "Continue?", JOptionPane.YES_NO_OPTION) == 0){
                                            resetGame();
                                        }else{
                                            System.exit(0);
                                        }
                                    }
                                }
                                if (Objects.equals(player, "X")) {
                                    player = "O";
                                } else {
                                    player = "X";
                                }
                            } else {
                                JOptionPane.showMessageDialog(MainPnl, "Input Error\nPlease make a valid move", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        }
    }

    private void resetGame(){
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
        gameTurns = 0;
        player = "O";
    }

    private boolean isTie(){
        boolean xFlag = false;
        boolean oFlag = false;
// Check all 8 win vectors for an X and O so
// no win is possible
// Check for row ties
        for(int row=0; row < 3; row++)
        {
            if(board[row][0].getText().equals("X") ||
                    board[row][1].getText().equals("X") ||
                    board[row][2].getText().equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].getText().equals("O") ||
                    board[row][1].getText().equals("O") ||
                    board[row][2].getText().equals("O"))
            {
                oFlag = true; // there is an O in this row
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }
            xFlag = oFlag = false;
        }
// Now scan the columns
        for(int col=0; col < 3; col++)
        {
            if(board[0][col].getText().equals("X") ||
                    board[1][col].getText().equals("X") ||
                    board[2][col].getText().equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].getText().equals("O") ||
                    board[1][col].getText().equals("O") ||
                    board[2][col].getText().equals("O"))
            {
                oFlag = true; // there is an O in this col
            }
            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;
        if(board[0][0].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][2].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][2].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;
        if(board[0][2].getText().equals("X") ||
                board[1][1].getText().equals("X") ||
                board[2][0].getText().equals("X") )
        {
            xFlag = true;
        }
        if(board[0][2].getText().equals("O") ||
                board[1][1].getText().equals("O") ||
                board[2][0].getText().equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        // Checked every vector so I know I have a tie
        return true;

    }

    private void isWin(){
        if(isColWin() || isRowWin() || isDiagonalWin())
        {
            JOptionPane.showMessageDialog(MainPnl, player + " wins!", "You Win!", JOptionPane.INFORMATION_MESSAGE);
            if(JOptionPane.showConfirmDialog(MainPnl, "Do you want to continue?", "Continue?", JOptionPane.YES_NO_OPTION) == 0){
                resetGame();
            }else{
                System.exit(0);
            }
        }
    }

    private boolean isDiagonalWin() {
        if(board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player) )
        {
            return true;
        }
        if(board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player) )
        {
            return true;
        }
        return false;
    }

    private boolean isRowWin() {
        for(int row=0; row < 3; row++)
        {
            if(board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin() {
        for(int col=0; col < 3; col++)
        {
            if(board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player))
            {
                return true;
            }
        }
        return false;
    }
}
