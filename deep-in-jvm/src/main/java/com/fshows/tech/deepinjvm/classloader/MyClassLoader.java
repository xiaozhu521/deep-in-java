/**
 * fshows.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.fshows.tech.deepinjvm.classloader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 *
 * @author xiaotian
 * @version MyClassLoader.java, v 0.1 2020-11-01 17:06
 */
public class MyClassLoader extends ClassLoader{

    /**
     * 类加载器名称
     */
    private String classLoaderName;

    public MyClassLoader(String classLoaderName) {
        this.classLoaderName = classLoaderName;
    }

    /**
     * 用于寻找文件
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        // 直接调用 defineClass 来获取 Class 对象
        return defineClass(name, b, 0, b.length);
    }



    /**
     * 用于加载类文件，说白了就是将 class 文件转换成字节数组
     * @param name
     * @return
     */
    private byte[] loadClassData(String name) {
        //类文件的全路径
        name = this.getClass().getClassLoader().getResource("").getPath() + name + ".xlass";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            //读取字节码文件中的字节流
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int tmp = 0;
            while((tmp = in.read()) != -1) {
                out.write(255 - tmp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 保证资源的释放
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }
        return out.toByteArray();
    }

    public static void main(String[] args) throws Exception{
        //初始化自定义类加载器
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class clazz = classLoader.loadClass("Hello");
        Object obj =  clazz.newInstance();
        Method method = clazz.getMethod("hello");
        method.invoke(obj);
    }
}

