package com.tb.pups;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;

public class DIScanner {

	static int counter = 0;
	public static void main(String[] args) throws IOException, ParseException {
		final String s = "/home/valentin/KillMe/java/com/mg/merp";
		Path p = Paths.get(s);
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr)
					throws FileNotFoundException, IOException {
				if (file.toString().endsWith(".java")) {
					try (FileInputStream in = new FileInputStream(file
							.toString())) {
						CompilationUnit cu;
						cu = JavaParser.parse(in);
						if(process(file, cu)){
							counter ++;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				return CONTINUE;
			}

			private boolean process(Path file, CompilationUnit cu) throws IOException {
				boolean found = false;
				List<TypeDeclaration> types = cu.getTypes();
				for (TypeDeclaration type : types) {
					if (type instanceof EnumDeclaration) {
						//Path target = Paths.get("/home/valentin/Dev/Projects/mbsamvn/modules/data/premodel/src/main/java", file.subpath(4, file.getNameCount()).toString());
						//Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
					} else {
						List<BodyDeclaration> members = type.getMembers();
						for (BodyDeclaration member : members) {
							if (member instanceof MethodDeclaration) {
								MethodDeclaration met = (MethodDeclaration) member;
								List<AnnotationExpr> anns = met.getAnnotations();
								if (anns != null) {
									found = true;
									for (AnnotationExpr ae : anns){
										System.out.format("class %s, method %s\n",type.getName(), met.getName());
										System.out.format("----- annotation %s\n",ae.getName());
									}
								}
							}
						}
					}
				}
				return found;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
				return CONTINUE;
			}
		});

		System.out.println("counter = "+ counter);
	}
}
