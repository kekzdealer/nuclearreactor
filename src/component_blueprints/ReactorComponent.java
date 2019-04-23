package component_blueprints;

/**
 * A component that sits in a reactor.
 * 
 * Depending on placement in the 6x9 reactor grid components can have a differing amount of available sides.
 * All values are evenly distributed to all available sides (2 - 4) <br>
 * <br>
 * Durability for fuel rods is reduced by one each reactor tick. <br>
 * Durability for heat management components is reduced by one for every 60 heat. <br>
 * Durability for neutron reflectors is reduced by one/two/four for each single/dual/quad cell adjacent to it. <br>
 * 
 * @author Kekzdealer
 *
 */
public abstract class ReactorComponent {
	
	private final int posX;
	private final int posY;
	
	protected ReactorComponent(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	protected abstract void destroy();
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof ReactorComponent) {
			final ReactorComponent rc = (ReactorComponent) o;
			return ((this.posX == rc.getX()) && (this.posY == rc.getY())) ? true : false;
		} else {
			return false;
		}
	}
	
	public int getX() {
		return posX;
	}
	
	public int getY() {
		return posY;
	}

}
