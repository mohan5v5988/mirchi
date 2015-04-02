package model;

import java.sql.Date;

public class Tranasactions {
	private int tid;
	private String nid;
	private String type;
	private String date;
	private String json;
	private Calculation c = null;
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Calculation getC() {
		return c;
	}

	public void setC(Calculation c) {
		this.c = c;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return "T ID : "+tid+" Nid : "+nid+" Type : "+type+" Date : "+date+" JSON : "+json ;
	}
	public static void main(String[] args) {
//		ObjectMapper mapper = new ObjectMapper();
//		ArrayList<Double> bkg = new ArrayList<Double>();
//		bkg.add(45.0);
//		bkg.add(36.5);
//		Calculation aaa = new Calculation(2,5,6,bkg,150,5);
//		String use="";
//		
//		Tranasactions o = new Tranasactions();
//		o.setTid(10);
//		o.setNid("mv");
//		o.setType("No 5");
//		o.setDate("2015-03-08");
//		try {
//			use = mapper.writeValueAsString(aaa);
//			o.setJson(use);
//			//System.out.println(mapper.writeValueAsString(o));
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		//==========================================================
//		CreateTranasactionsCommand t = new CreateTranasactionsCommand();
//		System.out.println(t.execute(o));
		//==========================================================
//		UpdateTranasactionsCommand t = new UpdateTranasactionsCommand();
//		System.out.println(t.execute(o));
		//========================================================
//		ArrayList<Tranasactions> arr = t.execute(9);
//		if(arr.isEmpty()) System.out.println("aaaaaaaaa");
//		for(Tranasactions a : arr) {
//			System.out.println(a);
//		}
		//=========================================================
//		GetTranasactionsBetweenDatesCommand b = new GetTranasactionsBetweenDatesCommand();
//		ArrayList<Tranasactions> arr = b.execute("1991-12-04", "2015-02-26");
//		for(Tranasactions a : arr) {
//			System.out.println(a);
//		}
	}
}
