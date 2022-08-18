package com.jiyong.commerce.common.util.logtrace;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogTrace {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>();
    private ThreadLocal<String> spaceHolder = new ThreadLocal<>();


    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }
        return sb.toString();
    }

    public TraceStatus begin(String message, Object[] args) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();
        Long startTimeMs = System.currentTimeMillis();
        String space = addSpace(START_PREFIX, traceId.getLevel());
        spaceHolder.set(space);
        log.info("[{}] {}{}  Target Parameter = {}", traceId.getId(), space, message, args);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    public TraceStatus keep(TraceStatus status, String message) {
        TraceId traceId = status.getTraceId();
        String space = spaceHolder.get();
        log.info("[{}] {}{}{} ", traceId.getId(), space, status.getMessage(), message);
        return status;
    }


    public void end(TraceStatus status, String message) {
        complete(status, null, message);
    }


    public void exception(TraceStatus status, Exception e, String message) {
        complete(status, e, message);
    }

    private void complete(TraceStatus status, Exception e, String message) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms return = {}", traceId.getId(), addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, message);
        } else {
            log.info("[{}] {}{} time={}ms ex={} return = {}", traceId.getId(), addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs, e.toString(), message);
        }
        releaseTraceId();

    }


    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId == null) {
            traceIdHolder.set(new TraceId());
        } else {
            traceIdHolder.set(traceId.createNextId());
        }
    }


    private void releaseTraceId() {
        TraceId traceId = traceIdHolder.get();
        if (traceId.isFirstLevel()) {
            traceIdHolder.remove();//destroy
            spaceHolder.remove();
        } else {
            TraceId previousId = traceId.createPreviousId();
            traceIdHolder.set(previousId);
            spaceHolder.set(addSpace(START_PREFIX, previousId.getLevel()));
        }
    }
}
