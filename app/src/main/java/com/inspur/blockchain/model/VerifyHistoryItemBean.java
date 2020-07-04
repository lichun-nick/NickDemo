package com.inspur.blockchain.model;

import java.util.List;

/**
 * @author lichun
 */
public class VerifyHistoryItemBean {

    private String trace_date;
    private String trace_time;
    private String did;

    public String getTrace_date() {
        return trace_date;
    }

    public void setTrace_date(String trace_date) {
        this.trace_date = trace_date;
    }

    public String getTrace_time() {
        return trace_time;
    }

    public void setTrace_time(String trace_time) {
        this.trace_time = trace_time;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
