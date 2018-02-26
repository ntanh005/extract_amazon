package com.ntanh.extract;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

public class KoExtract implements IExtract {

	@Override
	public List<AmazonAsin> extract(Document doc) {
		List<AmazonAsin> lst = new ArrayList();
		Gson gson = new Gson();
		Elements ko_type = doc.select("div#view_to_purchase-sims-feature>div>div>ul>li");
		for(int idx = 0; idx < 20 && idx < ko_type.size(); idx++)	{			
			Element e = ko_type.get(idx).select("span>div").first();
			String data = e.attr("data-p13n-asin-metadata");				
			Map<String, Object> map = new HashMap<String, Object>();
			map = (Map<String, Object>) gson.fromJson(data, map.getClass());			
			AmazonAsin obj = new AmazonAsin();
			obj.setAsin_code(map.get("asin").toString());
			obj.setDate(  new Date());
			obj.setDt_asin_code(map.get("asin").toString());
			obj.setType(1);
			lst.add(obj);
		}
		
		return lst;
	}

}
