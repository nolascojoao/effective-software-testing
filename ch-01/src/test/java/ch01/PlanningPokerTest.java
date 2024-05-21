package ch01;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

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
	
	@Property
	void inAnyOrder(@ForAll("estimates") List<Estimate> estimates) {
		estimates.add(new Estimate("MrLowEstimate", 1));
		estimates.add(new Estimate("MsHighEstimate", 100));
		
		Collections.shuffle(estimates);
		
		List<String> dev = new PlanningPoker().identifyExtremes(estimates);
		
		Assertions.assertThat(dev)
			.containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate");
	}
	
	@Provide
	Arbitrary<List<Estimate>>estimates() {
		Arbitrary<String> names = Arbitraries.strings()
				.withCharRange('a', 'z').ofLength(5);
		
		Arbitrary<Integer> values = Arbitraries.integers().between(2, 99);
		
		Arbitrary<Estimate>estimates = Combinators.combine(names, values)
				.as((name, value) -> new Estimate(name, value));
		
		return estimates.list().ofMinSize(1);
	}

}
