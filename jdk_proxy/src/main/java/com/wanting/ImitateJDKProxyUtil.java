package com.wanting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;

public class ImitateJDKProxyUtil {
    /**
     * java 编码 过程
     * <p>
     * 1、有java类
     * <p>
     * 2、有对应的class
     * <p>
     * 3、class 加载成类
     */
    public static void newInstance(Object target) {

        String line = "\n";
        String tab = "\t";
        //拿到目标对象的 Class
        Class<?> targetClass = target.getClass();
        //java头文件
        String packageName = "package com.micheal;"+line;
//        System.out.println(packageName);

        //定义需要导入的包
        String importList = "import " + targetClass.getName() + ";"+line;
//        System.out.println(importList);

        //类的第一行
        String oneLine = "public class Proxy$66 implements " + targetClass.getSimpleName() + "{" +line;
//        System.out.println(oneLine);

        //属性
        String propertie = tab+"private " + targetClass.getSimpleName() + " target;"+line;
//        System.out.println(propertie);

        // 构造器
        String construstor =tab+"public Proxy$66("+targetClass.getSimpleName()+" target) {" +line
                +tab+tab+" this.target = target;" +line
                +tab+"}"+line;

        //获取所有的方法
        Method[] methods = targetClass.getMethods();

        String allMethod = "";
        for (Method method : methods) {
            /**
             *    public void selectData() {
             *         System.out.println("-------log------");
             *         target.selectData();
             *     }
             */
            allMethod += tab+"public "+method.getReturnType()+" "+method.getName()+"() {"+line;
            allMethod += tab+tab+"System.out.println(\"-------log------\");"+line;
            allMethod += tab+tab+"target."+method.getName()+"();"+line;
            allMethod += tab+"}"+line;
        }

//        System.out.println(allMethod);
        String proxyJava =  packageName+importList+oneLine+propertie+construstor+allMethod+"}";
        System.out.println(proxyJava);

        // 写出成java 文件
        File file = new File("d:\\com\\micheal\\Proxy$66.java");
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(proxyJava);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
