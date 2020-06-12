package com.inspur.blockchain.model;

import java.util.List;

/**
 * @author lichun
 */
public class VoucherDetailBean {

    /**
     * fieldList : [{"VAL":"北京市东城区景山","FIELD":"ADDR","FIELD_HASH":"223F93F6A1261A8CF8AD6A56C94079B578F8B2A42CCBB3314E905528C78AE19C","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"rKL6o57w"},{"VAL":"19960101","FIELD":"DOB","FIELD_HASH":"F5C833EE9658EC89A83A9D40A15BCB94DF9DC91722C7A2829871228C50DD03BD","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"CmORlDZl"},{"VAL":"199992323232323232","FIELD":"ID_CARD_NO","FIELD_HASH":"BB2B127D0F080E73B07CDCD647B7B5E5BC32E37D1F8B6563AA061F738E4C4B76","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"D18oTgbH"},{"VAL":"周华","FIELD":"NAME","FIELD_HASH":"879284709EEE5BF37E0C199B2ACFD59B3BE799D447F52D209EA35A3E42680271","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"rXVJ47zH"},{"VAL":"汉族","FIELD":"NATION","FIELD_HASH":"AC05461B23B07D9D78834176031D908350530B0A05B21B38AA06C4A415470743","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"omQSHqmR"},{"VAL":"女","FIELD":"SEX","FIELD_HASH":"51A91E1DA50792F8D45BCCB087F111DDF2C575E938AB8F9B57BD6283966F3651","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"SuMv0vea"}]
     * credentialMap : {"ISSUER_NAME":"sceon","ISSUANCE_DATE":"2020-06-03 09:37:44","ISSUER_DESC":"济南市公安局","USER_ID":"20200513093640289326","DID_DESC":"周华的凭证","ENCRYPT_KEY":"045E6B848C4EABABED7960B79ABAB0D3A332E03EC0ECBAC9DA0B168CD82A199778F41E13EF4D6CA20CC7450540BB98EC9A5A91D3733AE23F76D1ECDD5E65636A08C8DEFD617C0FE4B114A18B87F285C4AAD0877A57F7DC13DCF3B2C550303469CBB2E7308C53E24C91","PR_NUM_IDX":"123","CPT_ID":"2020051414383895946","IDENTITY_ID":"FABRIC_20200513093640289326_00_202005130936404890","ISSUER_USERID":"20200107172638595995","ISSUER":"did:ibid:AA429A6662119C4B1F7E1BD003B0C4CB","EXPIRATION_DATE":"2023-06-03 09:37:44","CPT_NAME":"身份证","DID":"did:ibid:9C49F93C8E0385C5F63E66202AA8AED6","NONCE":"qG5LJrgAJivLdk","IS_AUTH":"1"}
     */

    private CredentialMapBean credentialMap;
    private List<FieldListBean> fieldList;

    public CredentialMapBean getCredentialMap() {
        return credentialMap;
    }

    public void setCredentialMap(CredentialMapBean credentialMap) {
        this.credentialMap = credentialMap;
    }

    public List<FieldListBean> getFieldList() {
        return fieldList;
    }

    public void setFieldList(List<FieldListBean> fieldList) {
        this.fieldList = fieldList;
    }

    public static class CredentialMapBean {
        /**
         * ISSUER_NAME : sceon
         * ISSUANCE_DATE : 2020-06-03 09:37:44
         * ISSUER_DESC : 济南市公安局
         * USER_ID : 20200513093640289326
         * DID_DESC : 周华的凭证
         * ENCRYPT_KEY : 045E6B848C4EABABED7960B79ABAB0D3A332E03EC0ECBAC9DA0B168CD82A199778F41E13EF4D6CA20CC7450540BB98EC9A5A91D3733AE23F76D1ECDD5E65636A08C8DEFD617C0FE4B114A18B87F285C4AAD0877A57F7DC13DCF3B2C550303469CBB2E7308C53E24C91
         * PR_NUM_IDX : 123
         * CPT_ID : 2020051414383895946
         * IDENTITY_ID : FABRIC_20200513093640289326_00_202005130936404890
         * ISSUER_USERID : 20200107172638595995
         * ISSUER : did:ibid:AA429A6662119C4B1F7E1BD003B0C4CB
         * EXPIRATION_DATE : 2023-06-03 09:37:44
         * CPT_NAME : 身份证
         * DID : did:ibid:9C49F93C8E0385C5F63E66202AA8AED6
         * NONCE : qG5LJrgAJivLdk
         * IS_AUTH : 1
         */

