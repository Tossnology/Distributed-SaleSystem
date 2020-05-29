//package com.software.topservice;
//
//import java.util.List;
//
//import com.software.domain.ItemDetail;
//
//public interface WarehourseDetailManagerService 
//{    
//	/**
//	 * 规则 如果仓库是任意的话，在itemComeFromName 字段写入“任意”
//	 * 		如果是子仓库，则写入 “x号仓库” x为仓库编号
//	 * 		如果是总仓库，则写入“总仓库”
//	 * 其他字段招填无误
//	 * @param record
//	 * @return
//	 */
//    public List<ItemDetail> select(ItemDetail record);
//    
//    /**
//     * 需要商品 id，商品更新后数量，所属仓库的id，以及名称
//     * @param record
//     */
//    public void updateByPrimaryKeySelective(ItemDetail record);   
//    
//    public List<String> typeMenu();
//}
