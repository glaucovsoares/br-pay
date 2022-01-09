package br.pay.models;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {

	public static String CASH      = "in cash";
	public static String PARCELED  = "in installments";
	
	private Long idPayment;
	private Sale sale;
	private String type;
	private BigDecimal amount;
	private Date datPayment;
	private Integer numInstallment;

	public Long getIdPayment() {
		return idPayment;
	}

	public void setIdPayment(Long idPayment) {
		this.idPayment = idPayment;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDatPayment() {
		return datPayment;
	}

	public void setDatPayment(Date datPayment) {
		this.datPayment = datPayment;
	}

	public Integer getNumInstallment() {
		return numInstallment;
	}

	public void setNumInstallment(Integer numInstallment) {
		this.numInstallment = numInstallment;
	}

}
