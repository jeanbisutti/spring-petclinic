package ai.startup;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
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
	void profile_spring_boot_with_ai_runtime_attach() {
		ApplicationInsights.attach();
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	@MeasureHeapAllocation
	void heap_allocation_main_thread_spring_boot_with_ai_runtime_attach() {
		ApplicationInsights.attach();
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	void heap_allocation_all_threads_spring_boot_with_ai() {
		ByteWatcher byteWatcher = new ByteWatcher();
		byteWatcher.reset();
		ApplicationInsights.attach();
		String[] args = {};
		PetClinicApplication.main(args);
		byteWatcher.printAllAllocations();
	}

}
