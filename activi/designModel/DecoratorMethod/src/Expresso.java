/**
 * @author deduce
 * 真实对象
 */
public class Expresso extends Beverage{

	public Expresso() {
		description = "Expresso";
	}

	@Override
	public double cost() {
		return 1.99;
	}
}
