package com.inspur.blockchain.model;


/**
 * @author lichun
 * 首页数据
 */
public class MainInfoBean {


    /**
     * valid : {"valid_max":62,"did":"did:ibid:1817ED48E51B67D945EB508BDBF3C60C"}
     * count : {"valid_dids":129,"user_id":"20200107155930030876","did_counts":25}
     * recent : {"trace_time":"2020-06-04 16:31:42","verifier_id":"20200107155930030876","verifier_props":"{id_card_no,nation,dob,sex,name,add}","nick_name":"rrdmcw","tx_hash":"183b8d3d8042c0102d587910846c7838b177dc5a6ff1f47687fbfbbc3720ac2a","did":"did:ibid:D97E49EFA84AA5DC9835063D496D497E","key":"20200107155930030876"}
     */
    private String token;
    private ValidBean valid;
    private CountBean count;
    private RecentBean recent;

    public ValidBean getValid() {
        return valid;
    }

    public void setValid(ValidBean valid) {
        this.valid = valid;
    }

    public CountBean getCount() {
        return count;
    }

    public void setCount(CountBean count) {
        this.count = count;
    }

    public RecentBean getRecent() {
        return recent;
    }

    public void setRecent(RecentBean recent) {
        this.recent = recent;
    }

    public static class ValidBean {
        /**
         * valid_max : 62
         * did : did:ibid:1817ED48E51B67D945EB508BDBF3C60C
         */
        private int valid_max;
        private String did;

        public int getValid_max() {
            return valid_max;
        }

        public void setValid_max(int valid_max) {
            this.valid_max = valid_max;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }
    }

    public static class CountBean {
        /**
         * valid_dids : 129
         * user_id : 20200107155930030876
         * did_counts : 25
         */
        private int valid_dids;
        private String user_id;
        private int did_counts;

        public int getValid_dids() {
            return valid_dids;
        }

        public void setValid_dids(int valid_dids) {
            this.valid_dids = valid_dids;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public int getDid_counts() {
            return did_counts;
        }

        public void setDid_counts(int did_counts) {
            this.did_counts = did_counts;
        }
    }

    public static class RecentBean {
        /**
         * trace_time : 2020-06-04 16:31:42
         * verifier_id : 20200107155930030876
         * verifier_props : {id_card_no,nation,dob,sex,name,add}
         * nick_name : rrdmcw
         * tx_hash : 183b8d3d8042c0102d587910846c7838b177dc5a6ff1f47687fbfbbc3720ac2a
         * did : did:ibid:D97E49EFA84AA5DC9835063D496D497E
         * key : 20200107155930030876
         */

        private String trace_time;
        private String verifier_id;
        private String verifier_props;
        private String nick_name;
        private String tx_hash;
        private String did;
        private String key;

        public String getTrace_time() {
            return trace_time;
        }

        public void setTrace_time(String trace_time) {
            this.trace_time = trace_time;
        }

        public String getVerifier_id() {
            return verifier_id;
        }

        public void setVerifier_id(String verifier_id) {
            this.verifier_id = verifier_id;
        }

        public String getVerifier_props() {
            return verifier_props;
        }

        public void setVerifier_props(String verifier_props) {
            this.verifier_props = verifier_props;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getTx_hash() {
            return tx_hash;
        }

        public void setTx_hash(String tx_hash) {
            this.tx_hash = tx_hash;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
