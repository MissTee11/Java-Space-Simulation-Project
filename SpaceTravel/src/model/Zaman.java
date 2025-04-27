package model;
import java.time.LocalDate;
import java.time.LocalDateTime;

/*Bir gezegenin zaman sistemini temsil eder.*/

public class Zaman {
	private int hoursPerDay;//Bu gezegende bir gündeki saat sayısı
	private LocalDate currentDate;//Güncel tarih
	private int currentHour;//Gün içindeki geçerli saat
	
	public Zaman(int hoursPerDay, LocalDate currentDate) {
		this.hoursPerDay = hoursPerDay;
		this.currentDate= currentDate;
		this.currentHour=0;
	}
	
	public void advanceHour() {//Zamanı bir saat ileri alır.
		currentHour++;
		if(currentHour>= hoursPerDay) {//Eğer saat hoursPerDay'e ulaşırsa veya onu aşarsa yeni bir gün başlar.
			currentHour=0;
			currentDate= currentDate.plusDays(1);
		}
	}
	public LocalDateTime getCurrentDateTime() {//Mevcut tam tarih ve saati alır
		
		 if (currentHour == 24) {
		        currentHour = 0;
		        currentDate = currentDate.plusDays(1);
		    }
		 
		return currentDate.atTime(currentHour, 0);
	}
	
	public int getCurrentHour() {
		return currentHour;
	}
	public String getFormattedTime() {
		return String.format("%02d:00", currentHour);
	}
	public int getHoursPerDay() {
		return hoursPerDay;
	}

}
