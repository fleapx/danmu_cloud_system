package cn.partytime.util;

import cn.partytime.dataRpc.RpcConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * Created by administrator on 2017/2/14.
 */
@Component
@Slf4j
public class UploadFlashUtil {

    @Autowired
    private RpcConfigService rpcConfigService;

    public Integer countFlashFile(){
        File base = new File(rpcConfigService.getUploadPath());
        File[] files = base.listFiles();
        if( null != files){
            int num = 0;
            for(int i=0;i<files.length;i++){
                if(files[i].isDirectory() && !StringUtils.isEmpty(files[i].getName())){
                    String firstStr = files[i].getName().substring(0,1);
                    if(!".".equals(firstStr)){
                        num++;
                    }
                }
            }
            return num;
        }else{
            return null;
        }

    }

    public Integer countJavaFile(){
        File base = new File(rpcConfigService.getUploadPath());
        File[] files = base.listFiles();
        if( null != files){
            int num = 0;
            for(int i=0;i<files.length;i++){
                if(files[i].isFile() && !StringUtils.isEmpty(files[i].getName()) && files[i].getName().indexOf(".jar") !=-1){
                    num++;
                }
            }
            return num;
        }else{
            return null;
        }

    }


    public String getJavaFileName(){
        File base = new File(rpcConfigService.getUploadPath());
        File[] files = base.listFiles();
        if( null != files){
            for(int i=0;i<files.length;i++){
                if(files[i].isFile() && !StringUtils.isEmpty(files[i].getName()) && files[i].getName().indexOf(".jar") !=-1){
                    return files[i].getName();
                }
            }
        }

        return null;

    }

    public String getFlashName(){
        File base = new File(rpcConfigService.getUploadPath());
        File[] files = base.listFiles();
        if( null != files){
            for(int i=0;i<files.length;i++){
                if(files[i].isDirectory() && !StringUtils.isEmpty(files[i].getName())){
                    String firstStr = files[i].getName().substring(0,1);
                    if(!".".equals(firstStr)){
                        return files[i].getName();
                    }
                }
            }
        }
        return null;

    }


    private String execShell(String shellString) {
        log.info(shellString);
        Process process = null;
        StringBuffer sb = new StringBuffer();
        try {
            String[] commands = { "/bin/sh", "-c", shellString };
            log.info("exec cmd:"+shellString);
            process = Runtime.getRuntime().exec(commands);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = input.readLine()) != null) {
                sb.append(line);
            }
            input.close();
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                log.info("call sh failed. error code is :" + exitValue);
            } else {
                log.info("sh exec success");
            }
        } catch (Exception e) {
            log.error("", e);
        }

        return sb.toString();
    }

    public void createVersion(Integer type , String versionNum){
        if( type == 0){
            String javaName = getJavaFileName();
            String temp = javaName.substring(0,javaName.indexOf(".jar"));
            File javaFile = new File(rpcConfigService.getDownloadPath()+"/java/"+temp+"_version."+versionNum+".jar");
            if(javaFile.exists()){
                javaFile.delete();
            }
            File backupJavaFile = new File(rpcConfigService.getBackupPath()+"/java/"+temp+"_version."+versionNum+".jar");
            if(backupJavaFile.exists()){
                backupJavaFile.delete();
            }

            //删除原来的版本
            //String rmOldVersion = "rm -rf  "+downloadPath+"/java/*.jar";
            //execShell(rmOldVersion);

            String mvcmd = "cp "+rpcConfigService.getUploadPath()+"/*.jar "+rpcConfigService.getDownloadPath()+"/java/"+temp+"_version."+versionNum+".jar";
            execShell(mvcmd);

            String mvBackupCmd = "mv "+rpcConfigService.getUploadPath()+"/*.jar "+rpcConfigService.getBackupPath()+"/java/"+temp+"_version."+versionNum+".jar";
            execShell(mvBackupCmd);
        }else if(type ==1){
            String flashName = getFlashName();
            File tarFile = new File(rpcConfigService.getDownloadPath()+"/flash/"+flashName+"_version."+versionNum+".tar");
            if(tarFile.exists()){
                tarFile.delete();
            }
            File backFlash = new File(rpcConfigService.getBackupPath()+"/"+flashName+"_version."+versionNum);
            if(backFlash.exists()){
                String rmCmd = "rm -rf "+rpcConfigService.getBackupPath()+"/"+flashName+"_version."+versionNum;
                execShell(rmCmd);
            }

            //删除原来的版本
            //String rmOldVersion = "rm -rf  "+downloadPath+"/flash/*.tar";
            //execShell(rmOldVersion);


            String tarCmd = "tar zcf "+rpcConfigService.getDownloadPath()+"/flash/"+flashName+"_version."+versionNum+".tar -C "+rpcConfigService.getUploadPath()+"/"+flashName+" .";
            execShell(tarCmd);

            String mvFlashCmd = "mv "+rpcConfigService.getUploadPath()+"/"+flashName+"/ "+rpcConfigService.getBackupPath()+"/flash/"+flashName+"_version."+versionNum;
            execShell(mvFlashCmd);
        }


    }


}
