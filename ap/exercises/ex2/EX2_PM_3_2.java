package ap.exercises.ex2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class PacmanGUI5 extends JFrame implements KeyListener {

    Point pacmanPoint = new Point();
    final int width = 500, height = 500, boxSize = 5;
    static int direction = 1;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;


    public PacmanGUI5() {
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        setSize(width, height);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2D);
        setVisible(true);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }


    private void logic() {
        movePacman();
    }

    private void movePacman() {
        int xMovement;
        int yMovement;
        switch (direction) {
            case LEFT:
                xMovement = -1;
                yMovement = 0;
                break;
            case RIGHT:
                xMovement = 1;
                yMovement = 0;
                break;
            case TOP:
                xMovement = 0;
                yMovement = -1;
                break;
            case BOTTOM:
                xMovement = 0;
                yMovement = 1;
                break;
            default:
                xMovement = yMovement = 0;
                break;
        }
        pacmanPoint.setLocation(pacmanPoint.x+xMovement,pacmanPoint.y+yMovement);
        handleCrossBorder();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W)
            direction = 3;
        else if (e.getKeyCode() == KeyEvent.VK_S)
            direction = 4;
        else if (e.getKeyCode() == KeyEvent.VK_A)
            direction = 1;
        else if (e.getKeyCode() == KeyEvent.VK_D)
            direction = 2;
        else if (e.getKeyCode() == KeyEvent.VK_P)
            direction = 0;
        else if (e.getKeyCode() == KeyEvent.VK_Q)
            System.exit(0);
        else
            direction = -1;

        System.out.println("direction:" + direction + "    <- e.getKeyCode()=" + e.getKeyCode());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void handleCrossBorder() {
        if(pacmanPoint.x<2)
            pacmanPoint.x=2;
        else if(pacmanPoint.x>=(width/boxSize))
            pacmanPoint.x=width/boxSize-1;
        if(pacmanPoint.y<7)
            pacmanPoint.y=7;
        else if(pacmanPoint.y>=(height/boxSize))
            pacmanPoint.y=height/boxSize-1;
    }
}

public class EX2_PM_3_2 {
    public static void main(String[] args) {
        PacmanGUI5 frame = new PacmanGUI5();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
