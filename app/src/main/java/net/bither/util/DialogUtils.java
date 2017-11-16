package net.bither.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ProgressBar;

/**
 * Created by wangzheng on 2017/11/15.
 */

public class DialogUtils {




    public static AlertDialog getProgressDialog(Activity activity,String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setCancelable(false)
                .setView(new ProgressBar(activity))
                .create();

        return alertDialog;
    }

    public static AlertDialog getConfirmTask(Context activity, DialogInterface.OnClickListener clickListener, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("取消",clickListener)
                .create();

        return alertDialog;
    }
}
