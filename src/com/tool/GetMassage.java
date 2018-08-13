package com.tool;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

public class GetMassage {

	public static void main(String[] args) throws DocumentException {
		// TODO Auto-generated method stub
		//鏍规嵁鎸囧畾鐨勮矾寰勫垱寤篺ile瀵硅薄
		File file = new File("test.xml");
		//鍒涘缓涓�涓猄AXReader瀵硅薄锛宒om4j鏂规硶
		SAXReader reader = new SAXReader();
		//鑾峰彇document瀵硅薄锛屽鏋滄枃妗ｆ棤鑺傜偣锛屽垯浼氭姏鍑篍xception鎻愮ず锛宒om4j鏂规硶
		Document document =reader.read(file);  //read鏂规硶閲嶈浇
		//鑾峰彇鏍硅妭鐐癸紝dom4j鏂规硶
		Element root = document.getRootElement();
		
//		閬嶅巻褰撳墠鑺傜偣鎵�鏈夌殑瀛愯妭鐐�
		List<Element> childElement = root.elements();  //鎵�鏈変竴绾у瓙鑺傜偣鐨刲ist
		for(Element child:childElement){    //閬嶅巻鎵�鏈変竴绾у瓙鑺傜偣
//			鏈煡灞炴�у悕鎯呭喌涓�
			List<Attribute> attributeList =child.attributes();
			for(Attribute attr : attributeList){
				System.out.println(attr.getName()+":"+attr.getValue());
			}
//			鏈煡瀛愯妭鐐规儏鍐典笅
			List<Element> ListElement = child.elements();  //鎵�鏈夊瓙鑺傜偣鐨刲ist
			for(Element ele : ListElement) {
				System.out.println(ele.getName()+":"+ele.getText());
			}
//			OutputFormat format = OutputFormat.createPrettyPrint();  
			System.out.println();
			
		}

	}
}