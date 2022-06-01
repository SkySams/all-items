package org.example.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
import org.example.service.impl.AsyncDispatcherServletListener;
import org.springframework.web.servlet.DispatcherServlet;
import sun.rmi.runtime.Log;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author: zyh
 * @date: 2022/6/1
 */
@Slf4j
@WebServlet(urlPatterns = {"/start/*"}, asyncSupported = true, name = "start")
public class AsyncDispatcherServlet extends DispatcherServlet {
    private ExecutorService exececutor;
    private static final int NUM_ASYNC_TASKS = 15;
    private static final long TIME_OUT = 10 * 1000;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        exececutor = Executors.newFixedThreadPool(NUM_ASYNC_TASKS);
    }

    @Override
    public void destroy() {
        exececutor.shutdownNow();
        super.destroy();
    }

    @Override
    protected void doDispatch(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        final AsyncContext ac = request.startAsync(request, response);
        ac.setTimeout(TIME_OUT);
        FutureTask task = new FutureTask(new Runnable() {

            @Override
            public void run() {
                try {
                    log.debug("Dispatching request " + request);
                    AsyncDispatcherServlet.super.doDispatch(request, response);
                    log.debug("doDispatch returned from processing request " + request);
                    ac.complete();
                } catch (Exception ex) {
                    log.error("Error in async request", ex);
                }
            }
        }, null);

        ac.addListener(new AsyncDispatcherServletListener(task));
        exececutor.execute(task);
    }
}