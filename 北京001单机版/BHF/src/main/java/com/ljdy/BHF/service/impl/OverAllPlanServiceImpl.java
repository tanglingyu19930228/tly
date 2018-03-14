package com.ljdy.BHF.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ljdy.BHF.service.ZqdlService;
/**
 * @ClassName OverAllPlanServiceImpl 
 * @Description (统筹规划service实现类) 
 * @Author 顾冲 guchong@luojiadeyi.com    
 * @Date 2017年9月25日 下午5:05:26 
 * @修改历史  
 *     1. [2017年9月25日]创建文件 by 顾冲
 */
@Service("overAllPlanService")
public class OverAllPlanServiceImpl {
    @Resource
    private ZqdlService zqdlService;
}
