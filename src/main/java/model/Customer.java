package model;

public class Customer {
	private String nid;
	private String name;
	private String address;
	private long pnumber;
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPnumber() {
		return pnumber;
	}
	public void setPnumber(long pnumber) {
		this.pnumber = pnumber;
	}
	public String toString() {
		return "NID : "+nid+" Name : "+name+" Address : "+address+" Phon number : "+pnumber;
	}
}
