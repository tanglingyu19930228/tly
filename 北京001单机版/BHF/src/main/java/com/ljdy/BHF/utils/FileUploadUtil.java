/**
 * @项目名称 RealestateRegister
 * @Title FileUploadUtilImpl.java 
 * @Package com.ljdy.realestateRegister.utils 
 * @Description (文件上传工具类) 
 * @Author 李金阳 lijy@luojiadeyi.com 
 * @Date 2016年6月5日 下午2:56:37 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
package com.ljdy.BHF.utils;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;

import com.ljdy.BHF.model.FileModel;

/** 
 * @ClassName FileUploadUtilImpl 
 * @Description (文件上传工具类) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月5日 下午2:56:37 
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
public class FileUploadUtil {
    
     /**
     * @Title getFileExt 
     * @Description (获取上传文件的扩展名) 
     * @param fileName
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2016年6月5日
     * @修改历史  
     *     1. [2016年6月5日]创建文件 by 李金阳
     */
    private static String getFileExt(String fileName){
        return FilenameUtils.getExtension(fileName);
    }
    
     /**
     * @Title newFileName 
     * @Description (通过UUID生成策略，生成新的唯一的文件名) 
     * @param fileName
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2016年6月5日
     * @修改历史  
     *     1. [2016年6月5日]创建文件 by 李金阳
     */
    private static String newFileName(String fileName){
        return UUID.randomUUID().toString()+"."+getFileExt(fileName);
    }
    
     /**
     * @Title uploadFile 
     * @Description (执行文件上传操作，并返回新的文件名) 
     * @param fileModel
     * @param root
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2016年6月5日
     * @修改历史  
     *     1. [2016年6月5日]创建文件 by 李金阳
     */
    public static String uploadFile(FileModel fileModel, String root){
        
        // 获取上传文件的原文件名
        String fileName = fileModel.getUploadFileName();
        
        // 获取上传文件的保存路径
        String filePath = root + File.separator + getFileExt(fileName);
        
        // 获取上传文件的新文件名
        String newFileName = newFileName(fileName);
        
        try {
            // 将临时文件拷贝到指定的保存路径
            FileUtil.copyFile(fileModel.getUpload(), new File(filePath,newFileName));
            
            // 返回上传文件保存的相对路径
            return File.separator + getFileExt(fileName) + File.separator + newFileName;
            
        } catch (Exception e) {
            
            throw new RuntimeException(e);
            
        }finally{
            
            // 上传完毕，删除临时文件
            fileModel.getUpload().delete();
            
        }
    }

    /**
     * 删除文件
     * @param fileName
     */
    public void deleteFile(String fileName){
        File file = new File(fileName);
        if(file.exists()){
            if(file.isFile()){
                file.delete();
            }else{
                deleteDir(fileName);
            }
        }
    }

    /**
     * 删除文件夹及文件夹下所有文件
     * @param dir
     */
    public void deleteDir(String dir){
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if(!dir.endsWith(File.separator)){
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        if(dirFile.exists() && dirFile.isDirectory()){
            File[] files = dirFile.listFiles();//子文件或子目录
            for(int i=0;i<files.length;i++){
                if(files[i].isFile()){
                    files[i].delete();
                }else{
                    deleteDir(files[i].getAbsolutePath());
                }
            }
        }
        dirFile.delete();//删除当前目录
    }
}
