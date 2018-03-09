package com.nantian.util.str;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class VOPOPaserUtil {
	
	
	/**
	 * 将PO转换为VO
	 * @param poList
	 * @return
	 */
	public static <VO, PO> List<VO> PO2VO(List<PO> poList, Class<PO> poCls, Class<VO> voCls) throws Exception {
		if(poList!=null && !poList.isEmpty()){
			List<VO> listVo = new ArrayList<VO>();
			try {
				Method method = voCls.getDeclaredMethod("po2vo", poCls); 
				for(PO po : poList){
					listVo.add((VO)method.invoke(null, po));
				}
				return listVo;
			} catch (Exception e) {
				throw e;
			}
		}else{
			return null;
		}
	}
	
	/**
	 * 将PO转换为VO
	 * @param poList
	 * @return
	 */
	public static <VO, PO> VO PO2VO(PO po, Class<PO> poCls, Class<VO> voCls) throws Exception {
		if(po!=null){
			VO vo = null;
			try {
				Method method = voCls.getDeclaredMethod("po2vo", poCls); 
				vo = (VO)method.invoke(null, po);
				return vo;
			} catch (Exception e) {
				throw new Exception("PO转VO出错");
			}
		}else{
			return null;
		}
	}
}
