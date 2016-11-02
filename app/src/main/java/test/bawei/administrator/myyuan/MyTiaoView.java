package test.bawei.administrator.myyuan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyTiaoView extends View {

    private static final int OFFSET = 5;
    private Paint mPaint;
    private int mCount;
    private int mWidth;
    private double mRandom;
    private int mRectHeight;
    private int mRectWidth;
    private LinearGradient mLinearGradient;

    public MyTiaoView(Context context) {
        this(context,null);
    }

    public MyTiaoView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTiaoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);//设置颜色
        mPaint.setStyle(Paint.Style.FILL);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.volumneView);
        if (ta != null) {
            mCount = ta.getInt(R.styleable.volumneView_count, 6);
            ta.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //测量 宽度/高度
        mWidth = getMeasuredWidth();
        //得到高度
        mRectHeight = getMeasuredHeight();
        //得到矩形的 宽度
        mRectWidth = (int) (mWidth * 0.8 / mCount);
        mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight,
                Color.GREEN, Color.YELLOW, Shader.TileMode.CLAMP);
        //设置 着色器
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mCount; i++) {
            mRandom = Math.random();
            float mcurrentHeight = (float) (mRectHeight * mRandom);
            float width = (float) (mWidth * 0.4 / 2 + OFFSET);
            canvas.drawRect(width + i * mRectWidth, mcurrentHeight, width
                    + (i + 1) * mRectWidth, mRectHeight, mPaint);
        }
        postInvalidateDelayed(300);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
