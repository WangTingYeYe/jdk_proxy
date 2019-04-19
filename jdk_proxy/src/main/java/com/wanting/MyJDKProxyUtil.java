package com.wanting;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class MyJDKProxyUtil {
        /**
         * java 编码 过程
         * <p>
         * 1、有java类
         * <p>
         * 2、有对应的class
         * <p>
         * 3、class 加载成类
         */
        public static Object newInstance(Object target) {



            String line = "\n";
            String tab = "\t";
            //拿到目标对象的 接口 Class
            Class<?> targetClass = target.getClass().getInterfaces()[0];
            //java头文件
            String packageName = "package com.micheal;"+line;
//        System.out.println(packageName);

            //定义需要导入的包
            String importList = "import " + targetClass.getName() + ";"+line;
            importList += "import java.lang.*;"+line;
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

                String returnType = method.getReturnType().getName();
                allMethod += tab+"public "+returnType+ " "+ method.getName()+"() {"+line;
                allMethod += tab+tab+"System.out.println(\"-------log------\");"+line;

                //如果有返回值
                if(!"void".equals(returnType)){
                    allMethod +=  tab+tab+"return target."+method.getName()+"();"+line;

                }else {
                    allMethod += tab+tab+"target."+method.getName()+"();"+line;
                }
                allMethod += tab+"}"+line;
            }

//        System.out.println(allMethod);
            String proxyJava =  packageName+importList+oneLine+propertie+construstor+allMethod+"}";
//            System.out.println(proxyJava);

            // 写出成java 文件
            File file = new File("D:\\com\\micheal\\Proxy$66.java");
            try {
                FileWriter fw = new FileWriter(file);
                fw.write(proxyJava);
                fw.flush();
                fw.close();
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

                StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
                Iterable units = fileMgr.getJavaFileObjects(file);

                JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
                t.call();
                fileMgr.close();

                URL[] urls = new URL[]{new URL("file:D:\\\\")};
                URLClassLoader urlClassLoader = new URLClassLoader(urls);
                Class clazz = urlClassLoader.loadClass("com.micheal.Proxy$66");

                //获取构造器
                Constructor constructor = clazz.getConstructor(targetClass);

                Object newInstance = constructor.newInstance(target);
                return newInstance;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
}
