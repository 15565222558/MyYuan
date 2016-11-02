package test.bawei.administrator.myyuan;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyDuoDianCK extends Activity {

    private int wWidth;
    private int wHeight;
    private Bitmap bitmap;
    private int width;
    private int height;
    private SurfaceHolder holder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //得到当前屏幕的 宽/高
        wWidth = getWindowManager().getDefaultDisplay().getWidth();
        wHeight = getWindowManager().getDefaultDisplay().getHeight();
        //定义图片工厂 放入图片
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a118);
        //得到 图片的宽/高
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        //用屏幕的 宽高 -图片的宽高/2
        int imageX = (wWidth - width) / 2;
        int imageY = (wHeight - height) / 2;
        MyLihk myLihk = new MyLihk(MyDuoDianCK.this);
        setContentView(myLihk);
    }

    private class MyLihk extends SurfaceView implements SurfaceHolder.Callback {

        public MyLihk(Context context) {
            super(context);
            holder = getHolder();
            holder.addCallback(this);
            //设置它 是可以聚焦的
            setFocusable(true);//获得焦点,进行触摸事件
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            setImage(1, 350, 500);
        }

        private void setImage(float scale, int width, int height) {
            //获取画布
            Canvas canvas = holder.lockCanvas();
            //设置画笔
            Paint paint = new Paint();
            //绘制矩形 的宽/高度
            canvas.drawRect(0, 0, wWidth, wHeight, paint);


            //创建一个 工具盒子
            Matrix matrix = new Matrix();
            //设置它的比例(权重)
            matrix.postScale(scale, scale);
            //target目标
            Bitmap target = Bitmap.createBitmap(MyDuoDianCK.this.bitmap, 0, 0, width, height, matrix, true);
            //得到宽/高
            int imageWidth = target.getWidth();
            int imageHeight = target.getHeight();
            //再次计算 用屏幕的 宽高-当前图片的宽高/2
            int imageX = (wWidth - imageWidth) / 2;
            int imageY = (wHeight - imageHeight) / 2;
            //把计算好的 图片宽高度 装进画布中   平移到其他位置
            canvas.translate(imageX, imageY);
            canvas.drawBitmap(bitmap, matrix, paint);
            //解锁,并提交图像
            holder.unlockCanvasAndPost(canvas);
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }

        @Override   //触摸事件
        public boolean onTouchEvent(MotionEvent event) {
            int pointerCount = event.getPointerCount();
            if (pointerCount == 2) {
                float pointA = event.getY(0);
                float pointB = event.getY(1);
                if (pointA < pointB) {
                    float temp = pointA;
                    pointA = pointB;
                    pointB = temp;
                }
                if (!(event.getAction() == MotionEvent.ACTION_UP)) {
                    float scale = getScale(pointA, pointB);
                    setImage(scale, 350, 500);
                }
            }

            return super.onTouchEvent(event);
        }

        private float getScale(float pointA, float pointB) {
            float scanle = pointA / pointB;
            return scanle;
        }
    }


}
