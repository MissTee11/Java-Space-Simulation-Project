package model;

/*Simülasyondaki bir kişiyi temsil eder.*/

public class Person {
	private String name;
	private int age;//yaş
	private int remainingLife;//kalan hayat
	private String spacecraftName;//uzay araci adi
	
	public Person(String name,int age, int remainingLife, String spacecraftName) {
		this.name = name;
		this.age=age;
		this.remainingLife = remainingLife;
		this.spacecraftName = spacecraftName;
	}
	public void decreaseLife() {//Kişinin kalan ömrünü bir birim azaltır.
		if(remainingLife>0) remainingLife--;
	}
	public boolean isDead() {//Kişinin ölü olup olmadığını kontrol eder.
		return remainingLife<=0;
	}
	public String getSpaceCraftName() {//Kişinin ait olduğu uzay aracının adını alır.
		return spacecraftName;
	}
	public int getRemainingLife() {//Kişinin kalan ömrünü alır.
		return remainingLife;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}
