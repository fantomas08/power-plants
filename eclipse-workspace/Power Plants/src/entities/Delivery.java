package entities;

import java.util.Date;

public class Delivery {
	
	private double quantity;
	private Date date;
	private Distributor distributor;
	
	public Delivery(double quantity, Date date, Distributor distributor) {
		this.quantity = quantity;
		this.date = date;
		this.distributor = distributor;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Distributor getDistributor() {
		return distributor;
	}

	public void setDistributor(Distributor distributor) {
		this.distributor = distributor;
	}

	@Override
	public String toString() {
		return "Delivery [quantity=" + quantity + ", date=" + date + ", distributor=" + distributor + "]";
	}
	
}
