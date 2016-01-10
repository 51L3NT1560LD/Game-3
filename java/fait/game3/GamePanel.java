package fait.game3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by Tedo on 1/9/2016.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback
{
    public float bgWidth;
    public float bgHeight;
    private float devWidth;
    private float devHeight;
    private MainThread thread;
    private Background bg;


    public GamePanel (Context context)
    {
        super(context);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        devWidth = size.x;
        devHeight = size.y;

        //add the callback to the surfaceHolder to intercept events
        getHolder().addCallback(this);

        thread = new MainThread (getHolder(), this);

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry) {
            try{
                thread.setRunning(false);
                thread.join();
            }catch(InterruptedException e){e.printStackTrace();}

            retry = false;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.rpgback);
        bgWidth = image.getWidth();
        bgHeight = image.getHeight();
        bg = new Background(image);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public boolean onTouchEvent (MotionEvent event)
    {
        return super.onTouchEvent(event);
    }

    public void update()
    {
        bg.update();
    }

    @Override
    public void draw(Canvas canvas){
        //finding phone resolution


        final float scaleFactorX = devWidth/bgWidth;
        final float scaleFactorY = devHeight/bgHeight;
        System.out.println("Screen Width = " + getWidth());
        System.out.println("Screen Height = " + getHeight());
        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX,scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
