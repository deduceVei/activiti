/**
 * @author deduce
 */
public class Mock extends CondementDecorator {
	/**
	 * 装饰者类中的真实引用
	 */
	Beverage beverage;


	public Mock(Beverage beverage) {
		super();
		this.beverage = beverage;
	}

	/**
	 * 此处是得到上一层的description
	 */
	@Override
	public String getDescription() {
		return beverage.getDescription() + "-mock";
	}

	@Override
	public double cost() {
		return beverage.cost() + 1.00;
	}
}
