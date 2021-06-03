package ffffffff0x.domainSplit.Main;

import ffffffff0x.domainSplit.impl.DomainSplit;
import ffffffff0x.domainSplit.impl.FileUtils;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author: RyuZUSUNC
 * @create: 2021-06-03 11:26
 **/

public class CliController {
    File inputfile;
    File outputfile;
    String allResult;
    Map<String,String> multipleResult = new HashMap<>();
    Map<Integer, HashSet<String>> originalResult;

    public void run(String in,String out){
        fileSplit(in,out);
        paraPocessing(originalResult,false);
        FileUtils.outPutFile(outputfile,allResult,"UTF-8");
        System.out.println("任务完成，输出目录为: " + outputfile.getAbsolutePath());
    }

    public void fileSplit(String in,String output){
        try{
            inputfile = new File(in);
            outputfile = new File(output);
            originalResult = DomainSplit.domainSplit(inputfile);
        }catch (Exception e){
            System.out.println("输入有误，请检查文件路径");
        }
    }

    public Boolean isReady(String in,String out){
        if (in.equals("-in") && out.equals("-out")){
            return true;
        }else {
            waring();
            return false;
        }
    }

    public void waring(){
        System.out.println("语法参考: java -jar DomainSplit.jar -in [dir/input.txt] -out [dir/output.txt]");
    }

    private void paraPocessing(Map<Integer, HashSet<String>> result, boolean multipleFile){
        int k = 0;

        if(multipleFile){
            for (int i = k; i < result.size()-1; i++) {
                StringBuilder sb =new StringBuilder();
                for (String a:result.get(i)) {
                    sb.append(a).append("\n");
                }
                sb.append("\n");
                if (i==-1){
                    multipleResult.put("fileURLs.txt",sb.toString());
                }else {
                    multipleResult.put("level-" + i +".txt",sb.toString());
                }
            }
        }else {
            StringBuilder sb =new StringBuilder();
            for (int i = k; i < result.size()-1; i++) {
                for (String a:result.get(i)) {
                    sb.append(a).append("\n");
                }
            }
            allResult = sb.toString();
        }
    }
}
