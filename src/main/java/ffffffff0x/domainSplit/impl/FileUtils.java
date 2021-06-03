package ffffffff0x.domainSplit.impl;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class FileUtils {
    /**
     * 获取文件的byte数组格式
     * @param file
     * @return
     */
    public static byte[] getFilebyte(File file){
        FileInputStream fileInputStream;
        byte[] result = null;
        try {
            fileInputStream = new FileInputStream(file);
            result = new byte[fileInputStream.available()];
            fileInputStream.read(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 按行获取文本
     * @param file
     * @return
     */
    public static ArrayList<String> getFileLines(File file){
        ArrayList<String> result = new ArrayList<>();
        try {
            FileInputStream inputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String str;
            while((str = bufferedReader.readLine()) != null)
            {
               result.add(str);
            }
            inputStream.close();
            bufferedReader.close();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存文本格式文件至存储
     * @param out
     * @param charset
     */
    public static void outPutFile(File file,String out,String charset){
        if (!file.exists()) {
            file.getParentFile().mkdirs();// 目录不存在的情况下，创建目录。
        }
        if(file!=null) {
            try {
                OutputStreamWriter OSW = new OutputStreamWriter(new FileOutputStream(file), charset);
                OSW.write(out);
                OSW.flush();
                OSW.close();
//                Desktop.getDesktop().open(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 返回载入文件/字符串的按行分割后的ArrayList<String>
     * @param object
     * @return
     */
    public static ArrayList<String> readLine(Object object){
        if(object instanceof File){
            return FileUtils.getFileLines((File)object);
        }else {
            String text = (String)object;
            ArrayList<String> list = new ArrayList<>();
            //把数组转成集合，也就是把数组里面的数据存进集合；
            Collections.addAll(list, text.split("\n"));
            return list;
        }
    }
}
