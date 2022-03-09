package org.example.config;


import com.xiaoleilu.hutool.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * @author yaolixiang
 * @date 2019.04.12
 */
@Component
public class RepeatedlyReadFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RepeatedlyReadFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("复制request.getInputStream流");

        AppHttpServeletResponseWrapper responseWrapper = new AppHttpServeletResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);
        this.updateResponseParams(request,response,responseWrapper);
    }

    @Override
    public void destroy() {

    }
    /**
     * 更改response里的数据
     *
     * @param request
     * @param response
     * @param responseWrapper
     * @throws IOException
     */
    private void updateResponseParams(ServletRequest request,ServletResponse response,AppHttpServeletResponseWrapper responseWrapper) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String apiversion = httpServletRequest.getHeader("apiversion");
        String platform = httpServletRequest.getHeader("platform");
        logger.info("版本号: {}", apiversion);
        logger.info("platform: {}", platform);

        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");

        // 这里只拦截返回，直接让请求过去，如果在请求前有处理，可以在这里处理
        byte[] content = responseWrapper.getContent();
        String responseParams = new String(content, "UTF-8");

        // 压缩数据
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(bout);
        logger.info("请求uri: {}",httpServletRequest.getRequestURI());
        logger.info("无版本更改：{}", responseParams);

        if (StrUtil.isNotEmpty(responseParams)){
            gzipOut.write(responseParams.trim().getBytes());
            gzipOut.flush();
            gzipOut.close();
            logger.info("响应状态：{}", httpServletResponse.getStatus());
            httpServletResponse.setHeader("Content-Encoding", "gzip");
            httpServletResponse.getOutputStream().write(bout.toByteArray());
        }
    }


    private class AppHttpServeletResponseWrapper extends HttpServletResponseWrapper {
        private ByteArrayOutputStream buffer;

        private ServletOutputStream out;

        public AppHttpServeletResponseWrapper(HttpServletResponse httpServletResponse) {
            super(httpServletResponse);
            buffer = new ByteArrayOutputStream();
            out = new WrapperOutputStream(buffer);
        }

        @Override
        public ServletOutputStream getOutputStream()
                throws IOException {
            return out;
        }

        @Override
        public void flushBuffer()
                throws IOException {
            if (out != null) {
                out.flush();
            }
        }

        public byte[] getContent()
                throws IOException {
            flushBuffer();
            return buffer.toByteArray();
        }

        class WrapperOutputStream extends ServletOutputStream {
            private ByteArrayOutputStream bos;

            public WrapperOutputStream(ByteArrayOutputStream bos) {
                this.bos = bos;
            }

            @Override
            public void write(int b)
                    throws IOException {
                bos.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener arg0) {
            }
        }
    }



}
