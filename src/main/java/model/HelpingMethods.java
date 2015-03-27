package model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class HelpingMethods {
	public static ArrayList<Tranasactions> setCalObjToTran(ArrayList<Tranasactions> arr) {
		for(int m=0; m<arr.size(); ++m) {
			arr.get(m).setC(convertJSONtoCalObj(arr.get(m).getJson()));
		}
		return arr;
	}
	
	
	private static Calculation convertJSONtoCalObj(String json) {
		ArrayList<Double> d = new ArrayList<Double>();
		JSONObject myJSON = new JSONObject(json);
		JSONArray jsonArray = myJSON.getJSONArray("bagkg");
		if (jsonArray != null) { 
		   int len = jsonArray.length();
		   for (int m=0; m<len; m++){ 
			   d.add(jsonArray.getDouble(m));
		   } 
		} 
		Calculation c = new Calculation(myJSON.getInt("bags"),myJSON.getInt("coolie"),
										myJSON.getInt("railway"),d,
										myJSON.getDouble("rate"),myJSON.getDouble("vat"));
		return c;
	}
}
