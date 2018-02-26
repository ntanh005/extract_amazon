package com.ntanh.extract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ReviewExtract  implements IExtract{

	@Override
	public List<AmazonAsin> extract(Document doc, String asin) {
		List<AmazonAsin> lst = new ArrayList();
		//*[@id="purchase-sims-feature"]/div
		Element related = doc.select("div#purchase-sims-feature>div").first();
		if(related == null){
			return lst;
		}
		String xx = related.attr("data-a-carousel-options");
		int str = xx.indexOf("[");		
		String ids = xx.substring(str + 1, xx.indexOf("]"));
		String[] arrIds = ids.split("," );
		for(int idx = 0; idx < 20 && idx < arrIds.length; idx++){
			String asindt = arrIds[idx].substring(1, arrIds[idx].length() - 1);
			AmazonAsin obj = new AmazonAsin();
			obj.setAsin_code(asin);
			obj.setDate(  new Date());
			obj.setDt_asin_code(asindt);
			obj.setType(0);
			lst.add(obj);
		}
		return lst;
	}

}
