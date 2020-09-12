package javaObserver;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Adisplay implements Observer {
	Observable observable;
	String name;
	Integer id;
	Date date;

	public Adisplay(Observable observable) {
		this.observable = observable;
		observable.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof WatchData) {
			WatchData watchData = (WatchData) o;
			this.name = watchData.getName();
			this.id = watchData.getId();
			this.date = watchData.getDate();
			display();
		}
	}

	public void display() {
		System.out.println(name + id + date);
	}
}
