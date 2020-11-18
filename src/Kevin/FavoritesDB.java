package Kevin;

import java.io.Serializable;

public class FavoritesDB implements Serializable {
	
	//邏輯是根據會員ID取得收藏清單
	
	
	private static String[] titles;	 
	private static String[] authors;	 
	private static Float[] prices;
	
	
	public static void setTitles(String[] ptitles) {
		titles=ptitles;
	}
	
	public static void setAuthors(String[] pauthors) {
		authors=pauthors;
	}
	
	public static void setPrices(Float[] pprices) {
		prices=pprices;
	}
		 
	/** Return the number of books in the database */
	public static int size() {
	       return titles.length;
	}
		 
	/** Return the title of the given bookID */
	public static String getTitle(int bookID) {
	       return titles[bookID];
	}
		 
	/** Return the author of the given bookID */
	public static String getAuthor(int bookID) {
	      return authors[bookID];
    }
		 
	/** Return the price of the given bookID */
    public static float getPrice(int bookID) {
	      return prices[bookID].floatValue();
    }

}
