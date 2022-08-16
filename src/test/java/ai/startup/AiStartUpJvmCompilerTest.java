package ai.startup;

import org.junit.jupiter.api.Test;
import org.quickperf.junit5.QuickPerfTest;
import org.quickperf.jvm.annotations.JvmOptions;
import org.quickperf.jvm.annotations.MeasureHeapAllocation;
import org.quickperf.jvm.jfr.annotation.ProfileJvm;
import org.springframework.samples.petclinic.PetClinicApplication;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;

@QuickPerfTest
class AiStartUpJvmCompilerTest {

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar -XX:+PrintCompilation")
	void spring_boot_with_ai_agent_print_compilation() {
		String[] args = {};
		PetClinicApplication.main(args);
	}
	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar -XX:+PrintCompilation -XX:TieredStopAtLevel=3")
	void spring_boot_with_ai_agent_without_c2_compiler() {
		String[] args = {};
		PetClinicApplication.main(args);
	}

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar -XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:CompilerDirectivesFile=jvm-compiler-tuning.json")
	void spring_boot_with_ai_agent_compiler_control() {
		String[] args = {};
		PetClinicApplication.main(args);
	}


	@Test
	@ProfileJvm
	void profile_spring_boot() {
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
	@JvmOptions
	void spring_boot_compilation_time() {
		String[] args = {};
		PetClinicApplication.main(args);
		CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
		long totalCompilationTime = compilationMXBean.getTotalCompilationTime();
		System.out.println("totalCompilationTime = " + totalCompilationTime + " ms");
	}

	@Test
	@JvmOptions("-javaagent:applicationinsights-agent-3.3.1.jar")
	void spring_boot_with_ai_agent_compilation_time() {
		String[] args = {};
		PetClinicApplication.main(args);
		CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
		long totalCompilationTime = compilationMXBean.getTotalCompilationTime();
		System.out.println("totalCompilationTime = " + totalCompilationTime + " ms");
	}

}
