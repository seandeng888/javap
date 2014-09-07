package org.seandeng.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Constants {
	
	public final static String INCOME_SELF = "1"; 

	/**
	 * 产品品牌
	 */
	public final static String[] ARRAY_PROD_BRAND = {"利得盈","建行财富","乾图理财","乾元","汇得盈",
		"QDII","对公债券产品","本外币组合产品","新股随心打","龙信",
		"大丰收","其他"};
	
	/**
	 * 贷款种类
	 */
	public final static List LIST_LOAN_TYPE = new ArrayList() {
		private static final long serialVersionUID = 7924517407280185281L;
		{
			add("发放单笔贷款");
			add("转让单笔贷款");
			add("转让贷款包内单位贷款");
			add("转让个人贷款资产包");
		}
	};

	/**
	 * 人行产品类型
	 */
	public final static Map MAP_PRODUCT_TYP_HP = new HashMap() {
		private static final long serialVersionUID = -1527292751427350723L;
		{
			put("100", "信托类");
			put("200", "结构性");
			put("300", "QDII");
			put("400", "其他");
			put("500", "不适用");
		}
	};
	
    public final static class SPRING_NAME{
    	public static final String BB_SERVICE = "BB.BBService";
    	public static final String BB_SUMMATE_SERVICE = "BB.BBSummateService";
    	public static final String BB_AUDIT_SERVICE = "BB.BBAuditService";
    }
	
}