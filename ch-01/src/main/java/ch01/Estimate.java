package ch01;

import java.util.Objects;

public class Estimate {

	private final String developer;
	private final int estimate;

	public Estimate(String developer, int estimate) {
		this.developer = developer;
		this.estimate = estimate;
	}

	public String getDeveloper() {
		return developer;
	}

	public int getEstimate() {
		return estimate;
	}

	@Override
	public String toString() {
		return "Estimate [developer=" + developer + ", estimate=" + estimate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(developer, estimate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estimate other = (Estimate) obj;
		return Objects.equals(developer, other.developer) && estimate == other.estimate;
	}
}
