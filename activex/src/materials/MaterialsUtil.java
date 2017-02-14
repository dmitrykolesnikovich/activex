package materials;

import java.util.ArrayList;
import java.util.List;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class MaterialsUtil {

	public static List<MaterialsItemFrom1C> getMaterialsFrom1C() {
		List<MaterialsItemFrom1C> materialList = new ArrayList<>();
		Variant connected = getConnection();

		StringBuilder sb = new StringBuilder("�������");
		sb.append("	�������������(����������������������������������.�����) ��� stock,")
				.append(" �������������(����������������������������������.��������) ��� material,")
				.append(" ����������������������������������.���������� ��� amount,")
				.append(" ����������������������������������.������ ��� isImport,")
				.append(" ����������������������������������.�����.��� ��� stockCode,")
				.append(" ����������������������������������.��������.��� ��� materialCode").append(" ��")
				.append(" ���������������.���������������������.������������� ��� ����������������������������������")
				.append(" ���������� ���").append(" �������")
				.append(" �������������(������������������������������.�����),")
				.append(" �������������(������������������������������.��������),")
				.append(" ������������������������������.����������,").append(" ������������������������������.������,")
				.append(" ������������������������������.�����.���,")
				.append(" ������������������������������.��������.���").append(" �� ")
				.append(" ���������������.�����������������.������������� ��� ������������������������������")
				.append(" ����������� �� ").append(" materialCode,").append(" stockCode");
		Dispatch query = Dispatch.call(connected.getDispatch(), "NewObject", "Query").getDispatch();
		Dispatch.put(query, "Text", sb.toString());
		Dispatch result = Dispatch.call(query, "Execute").toDispatch();
		Dispatch select = Dispatch.call(result, "Choose").toDispatch();

		while (Dispatch.call(select, "Next").getBoolean()) {
			Nomenclature material = new Nomenclature(Dispatch.get(select, "materialCode").getString(),
					Dispatch.get(select, "material").getString());
			Stock stock = new Stock(Dispatch.get(select, "stockCode").getString(),
					Dispatch.get(select, "stock").getString());
			MaterialsItemFrom1C item = new MaterialsItemFrom1C(material, stock, Dispatch.get(select, "amount").getInt(),
					Dispatch.get(select, "isImport").getBoolean(),"");
			materialList.add(item);
		}
		return materialList;
	}

	public static void sendOrder(OrderDocument orderDocument) {
		Variant connected = getConnection();
		Dispatch orderMaterialsManager = Dispatch
				.call(connected.getDispatch(), "NewObject", "DocumentManager.OrderMaterials").getDispatch();
		Dispatch orderMaterialsDoc = Dispatch.call(orderMaterialsManager, "CreateDocument").toDispatch();
		Dispatch.put(orderMaterialsDoc, "Date", "20170214");

		/* Constructed object */
		Dispatch constrObjManager = Dispatch
				.call(connected.getDispatch(), "NewObject", "CatalogManager.��������������������").getDispatch();
		Dispatch constrObjManagerDisp = Dispatch
				.call(constrObjManager, "FindByCode", orderDocument.getConstructedObjectCode()).toDispatch();
		Dispatch.put(orderMaterialsDoc, "ConstructedObject", Dispatch.get(constrObjManagerDisp, "Ref"));

		/* Stock object */
		Dispatch stockObjManager = Dispatch.call(connected.getDispatch(), "NewObject", "CatalogManager.������")
				.getDispatch();
		Dispatch stockObjManagerDisp = Dispatch.call(stockObjManager, "FindByCode", orderDocument.getStockCode())
				.toDispatch();
		Dispatch.put(orderMaterialsDoc, "Stock", Dispatch.get(stockObjManagerDisp, "Ref"));

		/* Materials table */
		Dispatch materialsTableDisp = Dispatch.get(orderMaterialsDoc, "materials").getDispatch();
		/* Materials item */
		for (OrderMaterialsItem item : orderDocument.getMaterials()) {
			Dispatch materialItemDisp = Dispatch.call(materialsTableDisp, "Add").getDispatch();

			/* Nomenclature object */
			Dispatch nomenclObjManager = Dispatch
					.call(connected.getDispatch(), "NewObject", "CatalogManager.������������").getDispatch();
			Dispatch nomenclObjDisp = Dispatch.call(nomenclObjManager, "FindByCode", item.getMaterial().getCode())
					.toDispatch();
			Dispatch.put(materialItemDisp, "Nomenclature", Dispatch.get(nomenclObjDisp, "Ref"));

			Dispatch.put(materialItemDisp, "Amount", item.getAmount());
			Dispatch.put(materialItemDisp, "Commecnts", item.getComments());
			Dispatch.put(materialItemDisp, "Reserve", item.isReserve());
			Dispatch.put(materialItemDisp, "AmountDays", item.getAmountDays());
		}
		Dispatch.call(orderMaterialsDoc, "write");

	}

	private static Variant getConnection() {
		ActiveXComponent v8App = new ActiveXComponent("v83.COMConnector");
		String conString = "File=D:/projects/1C/workbase;Usr=admin;Pwd=admin";
		return Dispatch.call(v8App, "Connect", conString);
	}
}
