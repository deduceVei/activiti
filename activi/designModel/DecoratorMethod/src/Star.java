/**
 * @author deduce
 */
public class Star {
	public static void main(String[] args) {

		/**
		 * 本例后加工的输出在后面，因为他会先调用前一个加工的description再加上此次加工的描述
		 * 装饰者模式的思想：
		 *	分为装饰者类和真实对象，传入的是真实对象，输出的是装饰对象。 他俩都要继承同样的接口
		 *	传入真实对象后，这个真实对象的引用给到装饰对象中真实对象的引用，然后调用此次加工之前的一个加工，以此这样来实现被不断的加工装饰
		 *
		 *
		 * 1.装饰者类要实现真实类同样的接口
		 * 2.装饰者类内有一个真实对象的引用(可以通过装饰者类的构造器传入)
		 * 3.装饰类对象在主类中接受请求,将请求发送给真实的对象(相当于已经将引用传递到了装饰类的真实对象)
		 * 4.装饰者可以在传入真实对象后,增加一些附加功能(因为装饰对象和真实对象都有同样的方法,装饰对象可以添加一定操作再调用真实对象的方法,或者先调用真实对象的方法,再添加自己的方法)
		 * 5.不用继承
		 * */
		Beverage beverage = new Expresso();
		beverage = new Mock(beverage);
		beverage = new Sugar(beverage);

		System.out.println(beverage.cost());
		System.out.println(beverage.getDescription());

	}

}
