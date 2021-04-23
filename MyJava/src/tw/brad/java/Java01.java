package tw.brad.java;

import java.io.File;

public class Java01 {

	public static void main(String[] args) {
		File here = new File(".");
		System.out.println(here.getAbsolutePath());
	}

}
