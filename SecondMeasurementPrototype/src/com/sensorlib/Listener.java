package com.sensorlib;

import java.util.List;

import android.net.wifi.ScanResult;

interface Listener {
	public void onScanResult(List<ScanResult> results);
}