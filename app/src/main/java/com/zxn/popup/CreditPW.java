package com.zxn.popup;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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

    public CreditPW(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mOffsetViewDistance = context.getResources().getDimensionPixelSize(R.dimen.dp_6);
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
        if (anchor instanceof TextView) {
            TextView v = (TextView) anchor;
            //x坐标方向定位
            mIsLeftSide = v.getCompoundDrawables()[0] != null;
            getContentView()
                    .setBackgroundResource(mIsLeftSide ? R.drawable.bg_d_credit_pw_right : R.drawable.bg_d_credit_pw_left);

            Drawable drawable = mIsLeftSide ? v.getCompoundDrawables()[0] : v.getCompoundDrawables()[2];
            int xSpace = drawable == null ? 0 : drawable.getIntrinsicWidth() / 2;
            int xPadding = v.getCompoundDrawablePadding();
            //左侧便宜距离,右侧侧便宜距离,
            int xOffset = mIsLeftSide ? -(getContentView().getMeasuredWidth() - xSpace - xPadding) : (v.getWidth() - xSpace - xPadding);

            //y坐标方向定位
            int popupHeight = getContentView().getMeasuredHeight();
            int ySpace = mOffsetViewDistance;
            int yOffset = -(v.getHeight() + popupHeight + ySpace);
            showAsDropDown(v, xOffset, yOffset);
        } else {
            super.showAsDropDown(anchor);
        }
    }


}
