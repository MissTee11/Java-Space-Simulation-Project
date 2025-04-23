package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UzayAraci {
	private String name;
	private String fromPlanet;//Gezegenden
	private String toPlanet;//Gezegene
	private LocalDate departureDate;
	private int distanceHours;
	private int hoursLeft;
	private String status;
	private List<Person> people;
	
	
	public UzayAraci(String name, String fromPlanet, LocalDate departureDate, int distanceHours) {
		this.name = name;
		this.fromPlanet=fromPlanet;
		this.toPlanet = toPlanet;
		this.departureDate = departureDate;
		this.distanceHours = distanceHours;
		this.hoursLeft = distanceHours;
		this.status = "Bekliyor";
		this.people = new ArrayList<>();
	}
	public void boardPerson(Person p) {
		people.add(p);
	}
	public void startJourney() {
		if(status.equals("Bekliyor")) {
			status= "Yolda";
		}
	}
	
}
