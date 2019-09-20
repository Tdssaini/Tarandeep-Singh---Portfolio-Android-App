package com.tarandeep.app.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.tarandeep.app.R;


public abstract class TDSProgressBar extends ProgressDialog{
	Context context=null;

	public TDSProgressBar(Context context) {
		super(context);
		this.context=context;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_dialog);
		setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		setCanceledOnTouchOutside(false);
	}
	int backPressCount=0;
	@Override
	public void onBackPressed() {
	}

	@Override
	public void dismiss() {
		super.dismiss();
		onDismiss();
	};

	public abstract void onDismiss();
}
