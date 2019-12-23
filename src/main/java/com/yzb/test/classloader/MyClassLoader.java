package com.yzb.test.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {

    public String path;

    public String classLoaderName;

    public MyClassLoader(ClassLoader parent, String path, String classLoaderName) {
        super(parent);
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        System.out.println("findClass");
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ( (i = in.read())  != -1) {
                out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }

}
