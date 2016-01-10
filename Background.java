package fait.game3;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Tedo on 1/9/2016.
 */
public class Background {

    private Bitmap image;
    private int x, y;

    public Background(Bitmap res)
    {
        image = res;
    }

    public void update()
    {

    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(image,x,y,null);
    }


}
