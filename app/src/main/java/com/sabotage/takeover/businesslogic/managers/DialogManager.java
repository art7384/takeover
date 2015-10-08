package com.sabotage.takeover.businesslogic.managers;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Кармишин on 05.08.2015.
 */
public class DialogManager {

    public void showDialogHelp(Context cnx, String mssg){
        AlertDialog.Builder adBulder = new AlertDialog.Builder(cnx);
        adBulder.setMessage(mssg);
        adBulder.setNegativeButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        adBulder.show();
    }

}
