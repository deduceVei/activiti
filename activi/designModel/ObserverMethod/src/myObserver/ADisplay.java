package myObserver;

import java.util.Date;

/**
 * @author deduce
 */
public class ADisplay implements IObserver {
	DataSubject dataSubject;
	String name;
	Integer id;
	Date date;

	/**
	 * 构造的时候进行注册
	 */
	public ADisplay(DataSubject dataSubject) {
		this.dataSubject = dataSubject;
		dataSubject.registerObserver(this);
	}

	@Override
	public void removeDisplay() {
		dataSubject.removeObserver(this);
	}

	@Override
	public void update(String name, Integer id, Date date) {
		this.name = name;
		this.id = id;
		this.date = date;
		System.out.println("-A--" + name + id + date);
	}
}
