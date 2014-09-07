package org.seandeng.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Constants {
	
	public final static String INCOME_SELF = "1"; 

	/**
	 * ��ƷƷ��
	 */
	public final static String[] ARRAY_PROD_BRAND = {"����ӯ","���вƸ�","Ǭͼ���","ǬԪ","���ӯ",
		"QDII","�Թ�ծȯ��Ʒ","�������ϲ�Ʒ","�¹����Ĵ�","����",
		"�����","����"};
	
	/**
	 * ��������
	 */
	public final static List LIST_LOAN_TYPE = new ArrayList() {
		private static final long serialVersionUID = 7924517407280185281L;
		{
			add("���ŵ��ʴ���");
			add("ת�õ��ʴ���");
			add("ת�ô�����ڵ�λ����");
			add("ת�ø��˴����ʲ���");
		}
	};

	/**
	 * ���в�Ʒ����
	 */
	public final static Map MAP_PRODUCT_TYP_HP = new HashMap() {
		private static final long serialVersionUID = -1527292751427350723L;
		{
			put("100", "������");
			put("200", "�ṹ��");
			put("300", "QDII");
			put("400", "����");
			put("500", "������");
		}
	};
	
    public final static class SPRING_NAME{
    	public static final String BB_SERVICE = "BB.BBService";
    	public static final String BB_SUMMATE_SERVICE = "BB.BBSummateService";
    	public static final String BB_AUDIT_SERVICE = "BB.BBAuditService";
    }
	
}