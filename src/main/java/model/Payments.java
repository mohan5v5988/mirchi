package model;

import java.sql.Date;

public class Payments {
	private int id;
	private String nid;
	private Double amount;
	private Date date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNid() {
		return nid;
	}
	public void setNid(String nid) {
		this.nid = nid;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	// to store the negative in db.
	public Double dbAmount() {
		return this.amount*-1;
	}
	// convert from negative to p.
	public void changeToPAmount() {
		Double a = this.amount;
		this.amount = Math.abs(a);
	}
//	public static void main(String[] args) {
//		Payments p = new Payments();
//		p.setAmount(3423237.0);
//		System.out.println(p.dbAmount());
//		p.changeToPAmount();
//		System.out.println(p.getAmount());
//	}
}
