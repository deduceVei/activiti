package myObserver;

import java.util.Date;

/**
 * @author deduce
 */
public class Start {

	public static void main(String[] args) {
		DataSubject dataSubject = new DataSubject();

		System.out.println("ADisplay注册");
		IObserver aDisplay = new ADisplay(dataSubject);
		dataSubject.measurementsChange("第一次Display", 19, new Date());


		System.out.println("-------");
		System.out.println("BDisplay注册");
		IObserver bDisplay = new BDisplay(dataSubject);
		dataSubject.measurementsChange("第二次Display", 18, new Date());


		dataSubject.removeObserver(aDisplay);
		System.out.println("-------");
		System.out.println("ADisplay被动移除，继续发布");
		dataSubject.measurementsChange("移除后Display", 17, new Date());


		System.out.println("-------");
		System.out.println("CDisplay注册，ADisplay重新回归注册");
		IObserver cDisplay = new CDisplay(dataSubject);
		dataSubject.registerObserver(aDisplay);
		dataSubject.measurementsChange("第三次Display", 16, new Date());


		System.out.println("-------");
		System.out.println("CDisplay主动移除");
		cDisplay.removeDisplay();
		dataSubject.measurementsChange("C主动移除后Display", 16, new Date());
	}
}
