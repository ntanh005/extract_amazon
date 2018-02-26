package com.ntanh.extract;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AmazonService implements IAmazonService {
	IAmazonDao dao = new AmazonDao();

	@Override
	public void getRelativeAsin(String asin) throws Exception {
		long startTime = System.currentTimeMillis();

		Document doc;
		try {
			// need http protocol
			
			doc = Jsoup.connect(String.format("https://www.amazon.co.jp/dp/%s/", asin))
					 .userAgent("Mozilla 5.0 (Windows NT 6.1)")
					.get();
			//
			if("Amazon CAPTCHA".compareTo(doc.title()) == 0){
				System.out.printf("%s-%s\n", asin, "Amazon CAPTCHA");
				//Thread.sleep(5000);
				return;
			}else{
				System.out.println(doc.title());
			}
			List<AmazonAsin> lst = new KoExtract().extract(doc, asin);
			if (lst.size() < 20) {
				lst.addAll(new ReviewExtract().extract(doc, asin));
			}		
			dao.insertAsinList(asin,  lst);
			if(3000 > (System.currentTimeMillis() - startTime)){
				Thread.sleep(3000 - System.currentTimeMillis() + startTime);
			}
			return;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
