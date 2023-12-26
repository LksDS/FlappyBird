import java.awt.*;

public class Pipe {
    private int width=16, height=50;
    private double x,y;
    public Pipe(Position pos,int height){
        this.x = pos.x;
        this.y = pos.y;
        this.height= height;
    }
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect((int) (x*GameScreen.getScreenScale()), (int) (y*GameScreen.getScreenScale()),width*GameScreen.getScreenScale(),height*GameScreen.getScreenScale());
    }
    public void tick(){
        x-= 1;
        if(x+width<0){
            Game.pipes.remove(this);
        }
        verifyCollisionPlayer();
    }
    public void verifyCollisionPlayer(){
        Rectangle pipeRect = new Rectangle((int) x, (int) y,width,height);
        if(pipeRect.intersects(Game.bird.getHitbox())){
            Game.resetGame();
        }
    }
    public double getX(){
        return this.x;
    }
}
