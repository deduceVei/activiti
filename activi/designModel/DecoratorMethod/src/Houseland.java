/**
 * @author deduce
 * 真实对象类
 */
public class Houseland extends Beverage {

	public Houseland() {
		description = "Houseland";
	}

	@Override
	public double cost() {
		return 0.99;
	}
}