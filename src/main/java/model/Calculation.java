package model;

import java.util.ArrayList;

public class Calculation 
{
	int bags;
	ArrayList<Double> bagkg = new ArrayList<Double>();
	ArrayList<Double> amount = new ArrayList<Double>();
	double rate,vatp,sum=0.0,kolagaram,per,totam,vat;
	int gunnie,revaze,coolie,railway;
	String output;
    public Calculation(int b,int c,int ra,ArrayList<Double> bk,double r,double v)
    {
    	bags = b;
    	coolie = c;
    	railway = ra;
    	bagkg = bk;
    	rate = r;
    	per = 1.0;
    	vat = v;
   // This for will generate the amount array. 
    	for(int m=0;m<bagkg.size();m++) { amount.add(((bagkg.get(m)-1)*rate)); }
    // To calculate sum.
    	for (Double d : amount) { sum += d; }
    	gunnie=20*bags;
        revaze=5*bags;
        sum=sum+gunnie+revaze;
        sum=twoDecimal(sum);
        sum=Math.round(sum);
        kolagaram=Math.round(precentage(per));
        sum=sum+kolagaram;
        totam=sum+vat+coolie+railway;
    }
    double precentage(double p)
    {
        return ((p/100)*sum);
    }
    double twoDecimal(double d)
    {
    	return ((int) Math.round(d*100))/100.0;
    }
    
    
    public int getBags() {
		return bags;
	}
	public void setBags(int bags) {
		this.bags = bags;
	}
	public ArrayList<Double> getBagkg() {
		return bagkg;
	}
	public void setBagkg(ArrayList<Double> bagkg) {
		this.bagkg = bagkg;
	}
	public ArrayList<Double> getAmount() {
		return amount;
	}
	public void setAmount(ArrayList<Double> amount) {
		this.amount = amount;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getVatp() {
		return vatp;
	}
	public void setVatp(double vatp) {
		this.vatp = vatp;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
	public double getKolagaram() {
		return kolagaram;
	}
	public void setKolagaram(double kolagaram) {
		this.kolagaram = kolagaram;
	}
	public double getPer() {
		return per;
	}
	public void setPer(double per) {
		this.per = per;
	}
	public double getTotam() {
		return totam;
	}
	public void setTotam(double totam) {
		this.totam = totam;
	}
	public double getVat() {
		return vat;
	}
	public void setVat(double vat) {
		this.vat = vat;
	}
	public int getGunnie() {
		return gunnie;
	}
	public void setGunnie(int gunnie) {
		this.gunnie = gunnie;
	}
	public int getRevaze() {
		return revaze;
	}
	public void setRevaze(int revaze) {
		this.revaze = revaze;
	}
	public int getCoolie() {
		return coolie;
	}
	public void setCoolie(int coolie) {
		this.coolie = coolie;
	}
	public int getRailway() {
		return railway;
	}
	public void setRailway(int railway) {
		this.railway = railway;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	
	public String toString() {
		return "";
	}
	String getOutput()
    {
		output = "B.No"+"   "+"Kg  "+"       "+"Rate  "+"   "+"Totle Amount  "+"\n";
		for(int m=0;m<bags;m++)
	    {
	   		output += (m+1)+"        "+bagkg.get(m)+"       "+rate+"     "+amount.get(m)+"\n";
	   	}
    	return output;
    }
}
