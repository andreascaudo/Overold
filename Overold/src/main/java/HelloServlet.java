

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String regioneSelezionata;
	private PrintWriter out;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HelloServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		out = response.getWriter();
		out.print("Welcome! \n");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		String item= request.getParameter("item");
		String region= request.getParameter("region");
		out = response.getWriter();
		out.print("Do you need ");
		out.print(item);
		out.print(" in ");
		out.print(region);
		out.print("\n");
		String search = "http://www.subito.it/annunci-"+ region + "/vendita/usato/?q=" + item;
		System.out.println(search);
		search = search.replaceAll(" ","+");
		out.print(executePost(search));
	}

	public static String executePost(String targetURL) {
		URL subito;
		StringBuffer sbb = new StringBuffer();
		try {
			subito = new URL(targetURL);

			URLConnection sb = subito.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							sb.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null){
				sbb.append(inputLine);
				sbb.append('\r');
			}
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sbb.toString();
	}

}
