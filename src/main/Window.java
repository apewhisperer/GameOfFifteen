package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    int[][] matrixGlob;


    public Window(int[][] matrix) {

        setSize(415, 435);
        setTitle("Game of Fifteen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
    }

    public void setMatrixGlob(int[][] matrixGlob) {
        this.matrixGlob = matrixGlob;
    }

    public JButton addButton(int num, int i, int j) {
        JButton numTileButton = new JButton(String.valueOf(num));
        numTileButton.setBounds((j * 100), (i * 100), 100, 100);
        numTileButton.setFont(new Font("Font", Font.BOLD, 50));
        numTileButton.addActionListener(this);
        if (numTileButton.getText().equals("0")) {
            numTileButton.setText("");
        }
        return numTileButton;
    }

    public void paintEndScreen() {

        JLabel winLabel = new JLabel("YOU WIN !");
        winLabel.setBounds(50, 135, 500, 100);
        winLabel.setFont(new Font("Font", Font.BOLD, 60));
        winLabel.setForeground(Color.GREEN);
        add(winLabel);

        JButton movesButton = new JButton("Moves number");
        movesButton.setBounds(130, 270, 150, 25);
        movesButton.setFont(new Font("Font", Font.ITALIC, 15));
        add(movesButton);
        movesButton.addActionListener(this);

        JButton playAgainButton = new JButton("Play again");
        playAgainButton.setBounds(130, 300, 150, 25);
        playAgainButton.setFont(new Font("Font", Font.ITALIC, 15));
        add(playAgainButton);
        playAgainButton.addActionListener(this);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(130, 330, 150, 25);
        closeButton.setFont(new Font("Font", Font.ITALIC, 15));
        add(closeButton);
        closeButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton source = (JButton) e.getSource();
        switch (source.getText()) {
            case "Play again":
                GameOfFifteen.counter = 0;
                GameOfFifteen.main(null);
                break;
            case "Close":
                dispose();
                break;
            case "Moves number":
                JLabel counterLabel = new JLabel("Number of moves: " + String.valueOf(GameOfFifteen.counter));
                counterLabel.setBounds(50, 50, 500, 100);
                counterLabel.setFont(new Font("Font", Font.PLAIN, 14));
                this.add(counterLabel);
                this.repaint();
                break;
            case "":
                break;
            default:
                int value = Integer.parseInt(source.getText());
//        System.out.println(value);
                GameOfFifteen.play(matrixGlob, value);
                break;
        }
    }
}