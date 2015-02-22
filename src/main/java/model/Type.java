package model;

public class Type {
	private String type;
	private double rate;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String toString() {
		return "Type : "+type+" Rate : "+rate;
	}
}
