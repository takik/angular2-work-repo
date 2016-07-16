package heroes.ws.model;

public class Hero {

	private int id;
	private String name;

	public Hero(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Hero() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
}