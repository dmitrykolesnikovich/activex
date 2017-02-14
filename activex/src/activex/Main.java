package activex;

import java.util.ArrayList;
import java.util.List;
import materials.MaterialsUtil;
import materials.MaterialsItemFrom1C;
import materials.Nomenclature;
import materials.OrderDocument;
import materials.OrderMaterialsItem;

public class Main {

	public static void main(String[] args) {
		get1c();
		send1c();
	}

	private static void get1c() {
		List<MaterialsItemFrom1C> list = MaterialsUtil.getMaterialsFrom1C();
		for (MaterialsItemFrom1C item : list) {
			System.out.println(item);
		}
	}

	private static void send1c() {
		Nomenclature material1 = new Nomenclature("000000004", "Цемент 500");
		Nomenclature material2 = new Nomenclature("000000003", "Цемент 400");

		List<OrderMaterialsItem> materials = new ArrayList<>();
		materials.add(new OrderMaterialsItem(material1, 340, "Комментарий 1", false, 0));
		materials.add(new OrderMaterialsItem(material2, 560, "Комментарий 2", true, 6));

		OrderDocument orderDocument = new OrderDocument("000000001", "000000002", materials);
		MaterialsUtil.sendOrder(orderDocument);
	}
}
