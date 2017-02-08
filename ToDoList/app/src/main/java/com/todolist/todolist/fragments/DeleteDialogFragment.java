package com.todolist.todolist.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.todolist.todolist.R;

/**
 * DeleteDialogFragment class is used to make user confirm the delation of a task
 * Created by darrac_s on 26/01/2017.
 */
public class DeleteDialogFragment extends DialogFragment {

    private boolean delete = false;

    /**
     * onCreateDialog method is overidded from DialogFragment
     * This methos is called when the dialog is created.
     * @param savedInstanceState Bundle saved instance.
     * @return The concerned Dialog
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete_dialog)
                .setTitle(R.string.delete_dialog_title)
                .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        delete = true;
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        delete = false;
                        dismiss();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    /**
     * @return the should delete boolean
     */
    public boolean getDelete() { return delete; }

    /**
     * Called when the dialog is dismissed.
     * @param dialog concerned dialog interface.
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

}
