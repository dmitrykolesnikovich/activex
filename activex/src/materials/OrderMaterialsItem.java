package materials;

public class OrderMaterialsItem {
	private Nomenclature material;
	private int amount;
	private String comments;
	private boolean reserve;
	private int amountDays;

	public OrderMaterialsItem() {
	}

	public OrderMaterialsItem(Nomenclature material, int amount, String comments, boolean reserve, int amountDays) {
		this.material = material;
		this.amount = amount;
		this.comments = comments;
		this.reserve = reserve;
		this.amountDays = amountDays;
	}

	public Nomenclature getMaterial() {
		return material;
	}

	public void setMaterial(Nomenclature material) {
		this.material = material;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isReserve() {
		return reserve;
	}

	public void setReserve(boolean reserve) {
		this.reserve = reserve;
	}

	public int getAmountDays() {
		return amountDays;
	}

	public void setAmountDays(int amountDays) {
		this.amountDays = amountDays;
	}

}
