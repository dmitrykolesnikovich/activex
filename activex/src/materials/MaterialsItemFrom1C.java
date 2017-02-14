package materials;

public class MaterialsItemFrom1C {
	private Nomenclature material;
	private Stock stock;
	private int balance;
	private boolean isImport;
	private String comments;
	
	public MaterialsItemFrom1C() {
	
	}

	public MaterialsItemFrom1C(Nomenclature material, Stock stock, int balance, boolean isImport, String comments) {
		this.material = material;
		this.stock = stock;
		this.balance = balance;
		this.isImport = isImport;
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Nomenclature getMaterial() {
		return material;
	}
	public void setMaterial(Nomenclature material) {
		this.material = material;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public boolean isImport() {
		return isImport;
	}
	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

	@Override
	public String toString() {
		return "Item [material=" + material + ", stock=" + stock + ", balance=" + balance + ", isImport=" + isImport
				+ "]";
	}

}
