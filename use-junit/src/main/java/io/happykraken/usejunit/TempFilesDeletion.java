package io.happykraken.usejunit;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TempFilesDeletion {
	public static void main(String[] args) {
		TempFilesDeletion tempFilesDeletion = new TempFilesDeletion();

		tempFilesDeletion.deleteTempFile();
	}

	public void deleteTempFile() {
		File folder = new File("/Users/julio/Dev/JavaApps/selenium-spring/use-testng/target");
		List<File> files = Arrays.asList(folder.listFiles());

		files
				.forEach(file -> {
					System.out.println(file.getName());
					if (file.isDirectory() && file.getName().equals("classes")) {
						FileSystemUtils.deleteRecursively(file);
					}
				});
	}
}
