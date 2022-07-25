package ai.startup;

import org.junit.jupiter.api.Test;
import org.quickperf.junit5.QuickPerfTest;
import org.quickperf.jvm.annotations.JvmOptions;
import org.quickperf.jvm.annotations.MeasureHeapAllocation;
import org.quickperf.jvm.jfr.annotation.ProfileJvm;
import org.springframework.samples.petclinic.PetClinicApplication;

@QuickPerfTest
class AiStartUpTest {

	@Test
	@ProfileJvm
	void profile_spring_boot() {
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	void heap_allocation_all_threads_spring_boot() {
		ByteWatcher byteWatcher = new ByteWatcher();
		byteWatcher.reset();
		String[] args = {};
		PetClinicApplication.main(args);
		byteWatcher.printAllAllocations();
	}

	@Test
	@MeasureHeapAllocation
	void heap_allocation_main_thread_spring_boot() {
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar")
	@ProfileJvm
	void profile_spring_boot_with_ai_agent() {
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar")
	@MeasureHeapAllocation
	void heap_allocation_main_thread_spring_boot_with_ai() {
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar")
	void heap_allocation_all_threads_spring_boot_with_ai() {
		ByteWatcher byteWatcher = new ByteWatcher();
		//byteWatcher.reset(); // Don't reset byte watcher because the agent is already installed at this point
		String[] args = {};
		PetClinicApplication.main(args);
		byteWatcher.printAllAllocations();
	}

}
