/**
 * @项目名称 RealestateRegister
 * @Title UserFilter.java 
 * @Package com.ljdy.realestateRegister.filter 
 * @Description (拦截未登录用户的非法请求) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月5日 下午5:58:47 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
package com.ljdy.BHF.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @ClassName UserFilter 
 * @Description (拦截未登录用户的非法请求) 
 * @Author 李金阳 lijy@luojiadeyi.com
 * @Date 2016年6月5日 下午5:58:47 
 * @修改历史  
 *     1. [2016年6月5日]创建文件 by 李金阳
 */
public class UserFilter implements Filter {

    @Override
    public void destroy() {
        
    }

     /** 
      * @Title doFilter 
      * @Description (已登录用户，放行请求；未登录用户，拦截请求，并跳转到登录页面) 
      * @param request
      * @param response
      * @param chain
      * @throws IOException
      * @throws ServletException 
      * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
      * @Date  2016年6月5日
      * @修改历史  
      *     1. [2016年6月5日]创建文件 by 李金阳 
     **/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        // 获取request、response的真实对象
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        
        // 获取请求路径
        String path = req.getServletPath();

        // 获取请求参数
        String param = req.getQueryString();

         /*
         * 以下情况，放行请求：
         * 1.没有请求资源地址；即默认请求登录页面
         * 2.用户请求的是登录页面
         * 3.用户已经登录
         */
        if ("/user/exitLogin".equals(path)) {
            req.getSession().removeAttribute("loginUser");
            res.sendRedirect(req.getContextPath()+"/login.jsp");
        }else if(path == null || (path.equals("/login.jsp")&&param==null) || req.getSession().getAttribute("loginUser") != null || path.equals("/user/login")
                || path.equals("/js/libs/jquery-1.8.3.min.js") || path.equals("/css/login.css") || path.equals("/css/common.css")
                || path.equals("/img/login_logo.png") || path.equals("/img/form_bottombg.png")
                || path.equals("/img/form_headbg.png") || path.equals("/img/icons1.png")  || path.equals("/img/icons2.png")
                || path.equals("/img/login_user.png")|| path.equals("/img/wel.png") ||path.equals("/img/login_text.png")
                || path.equals("/img/login_btm.png")|| path.equals("/img/login_btn.png")|| path.equals("/img/bg_color.png")
                || path.equals("/img/login_passd.png") || path.equals("/img/www.jpg")||path.equals("/BHF.ico")) {
            chain.doFilter(request, response);
        }else{  // 否则，跳转到登录页面
            res.sendRedirect(req.getContextPath()+"/login.jsp");
        }
        
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        
    }

}
