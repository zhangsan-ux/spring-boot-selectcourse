package com.personal.core.common;

import com.personal.core.constant.CommonConstant;
import org.slf4j.MDC;

/**
 * @author cgc6828
 */
public abstract class AbstractTrackRunnable implements Runnable {

    private String trackId = MDC.get(CommonConstant.THREAD_ID);

    @Override
    public void run() {

        try {
            MDC.put(CommonConstant.THREAD_ID, trackId);
            trackRun();
        } finally {
            MDC.remove(CommonConstant.THREAD_ID);
        }
    }

    /**
     * 运行
     */
    protected abstract void trackRun();
}
