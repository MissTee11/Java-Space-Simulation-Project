package core;
import model.*;
import util.FileReaderUtil;

import java.util.*;
import java.io.IOException;

public class Simulation {
	private List<Gezegen> gezegenler;
	private List<UzayAraci> uzayAracilar;
	private List<Person> people;
	
	public Simulation(String gezegenDosya, String araciDosya, String personDosya) throws IOException{
		gezegenler= FileReaderUtil.readGezegenler(gezegenDosya);
		uzayAracilar= FileReaderUtil.readUzayAracilar(araciDosya);
		people = FileReaderUtil.readPersons(personDosya);
		
		for(Person p :people) {
			uzayAracilar.stream()
						.filter(v ->v.getName().equals(p.getSpaceCraftName()))
						.findFirst()
						.ifPresent(v->v.boardPerson(p));
		}
	}
	public void run() {
		while(uzayAracilar.stream().anyMatch(v-> !v.isComplete())) {
			clearConsole();
			updateSimulation();
			printStatus();
		}
		System.out.println("Simulation tamamlanmis");
	}
	private void updateSimulation() {
		for(Gezegen gezegen: gezegenler) {
			gezegen.updateZaman();
		}
		for(UzayAraci uzayaraci: uzayAracilar) {
			Gezegen departureGezegen = gezegenler.stream()
					.filter(p->p.getName().equals(uzayaraci.getFromPlanet()))
					.findFirst().orElse(null);
			if(uzayaraci.getStatus().equals("Bekliyor")&&
			departureGezegen != null&&
			uzayaraci.getDepartureDate().equals(departureGezegen.getZaman().getCurrentDate())) {
				uzayaraci.startJourney();
			}
			uzayaraci.advanceHour();
			uzayaraci.checkDestruction();
			
			for(Person p: uzayaraci.getPeople()) {
				p.decreaseLife();
			}
		}
	}
	private void printStatus() {
		System.out.println("Gezegenler: ");
		for(Gezegen g: gezegenler) {
			System.out.printf("--- %s ---\nTarih: %s\nNüfus: %d\n\n", g.getName(), g.getZaman().getCurrentDate(), g.getPopulationSize());
		}
		System.out.println("Uzay Araclari: ");
		for(UzayAraci v: uzayAracilar) {
			String kalanSaat= v.getHoursLeft() >=0? String.valueOf(v.getHoursLeft()): "--";
			String arrival = (v.getStatus().equals("Vardi"))?"Hedefe Kalan Saat": "--";
			 System.out.printf("%-5s %-10s %-6s %-6s %-18s %s\n", v.getName(), v.getStatus(), v.getFromPlanet(), v.getToPlanet(), kalanSaat, arrival);
		}	
	}
	private void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}
	
	public static void main(String[] args) throws IOException{
		Simulation sim= new Simulation("Gezegenler.txt","UzayAracilar.txt", "Persons.txt");
		sim.run();
	}
	
}
