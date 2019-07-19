package com.d_andaman;


import android.app.AlertDialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;


public class Message extends DialogFragment{
	
	@Override
	public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();

       
        builder.setView(inflater.inflate(R.layout.message, null));
    
               /*.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int id) {
                       // sign in the user ...
                   }
               })
               .setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       Dialog.this.getDialog().cancel();
                   }
               });*/
        return builder.create();
    }

	
}
