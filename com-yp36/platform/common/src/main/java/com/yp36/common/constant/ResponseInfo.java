package com.yp36.common.constant;

public enum ResponseInfo {

	SUCCESS(200, "success"),
	SERVERERROR(5000, "service error"),
	COMMONERROR(4000, "system error"),
	NOAUTH(4001, "no authorization"),
	URLERROR(4002, "unkown url"),
	FORBIDDEN(4003,"access forbidden"),
	BADREQUEST(4004,"bad request"),
	AUTHFAILED(4005,"authorization failed"),
	METHODERROR(4006,"http method error"),
	PARAMERROR(4007,"parameter error"),
	APIERROR(4008,"api error"),
	APINODATA(4009,"no data found"),
	SENDMSGERR(4010,"failed to send validating code"),
	CHECKVERCODEERR(4011,"check vercode  err"),
	FMEMBISNEW(4012,"new ffan member"),
	BMEMBISNEW(4013,"new brand member"),
	MEMBEREXIST(4014,"member is exists"),
	COUPONOUTSTOCK(4015,"coupon is out of stock"),
	REGISTE_ERR(4016,"regist err"),
	SEND_MSG_TOO_OFTEN(4027,"send message too often"),
	SEND_MSG_MOBILE_ERR(4999,"wrong mobile"),
	COUPON_CODE_ERR(5601,"coupon code is err"),
	COUPON_IS_USED(5705,"coupon code is used"),
	COUPON_STORE_ERR(5206,"coupon can't use in the store"),
	COUPON_CHECK_UNSUPPORT(5203,"third coupon check can't support"),
	COUPON_NOT_EXSITS(5001,"coupon product is not exsits"),
	COUPON_THIRD_NO(5704,"third coupon not exsits"),
	PICKUPCODE_ERR(4017,"pickUpCode is not exsits"),
	PICKUPCODE_IS_UNUSE(4018,"pickUpCode is can't use"),
	ACTIVITY_IS_MISING(4500,"activity is not exsits"),
	ACTIVITY_IS_DISABLED(4501,"activity is disabled"),
	BMEMBISOLD(4502,"old brand  member "),
	ACTIVITY_COUPON_MAX(4503,"you has collected the coupon"),
	ACTIVITY_FOR_NEW_MEMBER(4504,"coupon is for new brand member"),
	UNKOWNERROR(4999,"unkown error"),
	ADDERROR(5001,"already exsits");


	private int code;
	private String msg;

	private ResponseInfo(int code, String msg){
		this.code=code;
		this.msg=msg;
	}

	public int getCode(){
		return this.code;
	}

	public String getMsg(){
		return this.msg;
	}

	public void setParameterErrorMsg(String msg){
		this.msg=msg;
	}
}
