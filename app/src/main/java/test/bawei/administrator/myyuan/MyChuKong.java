package test.bawei.administrator.myyuan;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyChuKong extends Activity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yuan);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setOnTouchListener(new View.OnTouchListener() {
            private float x;
            private float y;
            //用来操作图片的模型 Matrix(相当于一个盒子)
            private Matrix oldMatrix = new Matrix();
            private Matrix newMatrix = new Matrix();

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //判断操作的类型
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //按下时记住X,Y的坐标
                        x = motionEvent.getX();
                        y = motionEvent.getY();
                        //设置 旧的 矩阵
                        oldMatrix.set(iv.getImageMatrix());
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //移动时 用另一个模型 记住原来的位置
                        newMatrix.set(oldMatrix);
                        //移动模型
                        newMatrix.setTranslate(motionEvent.getX() - y, motionEvent.getY() - y);
                        break;
                }
                //把移动后停下来的 模型设置给 图片
                iv.setImageMatrix(newMatrix);
                return true;
            }
        });
    }

}
