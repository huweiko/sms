package com.example.miansha;

import com.example.service.mainService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class RunOnStartupReceiver extends BroadcastReceiver { 
		AppContext appContext;
        @Override
        public void onReceive(Context context, Intent intent) {
        	appContext = (AppContext)context.getApplicationContext();
        	//UIHealper.DisplayToast(context, "RunOnSartupReceiver");
    			appContext.BackgroundServiceIntent = new Intent(appContext,mainService.class);
    			appContext.startService(appContext.BackgroundServiceIntent);
        }
}