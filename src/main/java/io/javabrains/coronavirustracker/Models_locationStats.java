package io.javabrains.coronavirustracker;

public class Models_locationStats {
	private String state;
	private String country;
	private int latestTotalcases;
	private int diffFromPrevDay;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "Models_locationStats [state=" + state + ", country=" + country + ", latestTotalcases="
				+ latestTotalcases + "]";
	}
	public int getLatestTotalcases() {
		return latestTotalcases;
	}
	public void setLatestTotalcases(int latestTotalcases) {
		this.latestTotalcases = latestTotalcases;
	}

}
