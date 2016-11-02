package test.bawei.administrator.myyuan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/11/1.
 */
public class MyView extends View {
    private int circleColor;
    private int arcColor;
    private int textColor;
    private float textSize;
    private String text;
    private int startAngle;
    private int sweepAngle;
    private int mCircleXY;
    private float mRadius;
    private Paint mCirclePaint;
    private RectF mRectF;
    private Paint mArcPaint;
    private Paint mTextPaint;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        //设置 属性特征
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.circleView);
        //判断不为空
        if (ta != null) {
            //设置 对应内圆的颜色
            circleColor = ta.getColor(R.styleable.circleView_circleColor, 0);
            //设置 对应外环的颜色
            arcColor = ta.getColor(R.styleable.circleView_arcColor, Color.RED);
            //设置 对应的文本颜色
            textColor = ta.getColor(R.styleable.circleView_textColor, 0);
            //设置 对应中间文本文字的大小
            textSize = ta.getDimension(R.styleable.circleView_textSize, 50);
            //设置 中间文本
            text = ta.getString(R.styleable.circleView_text);
            //设置 对应外环的起始角度
            startAngle = ta.getInt(R.styleable.circleView_startAngle, 0);
            //设置 对应外环的扫描角度
            sweepAngle = ta.getInt(R.styleable.circleView_sweepAngle, 90);
            //再循环  回收利用
            ta.recycle();
        }


    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取屏幕的 宽-高
        int length = Math.min(w, h);
        //循环XY轴
        mCircleXY = length / 2;
        //得到半径
        mRadius = length * 0.5f / 2;
        //设置可循环的 画笔
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置内圆的颜色
        mCirclePaint.setColor(Color.GREEN);
        //设置 矩形
        mRectF = new RectF(length * 1.0f, length * 1.0f, length * 0.9f, length * 0.9f);

        //设置 弧度画笔
        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置 外环颜色
        mArcPaint.setColor(Color.RED);
        //设置 弧度的风格
        mArcPaint.setStyle(Paint.Style.STROKE);
        //设置 宽度
        mArcPaint.setStrokeWidth(w * 0.1f);

        //设置 字体
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置 字体颜色
        mTextPaint.setColor(textColor);
        //设置 字体大小
        mTextPaint.setTextSize(textSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制   参数(画布canvas)
        drawSth(canvas);
    }

    /**
     * 绘制方法
     *
     * @param canvas
     */
    private void drawSth(Canvas canvas) {
        //draw绘制 Circle循环
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        //绘制 Arc圆弧
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mArcPaint);
        //绘制 文字
        canvas.drawText("李海宽", mCircleXY, mCircleXY + textSize / 4, mTextPaint);
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
