package model;

public class Person {
	private String name;
	private int remainingLife;//kalan hayat
	private String spacecraftName;//uzay araci adi
	
	public Person(String name,int remainingLife, String spacecraftName) {
		this.name = name;
		this.remainingLife = remainingLife;
		this.spacecraftName = spacecraftName;
	}
	public void decreaseLife() {
		if(remainingLife>0) remainingLife--;
	}
	public boolean isDead() {
		return remainingLife<=0;
	}
	public String getSpaceCraftName() {
		return spacecraftName;
	}
	public int getRemainingLife() {
		return remainingLife;
	}
	public String getName() {
		return name;
	}
}
