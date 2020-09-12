package javaObserver;

import java.util.Date;
import java.util.Observable;

/**
 * @author deduce
 */
public class WatchData extends Observable {

	String name;
	Integer id;
	Date date;

	public WatchData() {
	}

	public void resultChange() {
		//notifyObservers前面设置此，才可以进行推送给观察者
		setChanged();
		//此处调用无参的，代表拉取从被观察者(主题)
		notifyObservers();
	}

	public void measurementsChange(String name, Integer id, Date date) {
		this.date = date;
		this.id = id;
		this.name = name;
		resultChange();
	}

	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}
}

