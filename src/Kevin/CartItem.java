package Kevin;

public class CartItem {
	   private int bookID;
	   private int qtyOrdered;
	 
	   public CartItem(int bookID, int qtyOrdered) {
	      this.bookID = bookID;
	      this.qtyOrdered = qtyOrdered;
	   }
	 
	   public int getBookID() {
	      return bookID;
	   }
	 
	   public void setBookID(int bookID) {
	      this.bookID = bookID;
	   }
	 
	   public int getQtyOrdered() {
	      return qtyOrdered;
	   }
	 
	   public void setQtyOrdered(int qtyOrdered) {
	      this.qtyOrdered = qtyOrdered;
	   }
	 
	   // Returns the author based on this bookID
	   public String getAuthor() {
	      return BookDB.getAuthor(bookID);
	   }
	 
	   // Returns the author based on this bookID
	   public String getTitle() {
	      return BookDB.getTitle(bookID);
	   }
	 
	   // Return the price based on this bookID
	   public float getPrice() {
	      return BookDB.getPrice(bookID);
	   }

}
