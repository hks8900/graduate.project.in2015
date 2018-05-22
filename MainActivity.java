package com.example.nam;

import android.app.Activity;

import android.content.Intent; // ����Ʈ ��� ���� import�մϴ�.
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// �⺻ȭ�� ���
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // ��ư ó��
		Button mapButton = (Button) findViewById(R.id.map);
		Button settingButton = (Button) findViewById(R.id.setting);
		Button callButton = (Button) findViewById(R.id.callcenter);
		Button developButton = (Button) findViewById(R.id.developer);
		Button runButton = (Button) findViewById(R.id.run);
	
		// ����ȭ���� ��ư Click ó��
		
		// ������ư
		mapButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, map.class);			
				startActivity(intent); // �ι�° ��Ƽ��Ƽ�� �����մϴ�.
			}
		});
		
	
		// ���� ��ư
		settingButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, setting.class);
				startActivity(intent);
			}
		});
		
		// �Ű� ��ư
		callButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, call.class);
				startActivity(intent);
			}
		});
		
		// ������ ��ư
		developButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, develop.class);
				startActivity(intent);
			}
		});
		//�����ư
		runButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, run.class);
				startActivity(intent);
			}
		});

	}
}