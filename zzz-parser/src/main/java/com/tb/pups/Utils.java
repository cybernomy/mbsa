package com.tb.pups;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

public class Utils {

	public static CompilationUnit extractClass(Path file)
			throws FileNotFoundException, IOException, ParseException {
		try (FileInputStream in = new FileInputStream(file.toString())) {
			return JavaParser.parse(in);
		}
	}

	public static TypeDeclaration getType(CompilationUnit target, String name) {
		boolean found = false;
		TypeDeclaration t = null;

		Iterator<TypeDeclaration> it = target.getTypes().iterator();
		while (!found && it.hasNext()) {
			t = it.next();
			found = name.equals(t.getName());
		}

		return found ? t : null;
	}

	public static Class findClass(String clazzName) {
		Class result = null;
		try {
			result = Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			try {
				result = Class.forName("java.lang." + clazzName);
			} catch (ClassNotFoundException e1) {

				Set<String> classNames = new FastClasspathScanner("com.mg.merp")
						.scan().getNamesOfAllClasses();
				Iterator<String> it = classNames.iterator();
				while (result == null && it.hasNext()) {
					String clz = it.next();
					if (clz.endsWith(clazzName)) {
						try {
							result = Class.forName(clz);
						} catch (ClassNotFoundException e2) {
							throw new RuntimeException(e2);
						}
					}
				}
			}
		}
		return result;
	}

	public static boolean isTypeExtends(ClassOrInterfaceDeclaration type,
			String name) {
		boolean result = false;
		List<ClassOrInterfaceType> ext = type.getExtends();
		if (ext != null) {
			Iterator<ClassOrInterfaceType> it = ext.iterator();
			while (!result && it.hasNext()) {
				result = it.next().toString().equals(name);
			}
		}
		return result;
	}

	public static AnnotationExpr getMethodAnnotation(MethodDeclaration met,
			String annotationName) {
		AnnotationExpr result = null;
		List<AnnotationExpr> anns = met
				.getAnnotations();
		if (anns != null && anns.size() > 0) {
			Iterator<AnnotationExpr> it = anns.iterator();
			while (result == null && it.hasNext()) {
				AnnotationExpr a = it.next();
				result = a.getName().toString().equals(annotationName) ? a : null;
			}
		}
		return result;
	}

}
