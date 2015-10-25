package com.tb.pups;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
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
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
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

			private void process(Path file) throws IOException, ParseException {

				CompilationUnit cu = Utils.extractClass(file);

				final ImportDeclaration impEn = new ImportDeclaration(
						new NameExpr("javax.persistence.Enumerated"), false,
						false);
				final ImportDeclaration impET = new ImportDeclaration(
						new NameExpr("javax.persistence.EnumType"), false,
						false);

				List<TypeDeclaration> types = cu.getTypes();

				for (TypeDeclaration t : types) {
					if (t instanceof ClassOrInterfaceDeclaration) {
						ClassOrInterfaceDeclaration type = (ClassOrInterfaceDeclaration) t;

						List<BodyDeclaration> members = type.getMembers();
						for (BodyDeclaration member : members) {
							if (member instanceof MethodDeclaration) {
								MethodDeclaration met = (MethodDeclaration) member;

								if (checkMethod(met)) {
									AnnotationExpr ae = Utils
											.getMethodAnnotation(met, "Column");
									if (ae instanceof NormalAnnotationExpr) {
										NormalAnnotationExpr nae = (NormalAnnotationExpr) ae;
										Iterator<MemberValuePair> it = nae
												.getPairs().iterator();
										boolean found = false;
										while (!found && it.hasNext()) {
											MemberValuePair mvp = it.next();
											if (mvp.getName().equalsIgnoreCase(
													"columnDefinition")) {
												it.remove();
												found = true;
											}
										}
									}

									AnnotationExpr en = Utils
											.getMethodAnnotation(met,
													"Enumerated");
									if (en == null) {
										cu.getImports().add(impEn);
										cu.getImports().add(impET);
										en = new SingleMemberAnnotationExpr(
												new NameExpr("Enumerated"),
												new NameExpr("EnumType.ORDINAL"));
										met.getAnnotations().add(en);
									}
									try (PrintWriter out = new PrintWriter(file
											.toString())) {
										out.println(cu.toString());
									}
								}

							}
						}
					}
				}

			}

			private boolean checkMethod(MethodDeclaration met) {
				boolean result = met.getName().startsWith("get")
						&& met.getType() instanceof ReferenceType;
				return result && enums.contains(met.getType().toString())
						&& (Utils.getMethodAnnotation(met, "Column") != null);
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
					TypeDeclaration td = Utils.extractClass(file).getTypes()
							.get(0);
					if (td instanceof EnumDeclaration) {
						enums.add(td.getName());
					} else {
						throw new RuntimeException(String.format(
								"а хто це %s ?", td.getName()));
					}
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
				return CONTINUE;
			}
		});

	}
}
