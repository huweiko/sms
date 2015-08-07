package com.example.service;



import com.example.miansha.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.util.Log;

public class mainService extends Service{
	private SoundPool soundPool;
  public class RefreshThread  extends Thread{
    	
    	@Override
		public void run(){
    		while(true)
        	{
    			Log.i("huwei", "播放声音");
    			if(soundPool == null){
    				soundPool= new SoundPool(1,AudioManager.STREAM_MUSIC,5);
        			soundPool.load(getApplicationContext(),R.raw.dida,1);
        			try {
        				Thread.sleep(1000);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			soundPool.play(1,1, 1, 0, -1, 1);
    			}
    			try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
    		
    	}
    }
    
    RefreshThread mRefreshThread = null;
    

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void onCreate(){
		super.onCreate();
	
	}

	public int onStartCommand(Intent intent , int flags , int startId){
		
		if(mRefreshThread == null){
    		mRefreshThread = new RefreshThread();
    		try {
    			mRefreshThread.start(); 
			} catch (IllegalThreadStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}

		
		return Service.START_STICKY;
	}
	public void onDestroy(){
		super.onDestroy();
		if(mRefreshThread != null){
    		try {
				mRefreshThread.join(15);
				mRefreshThread = null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		if(soundPool != null){
			soundPool.release();
			soundPool = null;
		}
	}
	

	/**
	 * 检测是否有活动网络
	 */
	public static boolean contactNet(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);// 获取系统的连接服务
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();// 获取网络的连接情况
		if (activeNetInfo != null && activeNetInfo.isConnected()
				&& activeNetInfo.isAvailable()) {
			return true;
		} else {
			return false;
		}
	}
}
