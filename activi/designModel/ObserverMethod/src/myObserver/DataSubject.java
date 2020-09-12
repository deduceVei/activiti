package myObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author deduce
 */
public class DataSubject implements ISubject {

	List<IObserver> observersList;
	String name;
	Integer id;
	Date date;

	public DataSubject() {
		observersList = new ArrayList();
	}

	@Override
	public void registerObserver(IObserver observer) {
		observersList.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		observersList.remove(observer);
	}

	@Override
	public void notifyObserver() {
		for (IObserver observer : observersList) {
			observer.update(name, id, date);
		}
	}

	public void resultChange() {
		notifyObserver();
	}

	public void measurementsChange(String name, Integer id, Date date) {
		this.date = date;
		this.id = id;
		this.name = name;
		resultChange();
	}
}
