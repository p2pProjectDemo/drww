package com.drww.service;


import com.drww.entity.IdCard;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * className:IdCardService
 * descriptive:
 * author:荣恒
 * createTime:2018/11/21 9:13
 */
@SuppressWarnings("all")

@Service
public class IdCardService {
	/**
	 * 生成身份证信息
	 * @param cardNo
	 * @return 返回身份证信息
	 * @throws UnsupportedEncodingException
	 */
	public static String getIdCardDetail(String cardNo){
		// 获取身份证信息
		IdCard idcard = getIdCardInfo(cardNo);

		System.out.println(cardNo);
		String substring = cardNo.substring(6, 10);
		System.out.println(substring);
		Date date = new Date();

		SimpleDateFormat year = new SimpleDateFormat("yyyy");

		String year1 = year.format(date);
		System.out.println(year1);


		int fullYear = Integer.valueOf(year1)-Integer.valueOf(substring);
		System.out.println(fullYear);

		// 存储文本信息
		StringBuffer news = new StringBuffer();

		if (idcard != null) {
			news.append("所属地区:"+idcard.getAtt()).append(":");
			news.append("出生日期:"+idcard.getBorn()).append(":");
			news.append("性别:"+idcard.getSex()).append(":");
			news.append("年龄:"+fullYear);
		}

		if(news.length() == 0){
			news.append("身份证号码").append(cardNo).append("不存在,请重新输入!");
		}

		return news.toString();
	}

	/**
	 * 获取身份证信息
	 * @param cardNo
	 * @return 返回身份证信息
	 */
	public static IdCard getIdCardInfo(String cardNo){
		URL url = null;
		IdCard idCard = new IdCard();
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			url = new URL("http://api.k780.com:88/?app=idcard.get&idcard="+cardNo+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=xml");

			System.out.println(url);

			Document doc = builder.parse(url.openStream());
			NodeList node = doc.getElementsByTagName("result");

			for(int i=0;i<node.getLength();i++){
				String idcard = "";
				String born = "";
				String sex = "";
				String att = "";

				if(doc.getElementsByTagName("idcard").item(i).getFirstChild() != null){
					idcard = doc.getElementsByTagName("idcard").item(i).getFirstChild().getNodeValue();
				}
				if(doc.getElementsByTagName("born").item(i).getFirstChild() != null){
					born = doc.getElementsByTagName("born").item(i).getFirstChild().getNodeValue();
				}
				if(doc.getElementsByTagName("sex").item(i).getFirstChild() != null){
					sex = doc.getElementsByTagName("sex").item(i).getFirstChild().getNodeValue();
				}
				if(doc.getElementsByTagName("att").item(i).getFirstChild() != null){
					att = doc.getElementsByTagName("att").item(i).getFirstChild().getNodeValue();
				}
				idCard.setIdCard(idcard);
				idCard.setBorn(born);
				idCard.setSex(sex);
				idCard.setAtt(att);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return idCard;
	}

	public static void main(String[] args){

			System.out.print(getIdCardDetail("410102199003071492"));

	}
}
