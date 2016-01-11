package fait.game3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
//import android.view.animation.Animation;

/**
 * Created by Tedo on 1/10/2016.
 */
public class Player extends GameObject{
    private Bitmap spritesheet;
    private int speed;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numFrames){
        //starting point
        x = 0;
        y = 0;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i=0; i<image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();
    }

    public void setUp(boolean b){up = b;}

    public void update()
    {
        long elapsed = (System.nanoTime()-startTime)/1000000;
        if(elapsed>100)
        {
            startTime = System.nanoTime();
        }
        animation.update();

        if(up){
            dy = speed;
        }
        else if(down){
            dy = -speed;
        }
        else {
            dy = 0;
        }

        if(right){
            dx = speed;
        }
        else if(left){
            dx = -speed;
        }
        else{
            dx = 0;
        }
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }
}
