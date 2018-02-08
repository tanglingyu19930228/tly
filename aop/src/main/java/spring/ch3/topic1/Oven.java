package spring.ch3.topic1;

public class Oven {
	private String name="";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Oven [name=" + name + "]";
	}
}
