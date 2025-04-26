package core;
import model.*;


import util.FileReaderUtil;

import java.util.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

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
						.ifPresent(v->{
							v.boardPerson(p);
							
							gezegenler.stream()
							.filter(pl -> pl.getName().equals(v.getFromPlanet()))
	                        .findFirst()
	                        .ifPresent(pl -> pl.addPerson(p));
		});
	  }
	}
		
	public void run() {
		while(uzayAracilar.stream().anyMatch(v-> !v.isComplete())) {
			clearConsole();
			updateSimulation();
			printStatus();
			System.out.flush();
			sleep(100);
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
					.findFirst()
					.orElse(null);
			
			if(uzayaraci.getStatus().equals("Bekliyor")&&
			departureGezegen != null&&
			uzayaraci.getDepartureDate().toLocalDate().equals(departureGezegen.getZaman().getCurrentDateTime().toLocalDate())) {
				uzayaraci.startJourney();
			
			
			for(Person p:uzayaraci.getPeople()) {
				departureGezegen.removePerson(p);
		    	}
			}
			
	        uzayaraci.advanceHour(departureGezegen.getZaman()); 
			
	        if (uzayaraci.getStatus().equals("Yolda")) {
			for(Person p: uzayaraci.getPeople()) {
				p.decreaseLife();
			  }
				uzayaraci.checkDestruction();
	        }
			
			
			if(uzayaraci.getStatus().equals("Vardi")) {
				Gezegen destinationGezegen = gezegenler.stream()
						.filter(p -> p.getName().equals(uzayaraci.getToPlanet()))
		                .findFirst()
		                .orElse(null);
			
			if(destinationGezegen !=null) {
				List<Person> arrivedPeople = new ArrayList<>(uzayaraci.getPeople());
				for(Person p: arrivedPeople) {
					if(!p.isDead()) {
						destinationGezegen.addPerson(p);
					  }
				    }
				uzayaraci.getPeople().clear();
			     }
			   }
			}
	}
	private void printStatus() {
		
		System.out.println("Gezegenler: ");
		System.out.printf("%-15s", "");
		
		for(Gezegen g: gezegenler) {
			System.out.printf("%-20s", "--- " + g.getName() + " ---");
		}
		 System.out.println();
		 
		System.out.printf("%-15s", "Tarih");
		for (Gezegen g : gezegenler) {
			 String date = g.getZaman().getCurrentDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("d.MM.yyyy"));
		     System.out.printf("%-20s", date);
		}
		 System.out.println();
		 
		System.out.printf("%-15s", "Nüfus");
		for (Gezegen g : gezegenler) {
		  System.out.printf("%-20d\t", g.getPopulationSize());
		}
		 System.out.println("\n");
		 
		
		System.out.println("Uzay Araclari: ");
		System.out.printf("%-15s %-15s %-10s %-10s %-20s %s\n", "Arac Adi", "Durum", "Cikis", "Varis", "Hedefe Kalan Saat", "Hedefe Varis Tarihi");
		 
		for(UzayAraci v: uzayAracilar) {
			
			String kalanSaat= v.getHoursLeft() >=0? String.valueOf(v.getHoursLeft()): "--";
			String arrivalDate = "--";
			
			if (v.getStatus().equals("Vardi")) {
				Gezegen destinationPlanet = gezegenler.stream()
	                    .filter(pl -> pl.getName().equals(v.getToPlanet()))
	                    .findFirst()
	                    .orElse(null);
	            if (destinationPlanet != null) {
	                arrivalDate = v.calculateArrivalDate(destinationPlanet.getZaman()).toLocalDate().toString();
	            }
	        }
			 System.out.printf("%-15s %-15s %-10s %-10s %-20s %s\n", v.getName(), v.getStatus(), v.getFromPlanet(), v.getToPlanet(), kalanSaat, arrivalDate);
		}	
	}
	
	private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
	
	private void clearConsole() {
		try {
	        if (System.getProperty("os.name").contains("Windows")) {
	            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	        } else {
	            System.out.print("\033[H\033[2J");
	            System.out.flush();
	        }
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main(String[] args) throws IOException{
		Simulation sim= new Simulation("data/Gezegenler.txt","data/UzayAracilar.txt", "data/Persons.txt");
		sim.run();
	}
	
}
