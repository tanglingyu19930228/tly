/**
 * @项目名称 WebTest
 * @Title PageBean.java 
 * @Package com.ljdy.realestateRegister.model 
 * @Description (分页组件实体类) 
 * @Author 徐成
 * @Date 2016年11月17日 上午11:50:35  
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年9月19日]创建文件 by 徐成
 */
package com.ljdy.BHF.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
 * @ClassName PageBean 
 * @Description (分页组件) 
 * @Author 徐成  ljdy593@luojiadeyi.com
 * @Date 2017年9月26日 下午13:18
 * @修改历史  
 *     1. [2017年9月26日]创建文件 by 徐成
*/
public class PageBean implements Serializable{

    private static final long serialVersionUID = 8470697978259453214L;
    
    // 指定的或是页面参数
    private int currentPage=0; // 当前页
    private int numPerPage; // 每页显示多少条

    // 查询数据库
    private int totalCount=0; // 总记录数
    private List<Object> recordList; // 本页的数据列表

    // 计算
    private int pageCount=0; // 总页数
    private int beginPageIndex; // 页码列表的开始索引（包含）
    private int endPageIndex; // 页码列表的结束索引（包含）
    
    private Map<String, Object> countResultMap; // 当前分页条件下的统计结果
    
    
    public PageBean(){}
    
    /**
     * 只接受前4个必要的属性，会自动的计算出其他3个属生的值
     * 
     * @param currentPage
     * @param pageSize
     * @param totalCount
     * @param recordList
     */
    public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;

        // 计算总页码
        pageCount = (totalCount + numPerPage - 1) / numPerPage;

        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }
    
    /**
     * 只接受前5个必要的属性，会自动的计算出其他3个属生的值
     * 
     * @param currentPage
     * @param pageSize
     * @param totalCount
     * @param recordList
     */
    public PageBean(int currentPage, int numPerPage, int totalCount, List<Object> recordList, Map<String, Object> countResultMap) {
        this.currentPage = currentPage;
        this.numPerPage = numPerPage;
        this.totalCount = totalCount;
        this.recordList = recordList;
        this.countResultMap = countResultMap;

        // 计算总页码
        pageCount = (totalCount + numPerPage - 1) / numPerPage;

        // 计算 beginPageIndex 和 endPageIndex
        // >> 总页数不多于10页，则全部显示
        if (pageCount <= 10) {
            beginPageIndex = 1;
            endPageIndex = pageCount;
        }
        // >> 总页数多于10页，则显示当前页附近的共10个页码
        else {
            // 当前页附近的共10个页码（前4个 + 当前页 + 后5个）
            beginPageIndex = currentPage - 4;
            endPageIndex = currentPage + 5;
            // 当前面的页码不足4个时，则显示前10个页码
            if (beginPageIndex < 1) {
                beginPageIndex = 1;
                endPageIndex = 10;
            }
            // 当后面的页码不足5个时，则显示后10个页码
            if (endPageIndex > pageCount) {
                endPageIndex = pageCount;
                beginPageIndex = pageCount - 10 + 1;
            }
        }
    }

    public List<Object> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<Object> recordList) {
        this.recordList = recordList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getBeginPageIndex() {
        return beginPageIndex;
    }

    public void setBeginPageIndex(int beginPageIndex) {
        this.beginPageIndex = beginPageIndex;
    }

    public int getEndPageIndex() {
        return endPageIndex;
    }

    public void setEndPageIndex(int endPageIndex) {
        this.endPageIndex = endPageIndex;
    }

    public Map<String, Object> getCountResultMap() {
        return countResultMap;
    }

    public void setCountResultMap(Map<String, Object> countResultMap) {
        this.countResultMap = countResultMap;
    }

}
