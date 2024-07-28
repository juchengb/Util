package com.example.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellUtil {
	public String executeShell(String msg) {
		String script = "my shell script";

		StringBuilder output = new StringBuilder();

		try {
			Process process = new ProcessBuilder("/usr/bin/sh", "-c", script).start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line).append("\n");
			}

			int exitCode = process.waitFor();
			if (exitCode != 0) {
				output.append("Error: script exited with code ").append(exitCode).append("\n");
			}
		} catch (Exception e) {
			output.append("Exception: ").append(e.getMessage()).append("\n");
		}
		return output.toString();
	}
}
