package newLearnings_2025;

public class Students {

	private String name;
	private String city;

	public Students() {
		this.name = "Default Name";
		this.city = "Default City";
	}

	public Students(String name, String city) {
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
