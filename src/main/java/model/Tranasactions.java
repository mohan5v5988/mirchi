package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import org.postgresql.util.PGobject;

import services.TranasactionsServices;

import com.fasterxml.jackson.databind.ObjectMapper;

import command.tranasactions.CreateTranasactionsCommand;
import command.tranasactions.DeleteTranasactionsByTidCommand;
import command.tranasactions.GetTranasactionsByDateCommand;
import command.tranasactions.GetTranasactionsByNidCommand;
import command.tranasactions.GetTranasactionsByNidandDateCommand;
import command.tranasactions.GetTranasactionsByTidCommand;
import command.tranasactions.UpdateTranasactionsCommand;

public class Tranasactions {
	private int tid;
	private String nid;
	private String type;
	private Date date;
	private String json;
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public String toString() {
		return "T ID : "+tid+" Nid : "+nid+" Type : "+type+" Date : "+date+" JSON : "+json ;
	}
	/*public static void main(String[] args) {
		ObjectMapper mapper = new ObjectMapper();
		UpdateTranasactionsCommand t = new UpdateTranasactionsCommand();
		ArrayList<Double> bkg = new ArrayList<Double>();
		bkg.add(45.0);
		bkg.add(36.5);
		Calculation aaa = new Calculation(2,5,6,bkg,150,5);
		String use="";
		
		Tranasactions o = new Tranasactions();
		o.setTid(10);
		o.setNid("mv");
		o.setType("No 5");
		o.setDate(java.sql.Date.valueOf("2015-02-27"));
		try {
			use = mapper.writeValueAsString(aaa);
			o.setJson(use);
			//System.out.println(mapper.writeValueAsString(o));
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(t.execute(o));
		//========================================================
//		ArrayList<Tranasactions> arr = t.execute(9);
//		if(arr.isEmpty()) System.out.println("aaaaaaaaa");
//		for(Tranasactions a : arr) {
//			System.out.println(a);
//		}
	}*/
}