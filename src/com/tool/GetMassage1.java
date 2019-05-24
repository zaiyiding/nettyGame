package com.tool;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;


import gnu.trove.map.TLongObjectMap;
import gnu.trove.map.hash.TLongObjectHashMap;

public class GetMassage1 {
	public class tmpStruct2{
		public int to_state = 0;
		public int type = 0;
	
		public String toString() {
				
		return "to_state:" + to_state + " type:" + type ;	
		}
	}
	public class tmpStruct{		
		Map<Integer, tmpStruct2> playersMap = new HashMap<Integer, tmpStruct2>();
	}
	//public final static TLongObjectMap<tmpStruct> playersMap = new TLongObjectHashMap<tmpStruct>();
	public final static Map<Integer, tmpStruct> playersMap = new HashMap<Integer, tmpStruct>();
	
	public void read() {

		// TODO Auto-generated method stub

		File file = new File("E:\\javapro\\famlilyStar\\src\\com\\tool\\state.xml");

		SAXReader reader = new SAXReader();       //创建一个SAXReader对象

		Document document = null;
		try {
			document = reader.read(file);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     //获取document对象

		if (null == document) {
			return;
		}
		
		Element root = document.getRootElement(); //获取根节点
		
		List<Element> childElement = root.elements("state"); //所有一节子节点的list
		List<Element> childfromState = childElement.get(0).elements("from_state");
		
		
		for(Element child : childfromState){    //遍历所有一级子节点
			int nId = Integer.parseInt(child.attribute("id").getText());
			List<Element> secondelements = child.elements();
			for(Element secondChild : secondelements) {
				tmpStruct input = null;
				if (!playersMap.containsKey(nId)) {
					input = new tmpStruct();
					playersMap.put(nId, input);
				}
				input = playersMap.get(nId);
				int event = Integer.parseInt(secondChild.attribute("event").getText());
				int to_state = Integer.parseInt(secondChild.attribute("to_state").getText());
				int type = Integer.parseInt(secondChild.attribute("type").getText());
				tmpStruct2 input2 = new tmpStruct2();
				input2.to_state = to_state;
				input2.type = type;
				input.playersMap.put(event, input2);
				//playersMap.put(nId, input);
			}								
		}
		
		int size = playersMap.size();
		
		tmpStruct tmp = playersMap.get(101110);
		tmpStruct2 tmp2 = tmp.playersMap.get(12);
		
		System.out.println(tmp2.toString());
	}
	
	
	
	public static void main(String[] args) throws DocumentException {
		GetMassage1 tmp = new GetMassage1();
		tmp.read();
	}
}