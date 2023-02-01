package com.example.basicspring.entity.dto;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
public class PayOrderApply {

    private String sn;
    private Long amount;
    private String proCode;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    @Override
    public String toString() {
        return "PayOrderApply{" +
                "sn='" + sn + '\'' +
                ", amount=" + amount +
                ", proCode='" + proCode + '\'' +
                '}';
    }
}