        private String ISSUER_NAME;
        private String ISSUANCE_DATE;
        private String ISSUER_DESC;
        private String USER_ID;
        private String DID_DESC;
        private String ENCRYPT_KEY;
        private String PR_NUM_IDX;
        private String CPT_ID;
        private String IDENTITY_ID;
        private String ISSUER_USERID;
        private String ISSUER;
        private String EXPIRATION_DATE;
        private String CPT_NAME;
        private String DID;
        private String NONCE;
        private String IS_AUTH;

        public String getISSUER_NAME() {
            return ISSUER_NAME;
        }

        public void setISSUER_NAME(String ISSUER_NAME) {
            this.ISSUER_NAME = ISSUER_NAME;
        }

        public String getISSUANCE_DATE() {
            return ISSUANCE_DATE;
        }

        public void setISSUANCE_DATE(String ISSUANCE_DATE) {
            this.ISSUANCE_DATE = ISSUANCE_DATE;
        }

        public String getISSUER_DESC() {
            return ISSUER_DESC;
        }

        public void setISSUER_DESC(String ISSUER_DESC) {
            this.ISSUER_DESC = ISSUER_DESC;
        }

        public String getUSER_ID() {
            return USER_ID;
        }

        public void setUSER_ID(String USER_ID) {
            this.USER_ID = USER_ID;
        }

        public String getDID_DESC() {
            return DID_DESC;
        }

        public void setDID_DESC(String DID_DESC) {
            this.DID_DESC = DID_DESC;
        }

        public String getENCRYPT_KEY() {
            return ENCRYPT_KEY;
        }

        public void setENCRYPT_KEY(String ENCRYPT_KEY) {
            this.ENCRYPT_KEY = ENCRYPT_KEY;
        }

        public String getPR_NUM_IDX() {
            return PR_NUM_IDX;
        }

        public void setPR_NUM_IDX(String PR_NUM_IDX) {
            this.PR_NUM_IDX = PR_NUM_IDX;
        }

        public String getCPT_ID() {
            return CPT_ID;
        }

        public void setCPT_ID(String CPT_ID) {
            this.CPT_ID = CPT_ID;
        }

        public String getIDENTITY_ID() {
            return IDENTITY_ID;
        }

        public void setIDENTITY_ID(String IDENTITY_ID) {
            this.IDENTITY_ID = IDENTITY_ID;
        }

        public String getISSUER_USERID() {
            return ISSUER_USERID;
        }

        public void setISSUER_USERID(String ISSUER_USERID) {
            this.ISSUER_USERID = ISSUER_USERID;
        }

        public String getISSUER() {
            return ISSUER;
        }

        public void setISSUER(String ISSUER) {
            this.ISSUER = ISSUER;
        }

        public String getEXPIRATION_DATE() {
            return EXPIRATION_DATE;
        }

        public void setEXPIRATION_DATE(String EXPIRATION_DATE) {
            this.EXPIRATION_DATE = EXPIRATION_DATE;
        }

        public String getCPT_NAME() {
            return CPT_NAME;
        }

        public void setCPT_NAME(String CPT_NAME) {
            this.CPT_NAME = CPT_NAME;
        }

        public String getDID() {
            return DID;
        }

        public void setDID(String DID) {
            this.DID = DID;
        }

        public String getNONCE() {
            return NONCE;
        }

        public void setNONCE(String NONCE) {
            this.NONCE = NONCE;
        }

        public String getIS_AUTH() {
            return IS_AUTH;
        }

        public void setIS_AUTH(String IS_AUTH) {
            this.IS_AUTH = IS_AUTH;
        }
    }

    public static class FieldListBean {
        /**
         * VAL : 北京市东城区景山
         * FIELD : ADDR
         * FIELD_HASH : 223F93F6A1261A8CF8AD6A56C94079B578F8B2A42CCBB3314E905528C78AE19C
         * DID : did:ibid:9C49F93C8E0385C5F63E66202AA8AED6
         * NONCE : rKL6o57w
         */

        private String VAL;
        private String FIELD;
        private String FIELD_HASH;
        private String DID;
        private String NONCE;

        public String getVAL() {
            return VAL;
        }

        public void setVAL(String VAL) {
            this.VAL = VAL;
        }

        public String getFIELD() {
            return FIELD;
        }

        public void setFIELD(String FIELD) {
            this.FIELD = FIELD;
        }

        public String getFIELD_HASH() {
            return FIELD_HASH;
        }

        public void setFIELD_HASH(String FIELD_HASH) {
            this.FIELD_HASH = FIELD_HASH;
        }

        public String getDID() {
            return DID;
        }

        public void setDID(String DID) {
            this.DID = DID;
        }

        public String getNONCE() {
            return NONCE;
        }

        public void setNONCE(String NONCE) {
            this.NONCE = NONCE;
        }
    }
}
