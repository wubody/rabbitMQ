package com.example.demo.entity;

/**
 * @author: WJQ
 * @date 2020/7/15 17:20
 */

import java.io.*;

/**
 * 技术考核题目3：IO/NIO操作
 * 考点1：文件的拷贝
 * 考点2：文件编码注意事项
 * 考点3：对文件流操作的注意事项
 * @author yyz
 */
public class Test3 {
    /**
     * 实现方法剪切文件：srcFile剪切至destFile，对于所有的TXT文件，需要对另存为的文件做编码转换：转换为UTF-8编码保存成新文件。
     *
     * @param targetDir
     */
    public static void cutFile(File srcDir, File destDir) throws IOException {
        if (!srcDir.exists()) {
            System.out.println("文件不存在!");
            return;
        }
        if(srcDir.isDirectory()){
            File[] files = srcDir.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files != null) {
                        for (File file : files) {
                            if (file.isDirectory()) {
                                String destPath = file.getPath().replace(srcDir.getPath(), destDir.getPath());
                                File d=new File(destPath);
                                if(!d.exists()){
                                    d.mkdirs();
                                }
                                cutFile(file, d);
                            }else {
                                String destPath = file.getPath().replace(srcDir.getPath(), destDir.getPath());
                                moveFile(file,new File(destPath));
                            }
                        }
                    }
                }
            }
        }else {
            moveFile(srcDir,destDir);
        }
    }

    /**
     * 剪切文件
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void moveFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.exists()) {
            System.out.println("源文件不存在!");
            return;
        }
        //创建文件流对象
        FileInputStream fis = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            if(srcFile.getPath().indexOf(".txt")>-1){
                osw = new OutputStreamWriter(fos, "UTF-8");
            }
            else {
                osw = new OutputStreamWriter(fos);
            }
            //为读取文件做准备
            byte[] bs = new byte[50];//储存读取的数据
            int count = 0;//储存读取的数据量

            while ((count = fis.read(bs)) != -1) {
                fos.write(bs, 0, count);
                osw.flush();
            }
            //复制完毕，关流
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        //删除文件
        srcFile.delete();
    }
    public static void main(String[] param) {
        File src=new File("d:\\a");
        File dest=new File("d:\\b");
        try {
            cutFile(src,dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}