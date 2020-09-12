

/**
 * @author deduce
 * 真实对象类和装饰者类需要共同实现的接口
 */
public abstract class Beverage {
	String description = "Unknow beverage";

	public String getDescription() {
		return description;
	}

	public abstract double cost();

}
