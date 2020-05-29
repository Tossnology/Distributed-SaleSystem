package com.software.domain;

public class Supplier {
    /**
     * 编号 id
     */
    private Integer id;

    /**
     * 公司名字 name
     */
    private String name;

    /**
     * 公司地址 address
     */
    private String address;

    /**
     * 负责人名字 principalName
     */
    private String principalname;

    /**
     * 汇款账号 account
     */
    private String account;

    /**
     * 更新时间 time
     */
    private String time;

    /**
     * 标志 label
     */
    private String label;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPrincipalname() {
        return principalname;
    }

    public void setPrincipalname(String principalname) {
        this.principalname = principalname == null ? null : principalname.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }
}