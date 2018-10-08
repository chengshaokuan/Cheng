package com.csk.utils.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: Cheng
 * @description: 解析url 工具类
 * @author: Mr.Cheng
 * @create: 2018-08-14 16:30
 **/
public class JsoupParserUtils {

	private static Logger logger = LoggerFactory.getLogger(JsoupParserUtils.class);

	private static String mobileSearch = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=";

	/**
	 * @Description: 解析手机号归属地
	 * @param: mobile
	 * @return: java.lang.String
	 * @Author: Mr.Cheng
	 * @Date: 15:36 2018/10/8
	 */
	public static String parseMobileOwership(String mobile) {
		String provAndCity = "未知";
		try {
			Document document = Jsoup.connect(mobileSearch + mobile).execute().parse();
			Element tr = document.select("table").last().select("tr").first();
			if (tr.text().contains("++ ip138.com查询结果 ++")) {
				provAndCity = tr.nextElementSibling().nextElementSibling().text().replaceAll(" ", "-")
						.replaceAll("卡号归属地 ", "").trim();
				Element e = new Element(provAndCity);
			} else {
				provAndCity = tr.text();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return provAndCity;
	}

	@Test
	public void test() throws IOException {
		parseMobileOwership(mobileSearch);
	}

}
