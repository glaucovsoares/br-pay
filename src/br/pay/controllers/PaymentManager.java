package br.pay.controllers;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.pay.dao.PaymentDao;
import br.pay.dao.RevenueDao;
import br.pay.models.Payment;
import br.pay.models.Revenue;

public class PaymentManager {

	public static Double DEFAULT_TAX = 0.02;
	public static Double NORMAL_TAX  = 0.05;
	public static Double HIGH_TAX    = 0.07;
	
	private PaymentDao paymentDao;
	private RevenueDao revenueDao;
	
	
	public PaymentManager(PaymentDao paymentDao, RevenueDao revenueDao) {
		super();
		this.paymentDao = paymentDao;
		this.revenueDao = revenueDao;
	}

	public void makePayment(Payment payment) throws Throwable {
		if(paymentDao.isSettledSale(payment.getSale()) == false) {
			
			paymentDao.save(payment);
			
			Revenue revenue = new Revenue(payment);			
			revenue.setDatSale(payment.getDatPayment());			
			
			BigDecimal tax;
			Calendar datReceip = new GregorianCalendar();
			datReceip.setTime(payment.getDatPayment());		
						
			
			if (Payment.CASH.equals(payment.getType())) {
				
				revenue.setAmount(payment.getAmount());
				revenue.setDatReceip(payment.getDatPayment());	
				
				revenueDao.save(revenue);
				
			} else {
				if(payment.getNumInstallment()==1) {										
					tax = payment.getAmount().multiply(new BigDecimal(DEFAULT_TAX));
					
					revenue.setAmount(payment.getAmount().subtract(tax));
					
					datReceip.set(Calendar.DAY_OF_WEEK, Revenue.DEFAULT_DAY_OF_RECEIP);
					datReceip.add(Calendar.MONTH, 1);
										
					revenue.setDatReceip(datReceip.getTime());
					
					revenueDao.save(revenue);
				}else {
					tax = payment.getNumInstallment() <= 3 ? new BigDecimal(NORMAL_TAX) : new BigDecimal(HIGH_TAX);
					tax = payment.getAmount().multiply(tax);
					
					revenue.setAmount(payment.getAmount().subtract(tax));
					
					for (int i = 1; i <= payment.getNumInstallment(); i++) {
						datReceip.setTime(payment.getDatPayment());		
						datReceip.set(Calendar.DAY_OF_WEEK, Revenue.DEFAULT_DAY_OF_RECEIP);
						datReceip.add(Calendar.MONTH, i);
						
						revenue = new Revenue(payment);

						revenue.setDatSale(payment.getDatPayment());
						revenue.setAmount(payment.getAmount().subtract(tax));						
						revenue.setDatReceip(datReceip.getTime());	
						
						revenueDao.save(revenue);
					}
				}
			}
		} else {
			throw new Throwable("The Sale has already been paid!");
		}
	}
}
