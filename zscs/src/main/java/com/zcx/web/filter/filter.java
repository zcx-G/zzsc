package com.zcx.web.filter;


import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")

public class filter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        req.setCharacterEncoding("utf-8");
        //放行的资源路径
        String[] urls= new String[]{
                "/backend/",
                "/front/",
                "/user/",
                "/root/",
                "/plugins/"
        };
        String url = req.getRequestURI();
        for (String u : urls) {
            if (url.contains(u)){
                chain.doFilter(req, res);
               return;
            }
        }

        HttpSession session = req.getSession();
        Object root = session.getAttribute("root");
        Object user = session.getAttribute("user");


        System.out.println(root);
        System.out.println(user);

        if (root != null){
            chain.doFilter(req, res);
            return;
        }


        if (user != null){
            chain.doFilter(req, res);
        }
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
