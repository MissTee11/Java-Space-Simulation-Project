package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*Gezegenler arasında seyahat eden bir uzay aracını temsil eder.*/

public class UzayAraci {
	private String name;
	private String fromPlanet;//Uzay aracının kalktığı gezegenin adı
	private String toPlanet;//Uzay aracının seyahat ettiği gezegenin adı
	private LocalDateTime departureDate;//Planlanan hareket tarihi ve saati
	private int hoursLeft;//Varışa kalan saatler
	private String status;//Mevcut durum
	private List<Person> people;//Uzay gemisindeki kişilerin listesi
	
	
	public UzayAraci(String name, String fromPlanet, String toPlanet, LocalDateTime departureDate, int hoursLeft) {
		this.name = name;
		this.fromPlanet=fromPlanet;
		this.toPlanet = toPlanet;
		this.departureDate = departureDate;
		this.hoursLeft = hoursLeft;
		this.status = "Bekliyor";
		this.people = new ArrayList<>();
	}
	public void boardPerson(Person p) {//Bir kişiyi uzay aracına bindirir.
		people.add(p);
	}
	public void startJourney() {//Uzay aracı şu anda bekliyorsa yolculuğu başlatır.
		if(status.equals("Bekliyor")) {
			status= "Yolda";
		}
	}
	public void advanceHour(Zaman zaman) {//Eğer bugün hareket tarihi ise yolculuğa başlayın
		 if (status.equals("Bekliyor") && departureDate.toLocalDate().equals(zaman.getCurrentDateTime().toLocalDate())) {
		        startJourney(); 
		    }
		 
		if(status.equals("Yolda")&& hoursLeft>0) {//ve eğer zaten seyahat ediyorsanız, kalan saatleri azaltın
			hoursLeft--;
			if(hoursLeft<=0) {
				status="Vardi";
				hoursLeft=0;
			}
		}
	}
	
	public void checkDestruction() {//Gemideki tüm insanların hayatını kaybettiğini kontrol eder.
		boolean allDead= people.stream().allMatch(Person::isDead);
		if(allDead) {
			status="IMHA";
		}
	}
	public boolean isComplete() {//Yolculuğun tamamlanıp tamamlanmadığını kontrol eder
		return status.equals("Vardi")|| status.equals("IMHA");
	}
	
	public LocalDateTime calculateArrivalDate(Zaman destinationPlanetZaman) {//Hedef gezegenin zaman sistemine göre beklenen varış tarihini hesaplar.
	    Zaman tempZaman = new Zaman(destinationPlanetZaman.getHoursPerDay(), departureDate.toLocalDate());
	    for (int i = 0; i < hoursLeft; i++) {
	        tempZaman.advanceHour();
	    }
	    return tempZaman.getCurrentDateTime();
	}

	public String getStatus() {
		return status;
	}
	public LocalDateTime getDepartureDate() {
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
