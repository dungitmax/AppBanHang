package com.ltd.tandung.amazon_shopping.CustomView;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.ltd.tandung.amazon_shopping.R;

/**
 * Created by letandung on 06/10/2017.
 */

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {
    Drawable crossx, nonecrossx, drawable;
    Boolean visible = false;

    public ClearEditText(Context context) {
        super(context);
        Khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Khoitao();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Khoitao();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Khoitao();
    }

    private void Khoitao() {
        crossx = ContextCompat.getDrawable(getContext(), R.drawable.icondelete).mutate();
        nonecrossx = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();
    }

    private void Caidat() {
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible ? crossx : nonecrossx;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (lengthAfter == 0 && start == 0) {//co chu~
            visible = false;
            Caidat();
        } else {
            visible = true;
            Caidat();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() - drawable.getBounds().width())) {
//            setText("");
//        }

        return super.onTouchEvent(event);
    }
}
