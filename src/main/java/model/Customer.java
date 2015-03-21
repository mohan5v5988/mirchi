package model;


public class Customer {
	private String nid;
	private String name;
	private String address;
	private String email;
	private String date;
	private long pnumber;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
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
		return "NID : "+nid+" Name : "+name+" Address : "+address+
				" Phon number : "+pnumber+" Email : "+email+" Date of Birth : "+date;
	}
}
