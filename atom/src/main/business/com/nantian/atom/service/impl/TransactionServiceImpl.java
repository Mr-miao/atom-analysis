package com.nantian.atom.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nantian.atom.po.Transaction;
import com.nantian.atom.service.CommonService;

@Service("transactionServiceImpl")
public class TransactionServiceImpl  extends CommonService {

	public List<Transaction> findTransByIds(List<Integer> ids){
		if(ids==null){
			return null;
		}
		List<Transaction> list = new ArrayList<Transaction>();
		for (Integer integer : ids) {
			if(this.getPO(Transaction.class, integer)!=null){
				Transaction tran=getPO(Transaction.class, integer);
				list.add(tran);
			}
		}
		HashSet<Transaction> set = new HashSet<Transaction>(list);
		list.clear();
		list.addAll(set);
		return list;
	}
	

}
