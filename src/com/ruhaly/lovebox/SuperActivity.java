package com.ruhaly.lovebox;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public abstract class SuperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		initData();
		initLayout(savedInstanceState);
	}

	public abstract void initData();

	public abstract void initLayout(Bundle savedInstanceState);

	public void showToast(final String str) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), str, Toast.LENGTH_SHORT)
						.show();
			}
		});
	}
}
