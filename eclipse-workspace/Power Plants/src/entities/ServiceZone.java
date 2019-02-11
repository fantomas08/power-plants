package entities;

public class ServiceZone {

	private double avgConsumption;
	private int numConsumers;
	private String province;

	public ServiceZone(double avgConsumption, int numConsumers, String province) {

		this.avgConsumption = avgConsumption;
		this.numConsumers = numConsumers;
		this.province = province;
	}

	public double getAvgConsumption() {
		return avgConsumption;
	}

	public void setAvgConsumption(double avgConsumption) {
		this.avgConsumption = avgConsumption;
	}

	public int getNumConsumers() {
		return numConsumers;
	}

	public void setNumConsumers(int numConsumers) {
		this.numConsumers = numConsumers;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "ServiceZone [avgConsumption=" + avgConsumption + ", numConsumers=" + numConsumers + ", province="
				+ province + "]";
	}

}
