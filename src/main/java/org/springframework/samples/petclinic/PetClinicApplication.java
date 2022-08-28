/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 */
@SpringBootApplication
public class PetClinicApplication {

	public static void main(String[] args) {

		long uptimeInSeconds = ManagementFactory.getRuntimeMXBean().getUptime() / 1_000;
		System.out.println("JVM uptime = " + uptimeInSeconds + " seconds");

		boolean cOptimizationEnabled = Boolean.getBoolean("appplicationinsights.debug.jit.c2-optimization.enabled");
		if (cOptimizationEnabled) {
			disableC2CompilerDirective();
		}

		SpringApplication.run(PetClinicApplication.class, args);

	}

	private static void disableC2CompilerDirective() {

		try {

			long pid = ProcessHandle.current().pid();
			Process directivesClearProcess = new ProcessBuilder("jcmd", "" + pid, "Compiler.directives_clear").start();

			int err = directivesClearProcess.waitFor();
			if (err == 0) {
				System.out.println("Compiler directive successfully removed");
			}

			if (err != 0) {
				InputStream errorStream = directivesClearProcess.getErrorStream();
				System.out.println("ERROR/ Compiler directive NOT successfully removed");
				printErrorOutputStreamOf(errorStream);
				throw new IllegalStateException("Compiler directive not removed.");
			}

			directivesClearProcess.destroy();

		} catch (IOException ioException) {
			throw new RuntimeException(ioException);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void printErrorOutputStreamOf(InputStream errorStream) throws IOException {
		List<String> errorLines = readOutput(errorStream);
		System.out.println();
		System.out.println("ErrorStream");
		System.out.println("-------------------------------------------");
		for (String line : errorLines) {
			System.out.println(line + System.lineSeparator());
		}
		System.out.println("-------------------------------------------");
		System.out.println();
	}

	private static void printInputStreamOf(Process process) throws IOException {
		InputStream inputStream = process.getInputStream();
		List<String> inputStreamLines = readOutput(inputStream);
		System.out.println("-------------------------------------------");
		System.out.println("InputStream");
		for (String inputStreamLine : inputStreamLines) {
			System.out.println(inputStreamLine + System.lineSeparator());
		}
		System.out.println("-------------------------------------------");
		System.out.println();
	}

	private static List<String> readOutput(InputStream inputStream) throws IOException {
		try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream))) {
			return output.lines()
				.collect(Collectors.toList());
		}
	}

}
