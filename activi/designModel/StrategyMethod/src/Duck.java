/**
 * @author deduce
 */
public abstract class Duck {
	/**
	 * 此类接口参数用来动态的设置不同的表现
	 */
	IFlyBehavior flyBehavior;
	ISoundBehavior soundBehavior;

	/**
	 * 此类方法用于公共继承
	 */
	public abstract void display();

	void performFly() {
		flyBehavior.fly();
	}

	void performSound() {
		soundBehavior.sound();
	}


	public IFlyBehavior getFlyBehavior() {
		return flyBehavior;
	}

	public void setFlyBehavior(IFlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}

	public ISoundBehavior getSoundBehavior() {
		return soundBehavior;
	}

	public void setSoundBehavior(ISoundBehavior soundBehavior) {
		this.soundBehavior = soundBehavior;
	}


	public static void main(String[] args) {
		Duck duck = new WhiteDuck();
		duck.performFly();
		duck.performSound();
		System.out.println("=====↓ setter ↓=====");
		duck.setFlyBehavior(new NoFlyBehavior());
		duck.setSoundBehavior(new NoSoundBehavior());
		duck.performFly();
		duck.performSound();
	}
}
