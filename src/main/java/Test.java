
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Calculation;
import model.Tranasactions;
import command.tranasactions.GetTranasactionsByNidCommand;
import command.tranasactions.GetTranasactionsByNidandDateCommand;
import command.tranasactions.GetTranasactionsByTidCommand;

public class Test {
	public static void main(String[] args) {
		
		
		GetTranasactionsByNidandDateCommand command = new GetTranasactionsByNidandDateCommand();
		ArrayList<Tranasactions> a = command.execute("mv", "2015-02-27");
		
		GetTranasactionsByNidCommand c = new GetTranasactionsByNidCommand();
		setCalObjToTran(c.execute("mv"));
//		System.out.println(convertJSONtoCalObj(a.get(0).getJson()));
//		ArrayList<String> s = convertTarraytoString(a);
//		for(String q : s) System.out.println(q);
 	}
	
	static void setCalObjToTran(ArrayList<Tranasactions> arr) {
		for(int m=0; m<arr.size(); ++m) {
			arr.get(m).setC(convertJSONtoCalObj(arr.get(m).getJson()));
		}
		for(Tranasactions t : arr) {
			System.out.println(t.getC());
			System.out.println();
		}
	}
	
	
	static Calculation convertJSONtoCalObj(String json) {
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
	
	
	
	
	
	
	
	
	
	
//============================================================================================================
	static ArrayList<String> convertTarraytoString(ArrayList<Tranasactions> arr) {
		ArrayList<String> s = new ArrayList<String>();
		StringBuilder s1 = new StringBuilder();
		for(int m=0;m<(arr.size());m++) {
//			s1.append(convertTobjecttoString(arr.get(m))+",");
			s.add(convertTobjecttoString(arr.get(m)));
		}
//		s1.append(convertTobjecttoString(arr.get((arr.size()-1))));
		//System.out.println(s1);
		return s;
	}
	
	
	static String convertTobjecttoString(Tranasactions t) {
		ObjectMapper mapper = new ObjectMapper();
		try {
//			System.out.println(mapper.writeValueAsString(t));
			JSONObject obj1 = new JSONObject(mapper.writeValueAsString(t));
			obj1.remove("json");
			JSONObject obj = new JSONObject(t.getJson());
			return obj1.toString().split("}")[0]+","+obj.toString().substring(1);
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
}
