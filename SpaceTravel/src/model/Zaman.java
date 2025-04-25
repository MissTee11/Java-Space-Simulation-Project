package model;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Zaman {
	private int hoursPerDay;
	private LocalDate currentDate;
	private int currentHour;
	
	public Zaman(int hoursPerDay, LocalDate currentDate) {
		this.hoursPerDay = hoursPerDay;
		this.currentDate= currentDate;
		this.currentHour=0;
	}
	
	public void advanceHour() {//counter for the days
		currentHour++;
		if(currentHour>= hoursPerDay) {
			currentHour=0;
			currentDate= currentDate.plusDays(1);
		}
	}
	public LocalDateTime getCurrentDateTime() {
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
