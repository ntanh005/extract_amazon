package com.ntanh.extract;

import java.util.List;

import org.jsoup.nodes.Document;

public interface IExtract {
	public List<AmazonAsin> extract(Document doc);
}
