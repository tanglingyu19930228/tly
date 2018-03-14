/**
 * @项目名称 RealestateRegister
 * @Title FileModel.java 
 * @Package com.ljdy.realestateRegister.model 
 * @Description (文件信息实体类) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月5日 下午2:49:12 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
package com.ljdy.BHF.model;

import java.io.File;

/** 
 * @ClassName FileModel 
 * @Description (文件信息实体类) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月5日 下午2:49:12 
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
public class FileModel {
    
    /** 
     * @Description (注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件)  
     * @Date 2016年6月5日 下午2:50:27 
    */ 
    private File upload;
    
    /** 
     * @Description (上传文件的MIME类型)  
     * @Date 2016年6月5日 下午2:50:58 
    */ 
    private String uploadContentType;
    
    /** 
     * @Description (上传文件的名称)  
     * @Date 2016年6月5日 下午2:51:39 
    */ 
    private String uploadFileName;
    
    public FileModel() {
        super();
    }
    
    public File getUpload() {
        return upload;
    }
    public void setUpload(File upload) {
        this.upload = upload;
    }
    public String getUploadContentType() {
        return uploadContentType;
    }
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    public String getUploadFileName() {
        return uploadFileName;
    }
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }
}
