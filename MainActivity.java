package com.example.nam;

import android.app.Activity;

import android.content.Intent; // 인텐트 사용 위해 import합니다.
import android.os.Bundle;

import android.view.View;

import android.widget.Button;

public class MainActivity extends Activity {

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// 기본화면 출력
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // 버튼 처리
		Button mapButton = (Button) findViewById(R.id.map);
		Button settingButton = (Button) findViewById(R.id.setting);
		Button callButton = (Button) findViewById(R.id.callcenter);
		Button developButton = (Button) findViewById(R.id.developer);
		Button runButton = (Button) findViewById(R.id.run);
	
		// 메인화면의 버튼 Click 처리
		
		// 지도버튼
		mapButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, map.class);			
				startActivity(intent); // 두번째 액티비티를 실행합니다.
			}
		});
		
	
		// 설정 버튼
		settingButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, setting.class);
				startActivity(intent);
			}
		});
		
		// 신고 버튼
		callButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, call.class);
				startActivity(intent);
			}
		});
		
		// 제작자 버튼
		developButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, develop.class);
				startActivity(intent);
			}
		});
		//실행버튼
		runButton.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, run.class);
				startActivity(intent);
			}
		});

	}
}