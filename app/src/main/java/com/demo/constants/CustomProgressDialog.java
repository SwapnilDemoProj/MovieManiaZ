package com.demo.constants;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.demo.R;

public class CustomProgressDialog {

     static ProgressDialog dialog;

    public static void showDialog(Context context, String message){
        dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setIndeterminate(true);
        dialog.setIndeterminateDrawable(context.getResources().getDrawable(R.drawable.circular_progress_dialog));
        dialog.setCancelable(false);
        dialog.show();

    }

    public static void dismissDialog(Context context){
       if(dialog.isShowing()) {
           dialog.dismiss();
       }

    }


    public static void showAlertDialogMessage(Context context,String title,String dialogContent)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(dialogContent);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

}
