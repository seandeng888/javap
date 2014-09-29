package org.seandeng.util;

public abstract class SystemPropertyUtils {

	/** 系统属性占位符的前缀: "${" */
	public static final String PLACEHOLDER_PREFIX = "${";

	/** 系统属性占位符的后缀: "}" */
	public static final String PLACEHOLDER_SUFFIX = "}";

	/** 系统属性占位符的值分隔符: ":" */
	public static final String VALUE_SEPARATOR = ":";

	private static final PropertyPlaceholderHelper strictHelper =
			new PropertyPlaceholderHelper(PLACEHOLDER_PREFIX, PLACEHOLDER_SUFFIX, VALUE_SEPARATOR, false);

	private static final PropertyPlaceholderHelper nonStrictHelper =
			new PropertyPlaceholderHelper(PLACEHOLDER_PREFIX, PLACEHOLDER_SUFFIX, VALUE_SEPARATOR, true);

	/**
	 * 解析text中的${...}占位符，用相应的系统属性系统属性值。
	 */
	public static String resolvePlaceholders(String text) {
		return resolvePlaceholders(text, false);
	}

	/**
	 * 解析text中的${...}占位符，用相应的系统属性系统属性值。如果标志位为true，则没有默认值的无法解析的占位符将保留原样不被解析。
	 * @param ignoreUnresolvablePlaceholders flag to determine is unresolved placeholders are ignored
	 */
	public static String resolvePlaceholders(String text, boolean ignoreUnresolvablePlaceholders) {
		PropertyPlaceholderHelper helper = (ignoreUnresolvablePlaceholders ? nonStrictHelper : strictHelper);
		return helper.replacePlaceholders(text, new SystemPropertyPlaceholderResolver(text));
	}


	private static class SystemPropertyPlaceholderResolver implements PropertyPlaceholderHelper.PlaceholderResolver {

		private final String text;

		public SystemPropertyPlaceholderResolver(String text) {
			this.text = text;
		}

		public String resolvePlaceholder(String placeholderName) {
			try {
				String propVal = System.getProperty(placeholderName);
				if (propVal == null) {
					// 没找到系统属性时，就去查找系统环境变量。
					propVal = System.getenv(placeholderName);
				}
				return propVal;
			}
			catch (Throwable ex) {
				System.err.println("Could not resolve placeholder '" + placeholderName + "' in [" +
						this.text + "] as system property: " + ex);
				return null;
			}
		}
	}

    public static void main(String []args) {
        System.setProperty("root.key","${muse.root}");
        System.setProperty("muse.root","D:\\project");
        System.out.println(resolvePlaceholders("${root.key}/logs/app.log", true));
    }
}
