package org.seandeng.common;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 *  @author dengsc
 */
public class SystemProperties {
    static Map propertyKeyCodeNameMapping = new HashMap();
    static {
        propertyKeyCodeNameMapping.put("java.version","Java的运行环境版本");
        propertyKeyCodeNameMapping.put("java.vendor","Java的运行环境供应商");
        propertyKeyCodeNameMapping.put("java.vendor.url","Java供应商的URL");
        propertyKeyCodeNameMapping.put("java.home","Java的安装路径");
        propertyKeyCodeNameMapping.put("java.vm.specification.version","Java的虚拟机规范版本");
        propertyKeyCodeNameMapping.put("java.vm.specification.vendor","Java的虚拟机规范供应商");
        propertyKeyCodeNameMapping.put("java.vm.specification.name","Java的虚拟机规范名称");
        propertyKeyCodeNameMapping.put("java.vm.version","Java的虚拟机实现版本");
        propertyKeyCodeNameMapping.put("java.vm.vendor","Java的虚拟机实现供应商");
        propertyKeyCodeNameMapping.put("java.vm.name","Java的虚拟机实现名称");
        propertyKeyCodeNameMapping.put("java.specification.version","Java运行时环境规范版本");
        propertyKeyCodeNameMapping.put("java.specification.vender","Java运行时环境规范供应商");
        propertyKeyCodeNameMapping.put("java.specification.name","Java运行时环境规范名称");
        propertyKeyCodeNameMapping.put("java.class.version","Java的类格式版本号");
        propertyKeyCodeNameMapping.put("java.class.path","Java的类路径");
        propertyKeyCodeNameMapping.put("java.library.path","加载库时搜索的路径列表");
        propertyKeyCodeNameMapping.put("java.io.tmpdir","默认的临时文件路径");
        propertyKeyCodeNameMapping.put("java.ext.dirs","一个或多个扩展目录的路径");
        propertyKeyCodeNameMapping.put("os.name","操作系统的名称");
        propertyKeyCodeNameMapping.put("os.arch","操作系统的构架");
        propertyKeyCodeNameMapping.put("os.version","操作系统的版本");
        propertyKeyCodeNameMapping.put("file.separator","文件分隔符");
        propertyKeyCodeNameMapping.put("path.separator","路径分隔符");
        propertyKeyCodeNameMapping.put("line.separator","行分隔符");
        propertyKeyCodeNameMapping.put("user.name","用户的账户名称");
        propertyKeyCodeNameMapping.put("user.home","用户的主目录");
        propertyKeyCodeNameMapping.put("user.dir","用户的当前工作目录");
    }
	
    public static void main(String [] args) {
        java.util.Properties pp = System.getProperties();
        Enumeration<?> names = pp.propertyNames();
        String propertyKey = null;
        while(names.hasMoreElements()){
            propertyKey = (String)names.nextElement();
            System.out.println(propertyKeyCodeNameMapping.get(propertyKey) + "||" + propertyKey+"||"+pp.getProperty(propertyKey));
        }
    }
}
