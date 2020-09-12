/**
 * @author deduce
 * 装饰者类
 */
public class Sugar extends CondementDecorator {

	Beverage beverage;

	public Sugar(Beverage beverage) {
		super();
		this.beverage = beverage;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + "-sugar";
	}

	@Override
	public double cost() {
		return 2.00 + beverage.cost();
	}
}
