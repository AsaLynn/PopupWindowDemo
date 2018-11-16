package com.zxn.popup;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zxn on 2018/11/15.
 */
public class CreditSignUtils {

    /**
     * 获取展示信誉认真的Drawable.
     *
     * @param view
     * @return
     */
    private static Drawable getCreditDrawable(TextView view) {
        Drawable drawable
                = view
                .getResources()
                .getDrawable(R.drawable.ic_auth);
        view.setCompoundDrawablePadding(view.getResources().getDimensionPixelOffset(R.dimen.dp_3));
        return drawable;
    }

    /**
     * TextView中左侧展示信誉标志.
     *
     * @param view
     * @param isCredited true已经认证,否则反之.
     */
    private static void showCreditLeftSide(TextView view, boolean isCredited) {
        Drawable drawable = isCredited ? getCreditDrawable(view) : null;
        view.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        if (isCredited) {
            CreditPW instance = CreditPW.getInstance(view.getContext());
            view.setOnClickListener(instance);
        }
    }

    /**
     * TextView中右侧展示信誉标志.
     *
     * @param view
     * @param isCredited true已经认证,否则反之.
     */
    private static void showCreditRightSide(TextView view, boolean isCredited) {
        Drawable drawable = isCredited ? getCreditDrawable(view) : null;
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        if (isCredited) {
            CreditPW instance = CreditPW.getInstance(view.getContext());
            //instance.setSide(false);
            view.setOnClickListener(instance);
        }
    }

    /**
     * TextView中展示信誉标志.
     *
     * @param view
     * @param isCredited true已经认证,否则反之.
     * @param isLeftSide true表示TextView左侧,反之在右侧.
     */
    public static void showCredit(TextView view, boolean isCredited, boolean isLeftSide) {
        if (isLeftSide) {
            showCreditLeftSide(view, isCredited);
        } else {
            showCreditRightSide(view, isCredited);
        }
    }

    public static void showCredit(View view, boolean isCredited) {
        //view.setImageResource(R.drawable.ic_auth);
        view.setVisibility(isCredited ? View.VISIBLE : View.GONE);

        if (isCredited) {
            view.setOnClickListener(CreditPW.getInstance(view.getContext()));
        }
    }
}
