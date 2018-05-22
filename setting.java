package com.example.nam;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class setting extends Activity {
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_ting);
		
		Button regButton = (Button) findViewById(R.id.register);
		Button check1Button = (Button) findViewById(R.id.CheckBox1);
		Button check2Button = (Button) findViewById(R.id.CheckBox2);
		
		SharedPreferences pref = getSharedPreferences("pref",
				Activity.MODE_PRIVATE);
		EditText edit1 = (EditText) this.findViewById(R.id.text1);
		CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox1);
		CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox2);
		
		String text = pref.getString("editText", ""); // ����� �� �ҷ�����
		Boolean chk1 = pref.getBoolean("check1", false);
		Boolean chk2 = pref.getBoolean("check2", true);
		edit1.setText(text);
		check1.setChecked(chk1);
		check2.setChecked(chk2);
		

		regButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				EditText edit1 = (EditText) findViewById(R.id.text1);
				CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox1);
				CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox2);
				editor.putString("editText", edit1.getText().toString());
				editor.putBoolean("check1", check1.isChecked());
				editor.putBoolean("check2", check2.isChecked());
				editor.commit();
				Toast.makeText(getBaseContext(), "��ϵǾ����ϴ�", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		
		check1Button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox1);
				CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox2);
				if(check1.isChecked())
				check2.setChecked(false);
				else
					check1.setChecked(true);
				
				
			}
		});
		check2Button.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox1);
				CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox2);
				if(check2.isChecked())
				check1.setChecked(false);
				else
					check2.setChecked(true);
				//stopService(new Intent("android.term"));
			}
		});
		
	}

	public void onStop() { // ���ø����̼��� ȭ�鿡�� �������
		super.onStop();
		SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE); // UI ���¸� �����մϴ�.
		SharedPreferences.Editor editor = pref.edit(); // Editor�� �ҷ��ɴϴ�.
		EditText edit1 = (EditText) findViewById(R.id.text1);
		CheckBox check1 = (CheckBox)findViewById(R.id.CheckBox1);
		CheckBox check2 = (CheckBox)findViewById(R.id.CheckBox2);
		editor.putString("editText", edit1.getText().toString()); // edit text��
		editor.putBoolean("check1", check1.isChecked());
		editor.putBoolean("check2", check2.isChecked());
																	// ������
		editor.commit(); // �����մϴ�.
	}
}
