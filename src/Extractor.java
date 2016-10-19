import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public abstract class Extractor {

	public Extractor() {
		// TODO Auto-generated constructor stub
	}
	/* declaramos estract como metodo abstracto, el ? nos permite que pueda ser de cualquier tipo,
	 * por eso le metemos el extends para expecificar un poco mas y que sean cosas pero q hereden de EXTRACTOR */
	public abstract ArrayList<? extends Extractor> extract(String xml);
	
	public static String readUrl(String urlRoute) {
		BufferedReader in = null;
		String inputLine, inputText = "";
		try {
			URL url = new URL(urlRoute);

			// abrimos canal de comunicaion
			in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF8"));

			while ((inputLine = in.readLine()) != null) {
				inputText = inputText + inputLine;
			}
		} catch (IOException t) {
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inputText;
	}
}
