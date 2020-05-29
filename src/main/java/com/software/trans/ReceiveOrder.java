package com.software.trans;

import java.util.List;

import com.software.domain.SaleorderCommon;
import com.software.domain.SaleorderItem;

public class ReceiveOrder 
{
	private String commontablename;
	private String itemtablename;
	private String warehoursedetailtablename;
	private String itemtopricetable;
	private String orderid;
	private String viceid;
	private String warehourseid;
	private String warehoursename;
	private String clientid;
	private String clientname;
	private String principalid;
	private String principalname;
	private String itemid;
	private String itemnum;
	private String itemname;
	private String perprice;
	private String sumprice;
	private String gather;
	private String change;
	private String margin;
	private String createtime;
	private String checktime;
	private String gathertime;
	private String returntime;
	private String postime;
	private String ordersumprice;
	private String status;
	private String type;
	private String exception;
	private String note;
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getViceid() {
		return viceid;
	}
	public void setViceid(String viceid) {
		this.viceid = viceid;
	}
	public String getWarehourseid() {
		return warehourseid;
	}
	public void setWarehourseid(String warehourseid) {
		this.warehourseid = warehourseid;
	}
	public String getWarehoursename() {
		return warehoursename;
	}
	public void setWarehoursename(String warehoursename) {
		this.warehoursename = warehoursename;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getPrincipalid() {
		return principalid;
	}
	public void setPrincipalid(String principalid) {
		this.principalid = principalid;
	}
	public String getPrincipalname() {
		return principalname;
	}
	public void setPrincipalname(String principalname) {
		this.principalname = principalname;
	}
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public String getItemnum() {
		return itemnum;
	}
	public void setItemnum(String itemnum) {
		this.itemnum = itemnum;
	}
	public String getPerprice() {
		return perprice;
	}
	public void setPerprice(String perprice) {
		this.perprice = perprice;
	}
	public String getSumprice() {
		return sumprice;
	}
	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}
	public String getGather() {
		return gather;
	}
	public void setGather(String gather) {
		this.gather = gather;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getChecktime() {
		return checktime;
	}
	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}
	public String getGathertime() {
		return gathertime;
	}
	public void setGathertime(String gathertime) {
		this.gathertime = gathertime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public String getPostime() {
		return postime;
	}
	public void setPostime(String postime) {
		this.postime = postime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCommontablename() {
		return commontablename;
	}
	public void setCommontablename(String commontablename) {
		this.commontablename = commontablename;
	}
	public String getItemtablename() {
		return itemtablename;
	}
	public void setItemtablename(String itemtablename) {
		this.itemtablename = itemtablename;
	}
	
	public String getWarehoursedetailtablename() {
		return warehoursedetailtablename;
	}
	public void setWarehoursedetailtablename(String warehoursedetailtablename) {
		this.warehoursedetailtablename = warehoursedetailtablename;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	
	
	public String getItemtopricetable() {
		return itemtopricetable;
	}
	public void setItemtopricetable(String itemtopricetable) {
		this.itemtopricetable = itemtopricetable;
	}
	public String getOrdersumprice() {
		return ordersumprice;
	}
	public void setOrdersumprice(String ordersumprice) {
		this.ordersumprice = ordersumprice;
	}

	
	public SaleorderCommon toCommon()
	{
		SaleorderCommon common = new SaleorderCommon();
		common.setTablename(this.commontablename);
		if (!this.orderid.equals("")) 
		{
			if (Integer.valueOf(this.orderid)==0) {
				common.setId(-1);
			}else{
				common.setId(Integer.valueOf(this.orderid));
			}
		}
		common.setWarehourseid(Integer.valueOf(this.warehourseid));
		common.setWarehoursename(this.warehoursename);
		if (!this.clientid.equals("")) 
		{
			System.out.println("i am here");
			if (Integer.valueOf(this.clientid)==0) {
				common.setClientid(-1);
			}else{
				common.setClientid(Integer.valueOf(this.clientid));
			}
		}
		common.setClientname(this.clientname);
		common.setPrincipalid(this.principalid);
		common.setPrincipalname(this.principalname);
		common.setCreatetime(this.createtime);
		common.setChecktime(this.checktime);
		common.setGathertime(this.gathertime);
		common.setReturntime(this.returntime);
		common.setPostime(this.postime);
		if (!this.status.equals("")) 
		{
			common.setStatus(Integer.valueOf(this.status));
		}
		if (!this.sumprice.equals("")) 
		{
			common.setSumprice(Float.valueOf(this.ordersumprice));
		}
		if (!this.gather.equals("")) 
		{
			common.setGather(Float.valueOf(this.gather));
		}
		if (!this.change.equals("")) 
		{
			common.setChange(Float.valueOf(this.change));
		}
		if (!this.margin.equals("")) 
		{
			common.setMargin(Float.valueOf(this.margin));
		}
		common.setType(this.type);
		common.setNote(this.note);
		common.setException(this.exception);
		System.out.println(common);
		return common;
	}
	
	public SaleorderItem toItem()
	{
		SaleorderItem item = new SaleorderItem();
		item.setTablename(this.itemtablename);
		if (!this.orderid.equals("")) 
		{
			item.setId(Integer.valueOf(this.orderid));
		}
		if (!this.viceid.equals("")) 
		{
			item.setViceid(Integer.valueOf(this.viceid));
		}
		if (!this.itemid.equals("")) 
		{
			item.setItemid(Integer.valueOf(this.itemid));
		}
		item.setItemname(this.itemname);
		if (!this.itemnum.equals("")) 
		{
			item.setItemnum(Integer.valueOf(this.itemnum));
		}
		if (!this.perprice.equals("")) 
		{
			item.setPerprice(Float.valueOf(this.perprice));
		}
		if (!this.sumprice.equals("")) 
		{
			item.setSumprice(Float.valueOf(this.sumprice));
		}
		item.setPricetype(this.type);
		return item;
	}
    
	public void fillTablename()
	{
		String id = String.format("%04d", Integer.valueOf(this.warehourseid));
		this.commontablename = "sub_saleorder_common_"+id;
		this.itemtablename	 =	"sub_saleorder_item_"+id;
		this.warehoursedetailtablename = "sub_warehourse_detail_"+id;
		this.itemtopricetable = "sub_warehourse_itemtoprice_"+id;
	}
	
	@Override
	public String toString() {
		return "ReceiveOrder [commontablename=" + commontablename + ", itemtablename=" + itemtablename
				+ ", warehoursedetailtablename=" + warehoursedetailtablename + ", itemtopricetable=" + itemtopricetable
				+ ", orderid=" + orderid + ", viceid=" + viceid + ", warehourseid=" + warehourseid + ", warehoursename="
				+ warehoursename + ", clientid=" + clientid + ", clientname=" + clientname + ", principalid="
				+ principalid + ", principalname=" + principalname + ", itemid=" + itemid + ", itemnum=" + itemnum
				+ ", itemname=" + itemname + ", perprice=" + perprice + ", sumprice=" + sumprice + ", gather=" + gather
				+ ", change=" + change + ", margin=" + margin + ", createtime=" + createtime + ", checktime="
				+ checktime + ", gathertime=" + gathertime + ", returntime=" + returntime + ", postime=" + postime
				+ ", ordersumprice=" + ordersumprice + ", status=" + status + ", type=" + type + ", exception="
				+ exception + ", note=" + note + "]";
	}
}
