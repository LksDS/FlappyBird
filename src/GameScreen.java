import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JFrame implements KeyListener {
    private static int width = 150, height = 200;
    private static int scale = 3;
    Game game;
    public void setGame(Game game){
        this.game = game;
    }
    public GameScreen(){
        width = width*scale;
        height = height*scale;
        initScreen();
    }
    public void initScreen(){
        setTitle("SnakeGame");
        setPreferredSize(new Dimension(width,height));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        pack();
        setVisible(true);
        addKeyListener(this);
        setLocationRelativeTo(null);
    }
    public static int getScreenHeight(){
        return height;
    }
    public static int getScreenScale(){
        return scale;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                game.bird.jump();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
