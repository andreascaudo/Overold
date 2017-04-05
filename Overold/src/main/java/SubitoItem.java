import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubitoItem {
	private String title;
	private String link;
	
	private String imgPreview;
	private int nPhotos;
	
	private float price;
	private Calendar date;
	private String city;
	private String province;
	
	
	public SubitoItem(String draft){
		title = takeTitle(draft);
		link = takeLink(draft);
		
		imgPreview = takeImgPreview(draft);
		nPhotos = takenPhotos(draft);
		
		price = takePrice(draft);
		try {
			date = new GregorianCalendar();
			date.setTime(takeDate(draft));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		city = takeCity(draft);
		province = takeProvince(draft);
		
		
	}
	
	public String takeTitle(String draft){
		String title;
		Matcher matcher = Pattern.compile("title=\"(.*?)\"").matcher(draft);
		while (matcher.find()) {
		    this.title = matcher.group();
		}
		this.title = this.title.substring(7, this.title.length()-1);
		return this.title;
	}
	
	public String takeLink(String draft){
		String link;
		Matcher matcher = Pattern.compile("<a href=(.*?)>").matcher(draft);
		while (matcher.find()) {
		    this.link = matcher.group();
		}
		matcher = Pattern.compile("http(.*?)htm").matcher(this.link);
		while (matcher.find()) {
		    this.link = matcher.group();
		}
		return this.link;
	}
	
	public String takeImgPreview(String draft){
		String imgPreview;
		Matcher matcher = Pattern.compile("http://s.sbito.it/b(.*?)\"").matcher(draft);
		while (matcher.find()) {
		    this.imgPreview = matcher.group();
		}
		
		if(this.imgPreview != null)
		this.imgPreview = this.imgPreview.substring(0, this.imgPreview.length()-1);
		
		return this.imgPreview;
	}
	
	public int takenPhotos(String draft){
		String tempnPhotos = null;
		Matcher matcher = Pattern.compile("o_number\">(.*?)<").matcher(draft);
		while (matcher.find()) {
		    tempnPhotos = matcher.group();
		}
		
		if(tempnPhotos != null){
			tempnPhotos = tempnPhotos.substring(10, tempnPhotos.length()-1);
			return Integer.parseInt(tempnPhotos);
		}
		
		if(imgPreview != null){
			return 1;
		}
		
		return 0;
		
	}
	
	public float takePrice(String draft){
		String tempPrice = null;
		Matcher matcher = Pattern.compile("_price\">(.*?) &").matcher(draft);
		while (matcher.find()) {
			tempPrice = matcher.group();
		}
		
		if(tempPrice != null){
			tempPrice = tempPrice.substring(8, tempPrice.length()-2);
			return Float.parseFloat(tempPrice);
		}
		
		return 0;
		
	}
	
	public Date takeDate(String draft) throws ParseException{
		String tempDate = null;
		Matcher matcher = Pattern.compile("time=\"(.*?)\"").matcher(draft);
		while (matcher.find()) {
			tempDate = matcher.group();
		}
		tempDate = tempDate.replace("-", "/");
		tempDate = tempDate.substring(8, tempDate.length()-1);
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date startDate = df.parse(tempDate);
		return startDate;
		
	}
	
	public String takeCity(String draft){
		String city;
		Matcher matcher = Pattern.compile("tion\">(.*?)<").matcher(draft);
		while (matcher.find()) {
		    this.city = matcher.group();
		}
		
		if(this.city != null)
		this.city = this.city.substring(6, this.city.length()-1);
		
		return this.city;
	}
	
	public String takeProvince(String draft){
		String province;
		Matcher matcher = Pattern.compile("city\">(.*?)<").matcher(draft);
		while (matcher.find()) {
		    this.province = matcher.group();
		}
		
		if(this.province != null)
		this.province = this.province.substring(6, this.province.length()-1);
		
		return this.province;
	}
	
	public String getTitle(){
		return title;
	}
	public String getLink(){
		return link;
	}
	public String getImgPreview(){
		return imgPreview;
	}
	public int getNPhotos(){
		return nPhotos;
	}
	public float getPrice(){
		return price;
	}
	public Calendar getDate(){
		return date;
	}
	public String getCity(){
		return city;
	}
	public String getProvice(){
		return province;
	}
	public String toString(){
		String temp = "\n" + "Titolo: " + title + "\n" + 
					"Link: " +  "<a href=\"" + link + "\">" + "\n" + 
					"Immagine: " + "<img src=1\"" + imgPreview + " alt=\"subito.it\">" + "\n" 
					+ "Numero di foto: " + nPhotos + "\n" + 
					"Prezzo: " + price + " euro\n" + 
					"Data: " + date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH) + 1) + "/" + date.get(Calendar.YEAR) + "  " + date.HOUR_OF_DAY + ":" + date.MINUTE + ":" + date.SECOND + "\n" +
					"Citta': " + city + "\n" +
					"Provincia: " + province + "\n\n";
		
		return temp;
		
	}
}
