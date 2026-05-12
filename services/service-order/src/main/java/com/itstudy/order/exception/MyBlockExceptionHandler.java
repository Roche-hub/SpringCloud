package com.itstudy.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itstudy.common.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                       String s, BlockException e) throws Exception {



        PrintWriter writer = httpServletResponse.getWriter();
        httpServletResponse.setContentType("application/json;charset=utf-8");

        R error = R.error(599, s + "被Sentinel限制了，原因是：" + e.getClass());

        String json = objectMapper.writeValueAsString(error);

        writer.write(json);
    }
}
