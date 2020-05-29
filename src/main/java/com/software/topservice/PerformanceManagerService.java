package com.software.topservice;

import java.util.List;

import com.software.domain.Warehourse;
import com.software.trans.WarehoursePerformance;

public interface PerformanceManagerService 
{
	public WarehoursePerformance calWarehoursePerformance(WarehoursePerformance record);

	public List<WarehoursePerformance> calStaffPerformance(WarehoursePerformance record);
}
