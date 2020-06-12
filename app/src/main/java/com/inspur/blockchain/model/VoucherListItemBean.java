package com.inspur.blockchain.model;

/**
 * @author lichun
 */
public class VoucherListItemBean {


    /**
     * issuance_date : 2020-06-03 09:37:44
     * cpt_id : 2020051414383895946
     * did_desc : 周华的凭证
     * encrypt_key : 045E6B848C4EABABED7960B79ABAB0D3A332E03EC0ECBAC9DA0B168CD82A199778F41E13EF4D6CA20CC7450540BB98EC9A5A91D3733AE23F76D1ECDD5E65636A08C8DEFD617C0FE4B114A18B87F285C4AAD0877A57F7DC13DCF3B2C550303469CBB2E7308C53E24C91
     * expiration_date : 2023-06-03 09:37:44
     * pr_num_idx : 123
     * cpt_name : 身份证
     * issuer_desc : 济南市公安局
     * nonce : qG5LJrgAJivLdk
     * issuer : did:ibid:AA429A6662119C4B1F7E1BD003B0C4CB
     * did : did:ibid:9C49F93C8E0385C5F63E66202AA8AED6
     */

    private String issuance_date;
    private String cpt_id;
    private String did_desc;
    private String encrypt_key;
    private String expiration_date;
    private String pr_num_idx;
    private String cpt_name;
    private String issuer_desc;
    private String nonce;
    private String issuer;
    private String did;

    public String getIssuance_date() {
        return issuance_date;
    }

    public void setIssuance_date(String issuance_date) {
        this.issuance_date = issuance_date;
    }

    public String getCpt_id() {
        return cpt_id;
    }

    public void setCpt_id(String cpt_id) {
        this.cpt_id = cpt_id;
    }

    public String getDid_desc() {
        return did_desc;
    }

    public void setDid_desc(String did_desc) {
        this.did_desc = did_desc;
    }

    public String getEncrypt_key() {
        return encrypt_key;
    }

    public void setEncrypt_key(String encrypt_key) {
        this.encrypt_key = encrypt_key;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getPr_num_idx() {
        return pr_num_idx;
    }

    public void setPr_num_idx(String pr_num_idx) {
        this.pr_num_idx = pr_num_idx;
    }

    public String getCpt_name() {
        return cpt_name;
    }

    public void setCpt_name(String cpt_name) {
        this.cpt_name = cpt_name;
    }

    public String getIssuer_desc() {
        return issuer_desc;
    }

    public void setIssuer_desc(String issuer_desc) {
        this.issuer_desc = issuer_desc;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
