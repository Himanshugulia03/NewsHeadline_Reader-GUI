
import org.json.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

class NewsApiRequest {
	private static final String api_key = "502bbb486edc4cf7af96474c0917f85f";
	private static final String api_url = "https://newsapi.org/v2/top-headlines?country=";

	public static String getdata(String country) {
		String fapi_url = api_url + country + "&apiKey=" + api_key;

		String inputline;
		StringBuilder content = new StringBuilder();
		try {
			URL url1 = new URL(fapi_url);
			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			while ((inputline = in.readLine()) != null) {
				content.append(inputline);
			}
			in.close();
			connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content.toString();
	}

}

   class JsonNewsdata{
	public static String parsedata(String response){
		StringBuilder result = new StringBuilder();
		JSONObject jsono = new JSONObject(response);
		JSONArray articles = jsono.getJSONArray("articles");

		for(int i=0; i<15; i++){
			JSONObject article = articles.getJSONObject(i);
			String title = article.getString("title");
			String url = article.getString("url");
			System.out.println(i+1 +". "+title);
			System.out.println("read more: "+ url);
			result.append("   "+i+1+".  "+title).append("\n\n");
		}
		return result.toString();
	}
}

public class Main {
	public static void main(String[] args){
		String country ;
		Scanner abc = new Scanner(System.in);
		System.out.println("enter country code(e.g: in for India, us for USA)");
		country = abc.next();

		String response = NewsApiRequest.getdata(country);
		JsonNewsdata.parsedata(response);
	}
}
