package com.nantian.atom.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nantian.atom.po.Opt;
import com.nantian.atom.service.CommonService;

@Service("optServiceImpl")
public class OptServiceImpl extends CommonService {

	public List<Integer> findTransIdsByOpt(List<Integer> optIds) {
		List<Integer> list = new ArrayList<Integer>();
		if(optIds==null){
			return null;
		}
		for (Integer integer : optIds) {
			if(this.getPO(Opt.class, integer)!=null){
				Opt opt=getPO(Opt.class, integer);
				if(opt.getOptServerId()!=null){
					list.add(opt.getOptServerId());
				}
				
			}
		}
		HashSet<Integer> set = new HashSet<Integer>(list);
		list.clear();
		list.addAll(set);
		return list;
	}
	
	public List<Opt> findOptsByIds(List<Integer> ids){
		List<Opt> list = new ArrayList<Opt>();
		if(ids==null){
			return null;
		}
		for (Integer key : ids) {
			Opt opt = this.getPO(Opt.class, key);
			if(opt!=null){
				list.add(opt);
			}else{
				log.error("未找到key为["+key+"]的OPT");
			}
		}
		return list;
	}
}
