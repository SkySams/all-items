package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.FutureTask;

/**
 * @author: zyh
 * @date: 2022/6/1
 */
@Slf4j
public class AsyncDispatcherServletListener implements AsyncListener {
    private FutureTask futureTask;

    public AsyncDispatcherServletListener(FutureTask futureTask) {
        this.futureTask = futureTask;
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        log.warn("Async request did not complete timeout occured");
        handleTimeoutOrError(event, "Request timed out");
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        log.debug("Completed async request");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        log.error("Error in async request", event.getThrowable());
        handleTimeoutOrError(event, "Error processing " + event.getThrowable().getMessage());
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        log.debug("Async Event started..");
    }

    private void handleTimeoutOrError(AsyncEvent event, String message) {
        PrintWriter writer = null;
        try {
            futureTask.cancel(true);
            HttpServletResponse response = (HttpServletResponse) event.getAsyncContext().getResponse();
            //HttpServletRequest request = (HttpServletRequest) event.getAsyncContext().getRequest();
            //request.getRequestDispatcher("/app/error.htm").forward(request, response);
            writer = response.getWriter();
            writer.print(message);
            writer.flush();
        } catch (IOException ex) {
            log.error(String.valueOf(ex));
        } finally {
            event.getAsyncContext().complete();
            if (writer != null) {
                writer.close();
            }
        }
    }
}
