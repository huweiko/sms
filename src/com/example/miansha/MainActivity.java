package com.example.miansha;

import com.example.service.mainService;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;

import com.example.miansha.R;
public class MainActivity extends Activity {
	mainService mMainService;
	AppContext appContext;
	private SoundPool soundPool;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		appContext = (AppContext) getApplication();
		setContentView(R.layout.activity_main);
		appContext.BackgroundServiceIntent = new Intent(appContext,mainService.class);
		appContext.startService(appContext.BackgroundServiceIntent);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(soundPool != null){
			soundPool.stop(1);
			soundPool = null;
		}
	}

}
