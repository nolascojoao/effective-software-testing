package ch01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlanningPokerTest {
	
	@Test
	void rejectNullInput() {
		Assertions.assertThatThrownBy(
				() -> new PlanningPoker().identifyExtremes(null)
				).isInstanceOf(IllegalArgumentException.class);
	}
	// Asserts that an exception happens when we call the method
	// Asserts that this assertion is an IllegalArgumentException
	
	@Test
	void rejectEmptyList() {
		Assertions.assertThatThrownBy(() -> {
			List<Estimate> emptyList = Collections.emptyList();
			new PlanningPoker().identifyExtremes(emptyList);
		}).isInstanceOf(IllegalArgumentException.class);
	}
	// Similar to the earlier test,ensures that the program 
	// throws an exception if an empty list of estimates is passed as input
	
	@Test
	void rejectSingleEstimate() {
		Assertions.assertThatThrownBy(() -> {
			List<Estimate> list = Arrays.asList(new Estimate("Eleanor", 1));
			new PlanningPoker().identifyExtremes(list);
		}).isInstanceOf(IllegalArgumentException.class);
	}
	// Ensures that the program throws an exception if a list with a single 
	// estimate is passed
	
	@Test
	void twoEstimates() {
		List<Estimate> list = Arrays.asList(
				new Estimate("Mauricio", 10),
				new Estimate("Frank", 5)
				);
		List<String> devs = new PlanningPoker().identifyExtremes(list);
		
		Assertions.assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Frank");
	}
	
	@Test
	void manyEstimates() {
		List<Estimate> list = Arrays.asList(
				new Estimate("Mauricio", 10),
				new Estimate("Arie", 5),
				new Estimate("Frank", 7)
				);
		List<String> devs = new PlanningPoker().identifyExtremes(list);
		
		Assertions.assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Arie");
	}

}
