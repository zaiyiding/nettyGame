package com.tool;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/*
 *鑾峰彇鏂囦欢鐨刣ocument瀵硅薄锛岀劧鍚庤幏鍙栧搴旂殑鏍硅妭鐐癸紝閫掑綊閬嶅巻鎵�鏈�
 *浣跨敤dom4j杩涜瑙ｆ瀽锛�
 */
public class GetXmlMassage {
	public void testGetDom() throws Exception{
		  //鏍规嵁鎸囧畾鐨勮矾寰勫垱寤篺ile瀵硅薄
		  File file = new File("test.xml");
		  //鍒涘缓涓�涓猄AXReader瀵硅薄锛宒om4j鏂规硶
		  SAXReader reader = new SAXReader();
		  //鑾峰彇document瀵硅薄锛屽鏋滄枃妗ｆ棤鑺傜偣锛屽垯浼氭姏鍑篍xception鎻愮ず锛宒om4j鏂规硶
		  Document document =reader.read(file);  //read鏂规硶閲嶈浇
		  //鑾峰彇鏍硅妭鐐癸紝dom4j鏂规硶
		  Element root = document.getRootElement();
		  //浠庢牴鑺傜偣寮�濮嬮亶鍘嗘墍鏈夎妭鐐�
		  this.getNodes(root);
	  }

	private void getNodes(Element node) {
		// TODO Auto-generated method stub
		System.out.println("-----------------------------");
		
//		褰撳墠鑺傜偣鐨勫悕绉般�佹枃鏈唴瀹瑰拰灞炴��
//		褰撳墠鑺傜偣鍚嶇О
		System.out.println("褰撳墠鑺傜偣鍚嶇О锛�"+node.getName());
//		褰撳墠鑺傜偣鍐呭
		System.out.println("褰撳墠鑺傜偣鐨勫唴瀹癸細"+node.getTextTrim());
//		褰撳墠鑺傜偣鐨勬墍鏈夊睘鎬х殑list锛宒om4j鏂规硶
		List<Attribute> listAttr =node.attributes();
//		閬嶅巻褰撳墠鑺傜偣鐨勬墍鏈夊睘鎬�
		for(Attribute attr:listAttr) {
			String name = attr.getName();      //灞炴�у悕绉�
			String value = attr.getValue();    //灞炴�х殑鍊�
			System.out.println("灞炴�у悕绉帮細"+name+"灞炴�у�硷細"+value);
		}
		
//		閫掑綊閬嶅巻褰撳墠鑺傜偣鎵�鏈夌殑瀛愯妭鐐�
		List<Element> listElement = node.elements();  //鎵�鏈変竴绾у瓙鑺傜偣鐨刲ist
		for(Element e:listElement){    //閬嶅巻鎵�鏈変竴绾у瓙鑺傜偣
			this.getNodes(e);          //閫掑綊
		}
		
	}
	
	
}
