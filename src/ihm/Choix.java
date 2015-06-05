package ihm;

public class Choix {
	public ActionType action;
	public Position position;
	
	public Choix(ActionType action, Position position) {
		super();
		this.action = action;
		this.position = position;
	}

	public ActionType getAction() {
		return action;
	}

	public void setAction(ActionType action) {
		this.action = action;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	

}
