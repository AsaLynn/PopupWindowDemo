package com.zxn.popup;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * PopupWindow的用法
 * Created by zxn on 2018-11-14 15:54:38.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.tv13)
    TextView tv13;
    @BindView(R.id.tv14)
    TextView tv14;
    @BindView(R.id.tv15)
    TextView tv15;
    @BindView(R.id.tv16)
    TextView tv16;
    private CreditPW mCreditPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mCreditPW = new CreditPW(this);
        //mCreditPW = CreditPW.getInstance(this);

        /*Drawable drawable = getResources().getDrawable(R.drawable.ic_auth);
        tv11.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.dp_3));
        tv11.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        tv12.setCompoundDrawablePadding(getResources().getDimensionPixelOffset(R.dimen.dp_3));
        tv12.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);*/

        //CreditSignUtils.showCreditRightSide(tv11, true);
        //CreditSignUtils.showCreditLeftSide(tv12, true);
        CreditSignUtils.showCreditRightSide(tv13, true);
        CreditSignUtils.showCreditLeftSide(tv14, true);
        CreditSignUtils.showCreditRightSide(tv15, true);
        CreditSignUtils.showCreditLeftSide(tv16, true);
    }

    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8, R.id.tv9, R.id.tv10, R.id.tv11, R.id.tv12})
    public void onViewClicked(View view) {
        int[] outLocation = new int[2];
        view.getLocationInWindow(outLocation);
        Log.i(TAG, "onViewClicked: x==" + outLocation[0] + "  y==" + outLocation[1]);
        switch (view.getId()) {
            case R.id.tv1:
                //屏幕左上角
                mCreditPW.showAtLocation(view, Gravity.NO_GRAVITY, 0, 0);
                break;
            case R.id.tv2:
                //屏幕右边下角
                mCreditPW.showAtLocation(view, Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                break;
            case R.id.tv3:
                //在指定view的左上角
                mCreditPW.showAtLocation(view, Gravity.NO_GRAVITY, outLocation[0], outLocation[1]);
                break;
            case R.id.tv4:
                //在指定view的左下角
                int y = outLocation[1] + view.getHeight();
                mCreditPW.showAtLocation(view, Gravity.NO_GRAVITY, outLocation[0], y);
                break;
            case R.id.tv5:
                //在指定view的偏移处
                int xOffset = outLocation[0] + view.getWidth();
                int yOffset = outLocation[1] - (int) UIUtils.dp2px(this, 30);
                mCreditPW.showAtLocation(view, Gravity.NO_GRAVITY, xOffset, yOffset);
                break;
            case R.id.tv6:
                int xOffset0 = outLocation[0] - (int) UIUtils.dp2px(this, 109);
                int yOffset0 = outLocation[1] - (int) UIUtils.dp2px(this, 30);
                mCreditPW.showAtLocation(view, Gravity.NO_GRAVITY, xOffset0, yOffset0);
                break;
            case R.id.tv7:
                //指定view下方弹框2
                mCreditPW.showAsDropDown(view);
                break;
            case R.id.tv8:
                //指定view下方的最后面弹框
                int xOffset8 = view.getWidth();
                int yOffset8 = 0;
                mCreditPW.showAsDropDown(view, xOffset8, yOffset8);
                break;
            case R.id.tv9:
                int xOffset9 = view.getWidth();
                int yOffset9 = -(3 * view.getHeight());
                mCreditPW.showAsDropDown(view, xOffset9, yOffset9);
                break;
            case R.id.tv10:
                int xOffset10 = -view.getWidth();
                int yOffset10 = -(3 * view.getHeight());
                mCreditPW.showAsDropDown(view, xOffset10, yOffset10);
                break;
            case R.id.tv11:
                //x坐标方向定位
                int xSpace = getResources().getDimensionPixelSize(R.dimen.dp_6);
                TextView textView = (TextView) view;
                int xPadding = textView.getCompoundDrawablePadding() * 2;
                int xOffset11 = view.getWidth() - xSpace - xPadding;

                //y坐标方向定位
                int popupHeith = getResources().getDimensionPixelOffset(R.dimen.dp_25);
                int ySpace = getResources().getDimensionPixelOffset(R.dimen.dp_5);
                int yOffset11 = -(view.getHeight() + popupHeith + ySpace);
                mCreditPW.showAsDropDown(view, xOffset11, yOffset11);
                break;
            case R.id.tv12:
                break;
        }
    }


   /* @OnClick({,R.id.tv11, R.id.tv12})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv11:
                break;
            case R.id.tv12:
                break;
        }
    }*/
}
