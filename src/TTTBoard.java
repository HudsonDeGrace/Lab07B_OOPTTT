import javax.swing.*;
import java.awt.*;

public class TTTBoard extends JFrame {

    JPanel MainPnl, TitlePnl, ContentPnl, BtnPnl;
    JLabel TitleLbl;

    public TTTBoard() {
        super("Tic Tac Toe");

        MainPnl = new JPanel();
        MainPnl.setLayout(new BorderLayout());
        add(MainPnl);
        createContentPnl();
        createTitlePnl();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 600);
        setVisible(true);
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
        TTTTile[][] board = new TTTTile[3][3];

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                board[row][col] = new TTTTile(row, col);
                ContentPnl.add(board[row][col]);
                board[row][col].setBorder(BorderFactory.createLineBorder(Color.black, 1));
            }
        }

        MainPnl.add(ContentPnl, BorderLayout.CENTER);
    }
}
