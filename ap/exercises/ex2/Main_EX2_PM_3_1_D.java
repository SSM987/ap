package ap.exercises.ex2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


class PacmanGUI4 extends JFrame implements KeyListener {

    Point pacmanPoint = new Point();
    final int width = 300, height = 300, boxSize = 5;
    static int direction = 1;
    final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
    Point dotPoint = new Point();
    int score;
    final int maxScore=15;
    final long maxtime=60000;
    long start;
    boolean finishGame=false;

    public PacmanGUI4() {
        addKeyListener(this);
        pacmanPoint.setLocation((width / boxSize) / 2, (height / boxSize) / 2);
        getNewDotPointLocation();
        setSize(width, height);
        start=System.currentTimeMillis();
    }

    @Override
    public void paint(Graphics g) {
        if(finishGame) {
            GameOverMessage(g);
            return;
        }
        Graphics2D g2D = (Graphics2D) g;
        g.clearRect(0, 0, width, height);
        logic();
        drawPacman(g2D);
        drawDotPoint(g2D);
        drawScore(g2D);
        setVisible(true);
    }

    private void drawPacman(Graphics2D g2d) {
        g2d.setColor(Color.BLUE);
        g2d.fillRect(pacmanPoint.x * boxSize, pacmanPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawDotPoint(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillRect(dotPoint.x * boxSize, dotPoint.y * boxSize, boxSize, boxSize);
    }

    private void drawScore(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        String s = "Score: " + score;
        System.out.println("Score: " + score);
        g2d.drawString(s, 25, 50);
    }

    private void logic() {
        if (dotPoint.x == pacmanPoint.x && dotPoint.y == pacmanPoint.y) {
            score++;
            if(score== maxScore) {
                finishGame = true;
                return;
            }
            getNewDotPointLocation();
        }
        long finish=System.currentTimeMillis();
        long timeElapsed=finish-start;
        if(timeElapsed>=maxtime){
            finishGame=true;
            return;
        }
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

    private void getNewDotPointLocation() {
        Random rand = new Random();
        int delta = boxSize * 2;
        dotPoint.setLocation(rand.nextInt(width / boxSize - 2 * delta) + delta, rand.nextInt(height / boxSize - 2 * delta) + delta);
    }
private void GameOverMessage(Graphics g){
        String message;
        if(score>=maxScore)
            message="You win.";
        else
            message="You lose!Time is up.";
    g.setColor(Color.BLACK);
    g.setFont(new Font("Hello",Font.BOLD,23));
    FontMetrics fontMetrics = g.getFontMetrics();
    int messageH=fontMetrics.getHeight();
    int messageW=fontMetrics.stringWidth(message);
    int x=(width-messageW)/2;
    int y=(height-messageH)/2;
    g.drawString(message,x,y);
}

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(finishGame)
            return;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            direction = 3;
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            direction = 4;
        else if (e.getKeyCode() == KeyEvent.VK_LEFT)
            direction = 1;
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
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

public class Main_EX2_PM_3_1_D {
    public static void main(String[] args) {
        PacmanGUI4 frame = new PacmanGUI4();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
