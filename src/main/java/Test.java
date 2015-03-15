
import java.util.ArrayList;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Tranasactions;
import command.tranasactions.GetTranasactionsByNidandDateCommand;
import command.tranasactions.GetTranasactionsByTidCommand;

public class Test {
	int a;
	public static void main(String[] args) {
		
		
		GetTranasactionsByNidandDateCommand command = new GetTranasactionsByNidandDateCommand();
		ArrayList<Tranasactions> a = command.execute("mv", "1991-12-04");
		ArrayList<String> s = convertTarraytoString(a);
		for(String q : s) System.out.println(q);
 	}
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
