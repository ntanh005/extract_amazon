package com.ntanh.extract;

import java.util.List;

public interface IAmazonDao {
	void insertAsinList(String asin, List<AmazonAsin> lst) throws Exception;
}
