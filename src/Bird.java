import java.awt.*;

public class Bird {
    private int x,y, width=16, height=16;
    private double yUpdate = 0, maxYUpdate = 8;
    public Bird() {
        setStartPosition();
    }


    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x*GameScreen.getScreenScale(),y*GameScreen.getScreenScale(),width*GameScreen.getScreenScale(),height*GameScreen.getScreenScale());
    }
    public void jump(){
        yUpdate = -3;
    }

    public void setStartPosition(){
        x= 20;
        y = 40;
        yUpdate =0;
    }
    public void tick() {
        verifyRoofCollision();
        fallFromGravity();
        verifyFloorCollision();
    }
    public void verifyRoofCollision(){
        if(y+yUpdate< 0){
            y = 0;
            yUpdate =0;
        }
    }
    public void verifyFloorCollision(){
        if(y > GameScreen.getScreenHeight()/GameScreen.getScreenScale()){
            Game.resetGame();
        }
    }
    public void fallFromGravity(){
        y+=yUpdate;
        if(yUpdate <= maxYUpdate)
        yUpdate += 0.25;
    }
    public Rectangle getHitbox(){
        Rectangle Hitbox = new Rectangle(x,y,width,height);
        return Hitbox;
    }
}
