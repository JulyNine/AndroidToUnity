package com.xinpianchang.fakemiss;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends UnityPlayerActivity {

    private static Context instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = getApplicationContext();
        //CreateToast("默认的初始化");
    }

    public static Context getContext()
    {
        return instance;
    }



    public void CreateToast(final String toast)
    {
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                //onCoderReturn("CreateToast()");
                Toast.makeText(
                        MainActivity.this,
                        toast,Toast.LENGTH_LONG).show();
            }
        });
    }







    //获取wifi信号强度
    //wifiinfo.getRssi()；获取RSSI，RSSI就是接受信号强度指示。
    //这里得到信号强度就靠wifiinfo.getRssi()这个方法。
//得到的值是一个0到-100的区间值，是一个int型数据，其中0到-50表示信号最好，
    //-50到-70表示信号偏差，小于-70表示最差，
    //有可能连接不上或者掉线，一般Wifi已断则值为-200。
    @SuppressWarnings("deprecation")
    public String ObtainWifiInfo() {
        // Wifi的连接速度及信号强度：
        String result="";
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        if (info.getBSSID() != null) {
            // 链接信号强度
            int strength = WifiManager.calculateSignalLevel(info.getRssi(), 5);
            // 链接速度
            int speed = info.getLinkSpeed();
            // 链接速度单位
            String units = WifiInfo.LINK_SPEED_UNITS;
            // Wifi源名称
            String ssid = info.getSSID();
            int ip = info.getIpAddress();
            String mac = info.getMacAddress();
            result = strength+"|"+mac+"|"+ssid;
        }
        UnityPlayer.UnitySendMessage("SDKManager", "OnWifiDataReturn", result);
        //   UnityCallAndroidToast(result);
        return result;
    }














}
