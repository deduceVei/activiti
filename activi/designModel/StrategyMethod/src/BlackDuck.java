

/**
 * @author deduce
 */
public class BlackDuck extends Duck {

	/**
	 * 继承了父类Duck的flyBehavior、soundBehavior
	 * 因此可作为无参构造方法来定义默认行为
	 * 在Duck父类中有setter方法可以重置如下行为
	 */
	public BlackDuck() {
		flyBehavior = new NoFlyBehavior();
		soundBehavior = new NoSoundBehavior();
	}

	@Override
	public void display() {
		System.out.println("WhiteDuck...");
	}
}
