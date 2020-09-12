package javaObserver;

import java.util.Date;
import java.util.Observer;

public class startAPI {
	public static void main(String[] args) {
		WatchData watchData = new WatchData();
		watchData.measurementsChange("name", 12, new Date());
		//构造注册一个观察者到可观察者(主题)
		Observer adisplay = new Adisplay(watchData);

		//update
		//第一个参数可以从传入的可观察者(主题)拉取数据)
		// 第二个参数可以给观察者传参
		adisplay.update(watchData, null);


	}
}
