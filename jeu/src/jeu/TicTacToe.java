package jeu;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame {
    private char[][] board;
    private char currentPlayer;
    private JButton[][] buttons;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        buttons = new JButton[3][3];

        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeBoard();
        setVisible(true);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 60));
                final int x = i;
                final int y = j;
                button.addActionListener(e -> play(x, y));
                buttons[i][j] = button;
                add(button);
                board[i][j] = ' ';
            }
        }
    }

    public void play(int x, int y)  {
        try {
            validateMove(x, y);
            board[x][y] = currentPlayer;
            buttons[x][y].setText(String.valueOf(currentPlayer));
            if (checkWinner()) {
                JOptionPane.showMessageDialog(this, "Le joueur " + currentPlayer + " a gagné!");
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "Match nul!");
                resetGame();
            } else {
                switchPlayer();
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void validateMove(int x, int y) {
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            throw new RuntimeException("Coordonnées hors limite!");
        }
        if (board[x][y] != ' ') {
            throw new RuntimeException("Case déjà occupée!");
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean checkWinner() {
        // Vérification des lignes et colonnes
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true;
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true;
        }
        // Vérification des diagonales
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true;
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
