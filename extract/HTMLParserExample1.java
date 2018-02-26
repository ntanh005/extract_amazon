package com.ntanh.extract;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HTMLParserExample1 {

	
	public static void main(String[] args) {
		 new AmazonService().getRelativeAsin("B0146D1XG6");
	}

}