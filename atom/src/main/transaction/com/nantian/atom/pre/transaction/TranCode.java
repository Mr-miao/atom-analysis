package com.nantian.atom.pre.transaction;

/**
 * 交易码枚举
 * @author 李建兴
 *
 */
public enum TranCode {
	
	//状态上送
	AM001("AM001"),
	//主动更新
	AM002("AM002"),
	//被动更新（服务端发起更新通知）
	MA001("MA001"),
	//软件更新返回（获取更新列表）
	AM003("AM003"),
	//被动更新结果返回
	AM004("AM004"),
	//媒体更新
	MA002("MA002"),
	//获取二级服务器地址
	AM005("AM005"),
	//消息通知（服务端主动通知）
	MA003("MA003"),
	//更新终端主密钥
	MA004("MA004"),
	//消息通知（Agent返回）
	AM006("AM006"),
	//媒体更新结果返回
	AM007("AM007"),
	//设备自动发现信息
	AM008("AM008"),
	//工作密钥交换
	AM009("AM009"),
	//工作密钥存储结果反馈
	AM010("AM010"),
	//主密钥存储结果反馈
	AM011("AM011");
	
	private String tranCode;
	
	private TranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	
	@Override
	public String toString() {
		return tranCode;
	}
}
