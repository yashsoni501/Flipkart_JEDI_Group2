/**
 * 
 */
package com.flipkart.service;

import com.flipkart.bean.Payment;

/**
 * @author yashsoni501
 *
 */
public class PaymentServiceImpl implements PaymentInterface {

	public static volatile PaymentServiceImpl instance = null;
	
	public static PaymentServiceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(PaymentServiceImpl.class){
				instance=new PaymentServiceImpl();
			}
		}
		return instance;
	}
	
	@Override
	public boolean sendNotification(Payment payment) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean initiatePayment(String studentId, float amount, int semester, String session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Payment generateFeeReciept(Payment payment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String onlinePayment(String studentId, float amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String offlinePayment(String studentId, float amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
