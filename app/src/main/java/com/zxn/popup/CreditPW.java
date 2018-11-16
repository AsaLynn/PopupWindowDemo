package com.zxn.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 信誉认证气泡.
 * Created by zxn on 2018/11/14.
 */
public class CreditPW
        extends PopupWindow
        implements PopupWindow.OnDismissListener, View.OnClickListener {

    private static CreditPW mCreditPWInstance;
    private String TAG = "CreditPW";
    private int mOffsetViewDistance;
    private boolean mIsLeftSide;
    private int mScreenWidth;
    private int mPwWidth;

    public CreditPW(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mOffsetViewDistance = context.getResources().getDimensionPixelSize(R.dimen.dp_6);
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mScreenWidth = getScreenWidth(wmManager);
        TextView contentView = new TextView(context);
        contentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
        contentView.setTextColor(context.getResources().getColor(R.color.c_ffffff));
        contentView.setText(R.string.text_user_of_3am_official_certification);
        contentView.setGravity(Gravity.CENTER_HORIZONTAL);
        contentView.setPadding(0, context.getResources().getDimensionPixelSize(R.dimen.dp_2), 0, 0);
        contentView.setBackgroundResource(R.drawable.bg_d_credit_pw_left);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        setContentView(contentView);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setOutsideTouchable(true);
        setClippingEnabled(true);//
        setTouchable(false);
        setOnDismissListener(this);
    }


    @Override
    public void onDismiss() {
        Log.i(TAG, "onDismiss: --->");
    }

    @Override
    public void onClick(View v) {
        showAsDropDown(v);
    }

    public static CreditPW getInstance(Context context) {
        if (null == mCreditPWInstance) {
            mCreditPWInstance = new CreditPW(context);
        }
        return mCreditPWInstance;
    }

    public void setOffsetViewDistance(int distance) {
        this.mOffsetViewDistance = distance;
    }

    @Override
    public void showAsDropDown(View anchor) {

        mPwWidth = getContentView().getMeasuredWidth();
        int[] location = new int[2];
        anchor.getLocationInWindow(location);

        //y坐标方向定位
        int popupHeight = getContentView().getMeasuredHeight();
        int ySpace = mOffsetViewDistance;
        int yOffset = -(anchor.getHeight() + popupHeight + ySpace);

        if (anchor instanceof TextView) {
            TextView v = (TextView) anchor;
            if (v.getCompoundDrawables()[0] == null && v.getCompoundDrawables()[2] == null) {
                showViewPoppup(anchor, yOffset, location);
            } else {
                showTextImgPoppup(v, yOffset, location);
            }
        } else {
            //普通正常view的弹框显示.
            showViewPoppup(anchor, yOffset, location);
        }
    }

    private void showTextImgPoppup(TextView v, int yOffset, int[] location) {
        //x坐标方向定位
        int xPadding = v.getCompoundDrawablePadding();
        if (v.getCompoundDrawables()[0] != null) {
            Drawable drawable = v.getCompoundDrawables()[0];
            int xSpace = drawable.getIntrinsicWidth() / 2;
            int leftDis = location[0] + xPadding + xSpace;
            if (leftDis >= mPwWidth) {
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_right);
                int xOffset = -(getContentView().getMeasuredWidth() - xSpace - xPadding);
                showAsDropDown(v, xOffset, yOffset);
            } else {
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_left);
                int xOffset = xSpace + xPadding;
                showAsDropDown(v, xOffset, yOffset);
            }
        } else {//v.getCompoundDrawables()[2] != null
            Drawable drawable = v.getCompoundDrawables()[2];
            int xSpace = drawable.getIntrinsicWidth() / 2;
            int rightDis = mScreenWidth - (location[0] + v.getWidth() - xPadding - xSpace);
            if (rightDis >= mPwWidth) {
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_left);
                int xOffset = v.getWidth() - xSpace - xPadding;
                showAsDropDown(v, xOffset, yOffset);
            } else {
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_right);
                int xOffset = v.getWidth() - mPwWidth - xSpace - xPadding;
                showAsDropDown(v, xOffset, yOffset);
            }
        }
    }

    private void showViewPoppup(View anchor, int yOffset, int[] location) {
        int leftDistance = location[0] + anchor.getWidth() / 2;
        int rightDistance = mScreenWidth - location[0] - anchor.getWidth() / 2;
        int xOffset = 0;
        if (leftDistance < mPwWidth) {//左对齐
            getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_left);
            xOffset = anchor.getWidth() / 2;
        } else if (rightDistance < mPwWidth) {//右对齐
            getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_right);
            xOffset = -(mPwWidth - anchor.getWidth() / 2);
        } else {
            if (leftDistance <= rightDistance) {//左对齐
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_left);
                xOffset = anchor.getWidth() / 2;
            } else {//右对齐
                getContentView().setBackgroundResource(R.drawable.bg_d_credit_pw_right);
                xOffset = -(mPwWidth - anchor.getWidth() / 2);
            }
        }
        showAsDropDown(anchor, xOffset, yOffset);
    }

    public static int getScreenWidth(WindowManager windowManager) {
        int widthPixels = 0;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        if (aboveApiLevel(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
                && !aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(defaultDisplay);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (aboveApiLevel(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            android.graphics.Point realSize = new android.graphics.Point();
            defaultDisplay.getRealSize(realSize);
            widthPixels = realSize.x;
        } else {
            DisplayMetrics metrics = new DisplayMetrics();
            defaultDisplay.getMetrics(metrics);
            widthPixels = metrics.widthPixels;
        }
        return widthPixels;
    }

    public static boolean aboveApiLevel(int sdkInt) {
        return getApiLevel() >= sdkInt;
    }

    public static int getApiLevel() {
        return Build.VERSION.SDK_INT;
    }

}
