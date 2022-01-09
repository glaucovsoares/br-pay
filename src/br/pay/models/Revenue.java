package br.pay.models;

import java.math.BigDecimal;
import java.util.Date;

public class Revenue {

	public static int DEFAULT_DAY_OF_RECEIP = 10;
	
	private Payment payment;
	private Long idRevenue;
	private Date datReceip;
	private BigDecimal amount;
	private Date datSale;

	
	
	public Revenue(Payment payment) {
		super();
		this.payment = payment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
		
	}

	public Long getIdRevenue() {
		return idRevenue;
	}

	public void setIdRevenue(Long idRevenue) {
		this.idRevenue = idRevenue;
	}

	public Date getDatReceip() {
		return datReceip;
	}

	public void setDatReceip(Date datReceip) {
		this.datReceip = datReceip;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDatSale() {
		return datSale;
	}

	public void setDatSale(Date datSale) {
		this.datSale = datSale;
	}

}
