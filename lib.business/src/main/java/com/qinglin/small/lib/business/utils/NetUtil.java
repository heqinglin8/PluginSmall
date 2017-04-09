package com.qinglin.small.lib.business.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import net.wequick.small.Small;

public class NetUtil {
	/**
	 * 检查网络状态
	 */
	public static boolean CheckNetState() {
		ConnectivityManager cManager = (ConnectivityManager) Small.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cManager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 获取连接网络的对象
	 * 
	 * @return
	 */
	private static NetworkInfo getNetworkInfo() {
		ConnectivityManager connectMgr = (ConnectivityManager) Small
				.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		return info;
	}

	/**
	 * 获取连接网络的对象
	 * 
	 * @return
	 */
	private static NetworkInfo getNetworkInfo(Context mContext) {
		ConnectivityManager connectMgr = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
//		NetworkInfo info = connectMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo info = connectMgr.getActiveNetworkInfo();
		return info;
	}

	/**
	 * 判断是否是WIFI
	 * 
	 * @return
	 */
	public static boolean isMobileNet(Context mContext) {
		boolean flag = false;
		NetworkInfo info = getNetworkInfo(mContext);
		// 非wifi网络
		if (info != null) {
			flag = info.isConnected();
		}
		return flag;
	}

	/**
	 * 判断是否是手机网络
	 * 
	 * @return
	 */
	public static boolean isMobile() {
		boolean flag = false;
		NetworkInfo info = getNetworkInfo();
		// 非wifi网络
		if (info != null) {
			if (!info.getTypeName().equals("WIFI")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 判断是否是WIFI
	 * 
	 * @return
	 */
	public static boolean isWifi() {
		boolean flag = false;
		NetworkInfo info = getNetworkInfo();
		// 非wifi网络
		if (info != null) {
			if (info.getTypeName().equals("WIFI")) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * @author xujun 判断是否白名单过滤
	 * @param url
	 * @return true过滤 false不过滤
	 * */
	public static boolean isWhitelistFiltering(String url) {
		/*
		boolean isFiltering = false;
		// 判断反劫持
		List<String> whiteList = AHBaseApplication.getInstance().getWhitleList();
		int size = whiteList.size();
		String hosts = StringUtil.getHostName(url);
		LogUtil.d("JIMMY", "hosts : " + hosts + "  url : " + url);
		for (int i = 0; i < size; i++) {
			String whiteStr = whiteList.get(i);
			if (hosts.contains(whiteStr)) {
				// 存在与白名单不过滤
				isFiltering = true;
				break;
			}
		}
		return isFiltering;
	*/
		return false;
	}
	
	public final static String NETWORK_TYPE_UNKNOWN = "NETWORK_TYPE_UNKNOWN";
	public final static String NETWORK_TYPE_UNCONNECTED = "NETWORK_TYPE_UNCONNECTED";
	public final static String NETWORK_TYPE_2G = "NETWORK_TYPE_2G";
	public final static String NETWORK_TYPE_3G = "NETWORK_TYPE_3G";
	public final static String NETWORK_TYPE_4G = "NETWORK_TYPE_4G";
	public final static String NETWORK_TYPE_WIFI = "NETWORK_TYPE_WIFI";
	
	/**
	 * 获取当前网络类型
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetworkType(Context context) {
		if (context == null) {
			return NETWORK_TYPE_UNKNOWN;
		}

		NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo == null) {
            return NETWORK_TYPE_UNCONNECTED;
        }

        if (!networkInfo.isAvailable() || !networkInfo.isConnected()) {
            return NETWORK_TYPE_UNCONNECTED;
        }

        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_TYPE_WIFI;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            switch (networkInfo.getSubtype()) {
	            case TelephonyManager.NETWORK_TYPE_CDMA: // 14-64 kbps
	            case TelephonyManager.NETWORK_TYPE_IDEN: // 25 kbps
	            case TelephonyManager.NETWORK_TYPE_1xRTT: // 50-100 kbps
	            case TelephonyManager.NETWORK_TYPE_EDGE: // 50-100 kbps
	            case TelephonyManager.NETWORK_TYPE_GPRS: // 100 kbps
	                return NETWORK_TYPE_2G;
	                
	            case TelephonyManager.NETWORK_TYPE_EVDO_0: // 400-1000 kbps
	            case TelephonyManager.NETWORK_TYPE_UMTS: // 400-7000 kbps
	            case TelephonyManager.NETWORK_TYPE_EVDO_A: // 600-1400 kbps
	            case TelephonyManager.NETWORK_TYPE_HSPA: // 700-1700 kbps
	            case TelephonyManager.NETWORK_TYPE_HSUPA: // 1-23 Mbps
	            case TelephonyManager.NETWORK_TYPE_HSDPA: // 2-14 Mbps
	            case 15: // 对应TelephonyManager.NETWORK_TYPE_HSPAP: 在API Level 13以下没有此值，但存在此网络类型
	                return NETWORK_TYPE_3G;
	                
	            case 13: // 对应TelephonyManager.NETWORK_TYPE_LTE
	                return NETWORK_TYPE_4G;
	                
	            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
	            default:
	                return NETWORK_TYPE_UNKNOWN;
            }
        }
		return NETWORK_TYPE_UNKNOWN;
	}
	
}
