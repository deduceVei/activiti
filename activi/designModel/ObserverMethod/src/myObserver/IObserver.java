package myObserver;

import java.util.Date;

/**
 * @author deduce
 */
public interface IObserver {
	void update(String name, Integer id, Date date);
	void removeDisplay();
}
