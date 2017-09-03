package com.example.tjjunlintx.diyview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tjjunlintx.diyview.R;

/**
 * Created by tjjunlintx on 2017/6/19.
 */

public class MyTextView extends TextView {
    private String mText = "少时诵诗书";
    private int TextColor;
    private float TextSize;
    private Paint paint;
    private Rect rect;
    private int height;
    public MyTextView(Context context) {
        this(context,null);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText = a.getString(R.styleable.MyTextView_mtext);
        TextColor = a.getColor(R.styleable.MyTextView_textcolor, Color.GREEN);
        TextSize = a.getDimension(R.styleable.MyTextView_textsize,0);
        a.recycle();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setTextSize(TextSize);
        paint.setColor(TextColor);
        rect = new Rect();
        paint.getTextBounds(mText,0,mText.length(),rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        if(heightMode == MeasureSpec.EXACTLY){
            setMeasuredDimension(width,height);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(width,height);
        }else{
            setMeasuredDimension(width,height);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText,0,height/2,paint);
    }
}
