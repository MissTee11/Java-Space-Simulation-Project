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
	
	
	public UzayAraci(String name, String fromPlanet, String toPlanet, LocalDate departureDate, int distanceHours) {
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
	public void advanceHour() {
		if(status.equals("Yolda")) {
			hoursLeft--;
			if(hoursLeft<=0) {
				status="Vardi";
				hoursLeft=0;
			}
		}
	}
	public void checkDestruction() {
		boolean allDead= people.stream().allMatch(Person::isDead);
		if(allDead) {
			status="IMHA";
		}
	}
	public boolean isComplete() {
		return status.equals("Vardi")|| status.equals("IMHA");
	}
	public String getStatus() {
		return status;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public int getHoursLeft() {
		return status.equals("IMHA")? -1: hoursLeft;
	}
	public String getFromPlanet() {
		return fromPlanet;
	}
	public String getName() {
		return name;
	}
	public String getToPlanet(){
		return toPlanet;
	}
	public List<Person> getPeople(){
		return people;
	}
	
}
