package com.inspur.blockchain.model;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherTypeListBean {

    private List<IssuerListBean> issuer_list;

    public List<IssuerListBean> getIssuer_list() {
        return issuer_list;
    }

    public void setIssuer_list(List<IssuerListBean> issuer_list) {
        this.issuer_list = issuer_list;
    }

    public static class IssuerListBean {
        /**
         * CPT_ID : 2020051414383895946
         * CPT_NAME : 身份证
         * ISSUER_PHONE : 17623047887
         * ISSUER_NAME : sceon
         * ISSUER_USER_ID : 20200107172638595995
         * ISSUER_DESC : 济南市公安局
         * ISSUER_DID : did:ibid:AA429A6662119C4B1F7E1BD003B0C4CB
         * IS_AUTH : 1
         */

        private String CPT_ID;
        private String CPT_NAME;
        private String ISSUER_PHONE;
        private String ISSUER_NAME;
        private String ISSUER_USER_ID;
        private String ISSUER_DESC;
        private String ISSUER_DID;
        private String IS_AUTH;

        public String getCPT_ID() {
            return CPT_ID;
        }

        public void setCPT_ID(String CPT_ID) {
            this.CPT_ID = CPT_ID;
        }

        public String getCPT_NAME() {
            return CPT_NAME;
        }

        public void setCPT_NAME(String CPT_NAME) {
            this.CPT_NAME = CPT_NAME;
        }

        public String getISSUER_PHONE() {
            return ISSUER_PHONE;
        }

        public void setISSUER_PHONE(String ISSUER_PHONE) {
            this.ISSUER_PHONE = ISSUER_PHONE;
        }

        public String getISSUER_NAME() {
            return ISSUER_NAME;
        }

        public void setISSUER_NAME(String ISSUER_NAME) {
            this.ISSUER_NAME = ISSUER_NAME;
        }

        public String getISSUER_USER_ID() {
            return ISSUER_USER_ID;
        }

        public void setISSUER_USER_ID(String ISSUER_USER_ID) {
            this.ISSUER_USER_ID = ISSUER_USER_ID;
        }

        public String getISSUER_DESC() {
            return ISSUER_DESC;
        }

        public void setISSUER_DESC(String ISSUER_DESC) {
            this.ISSUER_DESC = ISSUER_DESC;
        }

        public String getISSUER_DID() {
            return ISSUER_DID;
        }

        public void setISSUER_DID(String ISSUER_DID) {
            this.ISSUER_DID = ISSUER_DID;
        }

        public String getIS_AUTH() {
            return IS_AUTH;
        }

        public void setIS_AUTH(String IS_AUTH) {
            this.IS_AUTH = IS_AUTH;
        }
    }
}
