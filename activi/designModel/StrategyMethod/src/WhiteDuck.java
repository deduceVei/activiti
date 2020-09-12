/**
 * @author deduce
 */
public class WhiteDuck extends Duck{

	public WhiteDuck() {
		flyBehavior = new WingsFlyBehavior();
		soundBehavior = new DarkSoundBehavior();
	}

	@Override
	public void display() {
		System.out.println("WhiteDuck...");
	}

}
