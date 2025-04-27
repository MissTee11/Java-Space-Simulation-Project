package util;

import model.*;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*Dosyadan gezegenleri, uzay araçlarını ve kişileri okumayı sağlar.*/

public class FileReaderUtil {
	private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("d.MM.yyyy");
	
	public static List<Gezegen> readGezegenler(String path) throws IOException{//Bir dosyadan gezegenleri okur ve Gezegen nesnelerinin bir listesini oluşturur.
		List<Gezegen> gezegenler = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
			String[] parts = line.split("#");
			String name = parts[0];
			int hours = Integer.parseInt(parts[1]);
			LocalDate date =LocalDate.parse(parts[2], formatter);
			gezegenler.add(new Gezegen(name, new Zaman(hours, date)));
		}
	  }
		return gezegenler;
	}
	public static List<UzayAraci> readUzayAracilar(String path) throws IOException{//Bir dosyadan uzay araçlarını okur ve UzayAraci nesnelerinin bir listesini oluşturur.
		List<UzayAraci> uzayAracilar = new ArrayList<>();
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
			String[] parts = line.split("#");
			String name = parts[0];
			String from= parts[1];
			String to= parts[2];
			LocalDate date =LocalDate.parse(parts[3], formatter);
			
			LocalDateTime dateTime = date.atStartOfDay();
			
			int dist = Integer.parseInt(parts[4]);
			uzayAracilar.add(new UzayAraci(name, from, to, dateTime, dist));
		}
	}
		return uzayAracilar;
	}
	 public static List<Person> readPersons(String path) throws IOException {//Bir dosyadan kişileri okur ve Kişi nesnelerinin bir listesini oluşturur.
	        List<Person> people = new ArrayList<>();
	        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            String[] parts = line.split("#");
	            String name = parts[0];
	            int age = Integer.parseInt(parts[1]);
	            int life = Integer.parseInt(parts[2]);
	            String vehicle = parts[3];
	            people.add(new Person(name, age ,life, vehicle));
	        }
	       }
	        return people;
	    }
	
}
