package materials;

import java.util.List;

public class OrderDocument {
	private String ConstructedObjectCode;
	private String StockCode;
	private List<OrderMaterialsItem> materials;
	public OrderDocument() {
	}
	public OrderDocument(String constructedObjectCode, String stockCode, List<OrderMaterialsItem> materials) {
		ConstructedObjectCode = constructedObjectCode;
		StockCode = stockCode;
		this.materials = materials;
	}
	public String getConstructedObjectCode() {
		return ConstructedObjectCode;
	}
	public void setConstructedObjectCode(String constructedObjectCode) {
		ConstructedObjectCode = constructedObjectCode;
	}
	public String getStockCode() {
		return StockCode;
	}
	public void setStockCode(String stockCode) {
		StockCode = stockCode;
	}
	public List<OrderMaterialsItem> getMaterials() {
		return materials;
	}
	public void setMaterials(List<OrderMaterialsItem> materials) {
		this.materials = materials;
	}
	

}
