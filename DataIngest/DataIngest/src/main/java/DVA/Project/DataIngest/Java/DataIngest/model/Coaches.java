package DVA.Project.DataIngest.Java.DataIngest.model;

public class Coaches {
	private Person person;

	private Position position;

	public void setPerson(Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Position getPosition() {
		return this.position;
	}
}
