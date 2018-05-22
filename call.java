package com.example.nam;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class call extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calling);

		Button bt1 = (Button) findViewById(R.id.call1);
		Button bt2 = (Button) findViewById(R.id.call2);
	

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
	
 
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {

		case R.id.call1:

			startActivity(new Intent("android.intent.action.CALL",
					Uri.parse("tel:112")));
			break;

		case R.id.call2:

			startActivity(new Intent("android.intent.action.CALL",
					Uri.parse("tel:119")));

			break;
	

		}

	}
}