package model;
import java.util.ArrayList;
import java.util.List;

/*Simülasyonda bir gezegeni temsil ediyor.*/

public class Gezegen {
	private String name;
	private Zaman zaman;
	private List<Person>population;
	
	public Gezegen(String name, Zaman zaman) {//kurucu
		this.name=name;
		this.zaman=zaman;
		this.population = new ArrayList<>();
	}
	
	public void addPerson(Person p) {//Gezegenin nüfusuna bir kişi ekler.
		population.add(p);
	}
	public void removePerson(Person p) {//Bir kişiyi gezegenin nüfusundan çıkarır.
		population.remove(p);
	}
	public void updateZaman() {//Gezegenin saatini bir saat ileri alır.
		zaman.advanceHour();
	}
	public String getName() {//Gezegenin adını alır.
		return name;
	}
	public Zaman getZaman() {//Gezegenin zamanını alır.
		return zaman;
	}
	public int getPopulationSize() {//Gezegenin mevcut nüfus büyüklüğünü alır.
		return population.size();
	}
	

}
