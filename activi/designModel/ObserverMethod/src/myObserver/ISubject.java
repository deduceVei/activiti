package myObserver;

/**
 * @author deduce
 */
public interface ISubject {
	void registerObserver(IObserver observer);

	void removeObserver(IObserver observer);

	void notifyObserver();
}
