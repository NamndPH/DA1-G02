package com.zrapp.warehouse;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast extends Toast {
    public static final int LONG = 4000;
    public static final int SHORT = 2500;
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int WARNING = 2;

    public CustomToast(Context context) {
        super(context);
    }

    public static Toast makeText(Context context, String text, int duration, int type) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);
        View layout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null, false);
        TextView tv = layout.findViewById(R.id.tvToast);
        tv.setText(text);
        switch (type) {
            case SUCCESS:
                tv.setTextColor(Color.GREEN);
                break;
            case ERROR:
                tv.setTextColor(Color.RED);
                break;
            case WARNING:
                tv.setTextColor(Color.YELLOW);
                tv.setHighlightColor(Color.BLUE);
                break;
        }
        toast.setView(layout);
        return toast;
    }
}
