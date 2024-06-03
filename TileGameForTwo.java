import java.awt.*;
import java.awt.event.*;

class TileGameForTwo extends Frame implements KeyListener {
    Button[] buttons1 = new Button[9];
    Button[] buttons2 = new Button[9];
    Label lb, lb1, lb2;

    public TileGameForTwo() {
        setLayout(null);

        lb1 = new Label("Player 1");
        lb1.setBounds(90, 50, 70, 30);
        add(lb1);

        lb2 = new Label("Player 2");
        lb2.setBounds(340, 50, 70, 30);
        add(lb2);

        lb = new Label();
        lb.setBounds(200, 300, 200, 30);
        add(lb);

        for (int i = 0; i < 9; i++) {
            buttons1[i] = new Button(i == 2 ? "" : String.valueOf(i + 1));
            buttons1[i].setBounds(50 + (i % 3) * 50, 100 + (i / 3) * 50, 40, 40);
            buttons1[i].addKeyListener(this);
            add(buttons1[i]);

            buttons2[i] = new Button(i == 1 ? "" : String.valueOf(i + 1));
            buttons2[i].setBounds(300 + (i % 3) * 50, 100 + (i / 3) * 50, 40, 40);
            buttons2[i].addKeyListener(this);
            add(buttons2[i]);
        }
    }

    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        Button[] currentButtons = null;

        if (ke.getSource() instanceof Button) {
            Button sourceButton = (Button) ke.getSource();
            for (int i = 0; i < 9; i++) {
                if (sourceButton == buttons1[i]) {
                    currentButtons = buttons1;
                    break;
                } else if (sourceButton == buttons2[i]) {
                    currentButtons = buttons2;
                    break;
                }
            }
        }

        if (currentButtons != null) {
            moveButton(currentButtons, key);
            checkWin(currentButtons);
        }
    }

    private void moveButton(Button[] buttons, int key) {
        int emptyIndex = -1;

        for (int i = 0; i < 9; i++) {
            if (buttons[i].getLabel().equals("")) {
                emptyIndex = i;
                break;
            }
        }

        int swapIndex = -1;

        if (key == KeyEvent.VK_LEFT && emptyIndex % 3 < 2) {
            swapIndex = emptyIndex + 1;
        } else if (key == KeyEvent.VK_RIGHT && emptyIndex % 3 > 0) {
            swapIndex = emptyIndex - 1;
        } else if (key == KeyEvent.VK_UP && emptyIndex < 6) {
            swapIndex = emptyIndex + 3;
        } else if (key == KeyEvent.VK_DOWN && emptyIndex > 2) {
            swapIndex = emptyIndex - 3;
        }

        if (swapIndex != -1) {
            buttons[emptyIndex].setLabel(buttons[swapIndex].getLabel());
            buttons[swapIndex].setLabel("");
        }
    }

    private void checkWin(Button[] buttons) {
        boolean isPlayer1 = buttons == buttons1;
        boolean win = true;

        for (int i = 0; i < 8; i++) {
            if (!buttons[i].getLabel().equals(String.valueOf(i + 1))) {
                win = false;
                break;
            }
        }

        if (win && buttons[8].getLabel().equals("")) {
            lb.setText(isPlayer1 ? "Player 1 wins the Match...!!!" : "Player 2 wins the Match...!!!");
        }
    }

    public void keyTyped(KeyEvent ke) {}

    public void keyReleased(KeyEvent ke) {}

    public static void main(String[] args) {
        TileGameForTwo p = new TileGameForTwo();
        p.setTitle("Puzzle_Game for two Players");
        p.setSize(500, 400);
        p.setVisible(true);
    }
}

