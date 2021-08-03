package com.slc.jvm.classloader;

import sun.misc.Launcher;

import java.net.URL;
import java.util.Arrays;

/**
 * 获取classLoader
 *
 * @author slc
 */
public class Test {

    /**
     * 一、获取ClassLoader
     * <p>
     * <p>
     * --------------------
     * 引导类加载器
     * 加载内容位置：
     * 1）JAVA_HOME/jre/lib/rt.jar、resources.jar | sun.bot.class.path路径下的内容；
     * 2）加载java核心类库，出于对安全问题的考虑，引导类加载器只加载 java、javax、sun 开头的类；
     * 3）加载扩展类和应用程序类加载器；（这两类加载器也是java语言的类）
     * --------------------
     * 扩展类加载器
     * 加载内容位置：
     * 1）java.ext.dir属性制定的目录
     * 2）JDK安装目录下 jre/lib/ext  (用户添加在该目录的jar类也会被加载)
     * --------------------
     * 应用类加载器
     * 1）用户自定义类的加载器  默认为  运用类加载器
     */
    @org.junit.Test
    public void testGetObj() {
        //应用类加载器: Launcher$AppClassLoader@18b4aac2
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(appClassLoader);

        //扩展类加载器: Launcher$ExtClassLoader@e73f9ac
        ClassLoader extClassLoader = appClassLoader.getParent();
        System.out.println(extClassLoader);

        //引导类加载器: null
        ClassLoader bootStrapClassLoader = extClassLoader.getParent();
        System.out.println(bootStrapClassLoader);

        //用户自定义类加载器: Launcher$AppClassLoader@18b4aac2
        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);

        //引导类加载器: null 加载核心类库
        ClassLoader classLoaderForString = String.class.getClassLoader();
        System.out.println(classLoaderForString);
    }

    /**
     * 二、获取ClassLoader加载的jar包范围
     */
    @org.junit.Test
    public void testLoadJarPath() {
        System.out.println("---------------------启动类加载器---------------------");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        Arrays.stream(urLs).forEach(url -> System.out.println(url.toExternalForm()));
        System.out.println("---------------------引导加载器---------------------");
        String extDirs = System.getProperty("java.ext.dirs");
        Arrays.stream(extDirs.split(";")).forEach(System.out::println);
    }

}