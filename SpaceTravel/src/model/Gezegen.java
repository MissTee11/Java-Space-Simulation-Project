package model;
import java.util.ArrayList;
import java.util.List;

public class Gezegen {
	private String name;
	private Zaman zaman;
	private List<Person>population;
	
	public Gezegen(String name, Zaman zaman) {
		this.name=name;
		this.zaman=zaman;
		this.population = new ArrayList<>();
	}
	
	public void addPerson(Person p) {
		population.add(p);
	}
	public void removePerson(Person p) {
		population.remove(p);
	}
	public void updateZaman() {
		zaman.advanceHour();
	}
	public String getName() {
		return name;
	}
	public Zaman getZaman() {
		return zaman;
	}
	public int getPopulationSize() {
		return population.size();
	}
	

}
