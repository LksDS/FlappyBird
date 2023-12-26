import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Runnable {
    Thread gameThread;
    GameScreen screen;
    public static Bird bird;
    int count = 0;
    static ArrayList<Pipe> pipes = new ArrayList<>();

    public Game() {
        screen = new GameScreen();
        screen.setGame(this);
        bird = new Bird();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ticksPerSecond = 30;
        double ns = 1000000000 / ticksPerSecond;
        double delta = 0;
        double timer = System.currentTimeMillis();
        screen.requestFocus();
        while (gameThread != null) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;

            }
            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
            }
        }
    }

    public void tick() {
        bird.tick();
            for (int i = 0; i < pipes.size(); i++) {
                pipes.get(i).tick();
            }
            count += 1;
            if (count >= 80) {
                count = 0;
                addPipes();
        }
    }
        public static void resetGame () {
            pipes.clear();
            bird.setStartPosition();
        }
        public void addPipes() {
            Random rand = new Random();
            int topHeight = 20 + rand.nextInt(70);
            pipes.add(new Pipe(new Position(150, 0), topHeight));
            pipes.add(new Pipe(new Position(150, topHeight + 50), screen.getHeight() - topHeight + 50));
        }


        public void render() {
            if (screen.getBufferStrategy() == null)
                screen.createBufferStrategy(3);
            BufferStrategy bs = screen.getBufferStrategy();
            Graphics g = bs.getDrawGraphics();
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, screen.getWidth(), screen.getHeight());
            bird.render(g);
            for (Pipe pipe : pipes)
                pipe.render(g);
            g.dispose();
            bs.show();
        }

        public void startGameThread() {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


