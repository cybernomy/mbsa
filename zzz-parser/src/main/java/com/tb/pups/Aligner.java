package com.tb.pups;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

public class Aligner {

	public static void main(String[] args) throws IOException, ParseException {
		final String s = "/home/valentin/Dev/Projects/mbsamvn/modules/data/model/src/main/java/com/mg/merp";
		Path p = Paths.get(s);
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr)
					throws FileNotFoundException, IOException {
				if (file.toString().endsWith(".java")) {
					try {
						CompilationUnit cu = extractClass(file);
						try(PrintWriter out = new PrintWriter(file.toString())){
							out.println(cu.toString());
						}
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
				return CONTINUE;
			}

			private CompilationUnit extractClass(Path file) throws FileNotFoundException, IOException, ParseException{
				try (FileInputStream in = new FileInputStream(file.toString())) {
					return JavaParser.parse(in);
				}
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
				return CONTINUE;
			}
		});
	}
}
