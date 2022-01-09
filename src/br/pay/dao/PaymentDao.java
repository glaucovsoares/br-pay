package br.pay.dao;

import br.pay.models.Payment;
import br.pay.models.Sale;

public class PaymentDao {

	public void save(Payment payment) {
		//save on database
	}
	
	public boolean isSettledSale(Sale sale) {
		return false; //check in database if sale is settled
	}
}
