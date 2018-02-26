package com.ntanh.extract;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class AmazonService implements IAmazonService {

	@Override
	public void getRelativeAsin(String asin) {
		Document doc;
		try {
			// need http protocol
			doc = Jsoup.connect(String.format("https://www.amazon.co.jp/dp/%s/", asin)).get();
			List<AmazonAsin> lst = new KoExtract().extract(doc);
			if (lst.size() < 20) {
				lst.addAll(new ReviewExtract().extract(doc));
			}
			
			for(int idx = 0; idx < 20 && idx < lst.size(); idx++){
				System.out.printf("%s-%s\n", lst.get(idx).dt_asin_code, lst.get(idx).type);
			}
			return;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
