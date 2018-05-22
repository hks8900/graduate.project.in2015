package com.example.nam;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class run extends Activity implements SensorEventListener,LocationListener 
{
	private double sLatitude;
	private double sLongitude;
	LocationListener sLocationListener = null;
	Location location;
	LocationManager manager;
	LocationManager managerNET;
	String locationProvider;
	String provider;
	private long lastTime;
	private float lastX;
	private float lastY;
	private float lastZ;
	private float x, y, z ;
	private static final int SHAKE_THRESHOLD = 1500;// 테스트를 위해 00 뺌
	private static final int DATA_X = SensorManager.DATA_X;
	private static final int DATA_Y = SensorManager.DATA_Y;
	private static final int DATA_Z = SensorManager.DATA_Z;
	private static boolean chek;
	public String text;
	private SensorManager sensorManager;
	private Sensor accelerormeterSensor;
	private float shake;
	private float SpeedMHK;
	private float SpeedMK = 10;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.safe);
		SharedPreferences pref = getSharedPreferences("pref",Activity.MODE_PRIVATE);
		EditText edit1 = (EditText) this.findViewById(R.id.text1);
		CheckBox check1 = (CheckBox) findViewById(R.id.CheckBox1);
		text = pref.getString("editText", ""); // 저장된 값 불러오기
		chek = pref.getBoolean("check1", false);
		if (text.isEmpty()) {
			Toast.makeText(getBaseContext(), "전화번호가 등록되어있지않습니다",
					Toast.LENGTH_SHORT).show();
			finish();
		}
		if (!(chek)) {
			Toast.makeText(getBaseContext(), "설정되어있지않습니다", Toast.LENGTH_SHORT)
					.show();
			finish();
		}
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		String context = Context.LOCATION_SERVICE;
		if (manager == null)
		{
			manager = (LocationManager) getSystemService(context);//
		}
		if (sLocationListener == null)
		{
			sLocationListener = new run();
		}
		Criteria criteria = new Criteria();
	    criteria.setAccuracy(Criteria.ACCURACY_COARSE);// 정확도
	    criteria.setPowerRequirement(Criteria.POWER_LOW); // 전원 소비량
	    criteria.setAltitudeRequired(true); // 고도 사용여부
	    criteria.setBearingRequired(true); //
	    criteria.setSpeedRequired(true); // 속도
	    criteria.setCostAllowed(true); // 금전적비용
	    provider = manager.getBestProvider(criteria,true);
	    location = manager.getLastKnownLocation(provider);
	   manager.requestLocationUpdates(provider, 0, 0, sLocationListener);
	   double latitude = location.getLatitude(); // 위도
	   		sLatitude = latitude;
       double longitude = location.getLongitude(); // 경도
       		sLongitude = longitude;
       float Speed = location.getSpeed();
       		SpeedMHK = Speed*3600;

	} //oncreate 
	@Override
	public void onStart() {
		super.onStart();
		if (accelerormeterSensor != null)
			sensorManager.registerListener(this, accelerormeterSensor,
					SensorManager.SENSOR_DELAY_GAME);
	}
	@Override
	public void onStop() {
		super.onStop();
		if (sensorManager != null)
			sensorManager.unregisterListener(this);
		finish();
	}
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
	public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                y = event.values[SensorManager.DATA_Z];
                shake = Math.abs(x + y + z - lastX - lastY -lastZ  ) / gabOfTime * 10000;
                // 이벤트 발생!!
        Geocoder gcK = new Geocoder(getApplicationContext(), Locale.KOREA);
            if(SpeedMHK > 30) {
				if (shake> SHAKE_THRESHOLD){
					try {
						List<Address> addresses = gcK.getFromLocation(sLatitude,
								sLongitude, 1);
						StringBuilder sb = new StringBuilder();
						if (addresses.size() > 0) {
							for (Address addr : addresses) {
								sb.append(addr.getMaxAddressLineIndex())
										.insert(0, "-살려주세요-\n");
								for (int i = 0; i < addr
										.getMaxAddressLineIndex(); i++)
									sb.append(addr.getAddressLine(i)).append(
											"<< \n\n");
							}
							Address address = addresses.get(0);
							sb.delete(7, 8);
							sb.append(address.getCountryName()).append("\n");//국가
							sb.append(address.getAdminArea() + "\n"); //도 
							//sb.append("SubLocality:null" + address.getSubLocality() + "\n");  // 여기는 null
							//sb.append(address.getPostalCode()).append("\n");
							//sb.append("경상북도").append(" "); // 가라코드
							//sb.append("현재 경위도").append("latitude ").append("longitude ");
							//sb.append("세부도로:" + address.getSubThoroughfare() + "\n"); // 여기는 null
							sb.append(address.getLocality()).append(" "); // 시
							sb.append(address.getThoroughfare()).append(" "); //동
							sb.append(address.getFeatureName()).append("\n"); //번지
							sb.append(address.getLatitude()).append("위도 ");//위도
							sb.append(address.getLongitude()).append("경도 ");//경도
							// sb.append(tStr).append("\n");
							// sb.append(tStrLocal).append("\n");
							Toast.makeText(getBaseContext(), sb.toString(),Toast.LENGTH_SHORT).show();
							SmsManager sms = SmsManager.getDefault();
							sms.sendTextMessage(text, null, sb.toString()
									+ "에서 사고발생. 긴급구조를 요청합니다. ", null, null);
							setContentView(R.layout.not_safe);
							finish();
													}

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
            }
				lastX = event.values[DATA_X];

				lastY = event.values[DATA_Y];

				lastY = event.values[DATA_Z];
	}
	}
	}
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}
	
		}

	

