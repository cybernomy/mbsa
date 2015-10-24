package com.tb.pups;

import static java.nio.file.FileVisitResult.CONTINUE;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;

public class EnumAnnMapper {

	public static Set<String> enums = new HashSet<>(90);

	public static void main(String[] args) throws IOException, ParseException {
		prepare();
		final String s = "/home/valentin/Dev/Projects/mbsamvn/modules/data/model/src/main/java";
		Path p = Paths.get(s);
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr)
					throws FileNotFoundException, IOException {
				if (file.toString().endsWith(".java")) {
					try {
						process(file);
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
				return CONTINUE;
			}

			private void process(Path file)	throws IOException, ParseException {

				CompilationUnit cu = Utils.extractClass(file);

				final ImportDeclaration imp = new ImportDeclaration(new NameExpr("javax.persistence.Enumerated"),false, false);
				final ClassOrInterfaceType abe = new ClassOrInterfaceType();

				List<TypeDeclaration> types = cu.getTypes();

				for (TypeDeclaration t : types) {
					if (t instanceof ClassOrInterfaceDeclaration) {
						ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) t;
						if (Utils.isTypeExtends(type, "com.mg.merp.core.model.AbstractEntity")) {
							boolean addImport = false;

							List<BodyDeclaration> members = type.getMembers();
							for (BodyDeclaration member : members) {
								if (member instanceof MethodDeclaration) {
									MethodDeclaration met = (MethodDeclaration) member;

									if (checkMethod(met)) {
										System.out.println(met);
									}

								}
							}

							if (addImport) {
								cu.getImports().add(imp);
							}
							/*
							 * try(PrintWriter out = new
							 * PrintWriter(result.toString())){
							 * out.println(target.toString()); }
							 */
						}
					}
				}
			}

			private boolean checkMethod(MethodDeclaration met) {
				boolean result = met.getName().startsWith("get")
						&& met.getType() instanceof ReferenceType;
				return result && enums.contains(met.getType().toString()) && (Utils.getMethodAnnotation(met, "Column") != null);
			}

			private void addAnnotation(CompilationUnit target,
					TypeDeclaration type, MethodDeclaration met,
					SingleMemberAnnotationExpr ae) {
				boolean found = false;

				TypeDeclaration t = Utils.getType(target, type.getName());
				MethodDeclaration m = null;

				found = t != null;
				if (found) {
					found = false;
					Iterator<BodyDeclaration> mit = t.getMembers().iterator();
					while (!found && mit.hasNext()) {
						BodyDeclaration b = mit.next();
						if (b instanceof MethodDeclaration) {
							m = (MethodDeclaration) b;
							if (met.getParameters().size() > 0) {
								throw new RuntimeException(
										String.format(
												"откуда в этом методе %s.%s параметры??",
												type, met.getName(),
												met.getName()));
							}
						}
					}
					if (found) {
						m.getAnnotations().add(ae);
					}
				}

				if (!found) {
					throw new RuntimeException(String.format(
							"какая-то хуйня, не нашли %s.%s", type.getName(),
							met.getName()));
				}
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
				return CONTINUE;
			}
		});
	}

	private static void prepare() throws IOException {
		final String s = "/home/valentin/Dev/Projects/mbsamvn/modules/data/premodel/src/main/java";
		Path p = Paths.get(s);
		Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr)
					throws FileNotFoundException, IOException {
				try {
					TypeDeclaration td = Utils.extractClass(file).getTypes().get(0);
					if (td instanceof EnumDeclaration){
						enums.add(td.getName());
					}else{
						throw new RuntimeException(String.format("а хто це %s ?", td.getName()));
					}
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				return CONTINUE;
			}
		});

	}
}
