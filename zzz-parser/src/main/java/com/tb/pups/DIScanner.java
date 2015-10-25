package com.tb.pups;

import static java.nio.file.FileVisitResult.CONTINUE;
import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.SingleMemberAnnotationExpr;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.ReferenceType;

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
					try {
						if(process(file)){
							counter ++;
							System.out.println(counter+"..."+file);
						}
					} catch (ParseException e) {
						throw new RuntimeException(e);
					}
				}
				return CONTINUE;
			}

			private boolean process(Path file) throws IOException, ParseException {
				boolean found = false;

				CompilationUnit cu = Utils.extractClass(file);

				final ImportDeclaration imp = new ImportDeclaration(new NameExpr("com.mg.framework.api.annotations.DataItemName"), false, false);

				Path java = file.subpath(4, file.getNameCount());
				Path result = Paths.get("/home/valentin/Dev/Projects/mbsamvn/modules/data/model/src/main/java", java.toString());

				List<TypeDeclaration> types = cu.getTypes();
				for (TypeDeclaration type : types) {
					if (! (type instanceof EnumDeclaration)) {
						/*if(!Files.isReadable(result)){
							System.out.format("missed file %s, copy it\n", result);
							Files.copy(file, result, StandardCopyOption.REPLACE_EXISTING);
						}*/

						boolean addImport = false;
						CompilationUnit target = Utils.extractClass(result);
						if (type.getAnnotations().size() > 0) {
							TypeDeclaration tp = Utils.getType(target, type.getName());
							tp.getAnnotations().addAll(type.getAnnotations());
							addImport = true;
						}

						List<BodyDeclaration> members = type.getMembers();
						for (BodyDeclaration member : members) {
							if (member instanceof MethodDeclaration) {
								MethodDeclaration met = (MethodDeclaration) member;

								List<AnnotationExpr> anns = met.getAnnotations();
								if (anns != null && anns.size() > 0) {
									found = true;
									addImport = true;
									for (AnnotationExpr ae : anns){
										String clazz = cu.getPackage().getName()+"."+type.getName();
										if(ae instanceof SingleMemberAnnotationExpr){
											addAnnotation(target, type, met, (SingleMemberAnnotationExpr)ae);
										}else{
											throw new RuntimeException(String.format(
													"чё за аннотация??  %s, %s", ae.getName(), clazz));
										}
									}

								}
							}
						}
						if(addImport){
							target.getImports().add(imp);

							try(PrintWriter out = new PrintWriter(result.toString())){
								out.println(target.toString());
							}
						}
					}
				}
				return found;
			}

			private void addAnnotation(CompilationUnit target,
					TypeDeclaration type, MethodDeclaration met,
					SingleMemberAnnotationExpr ae) {
				boolean found = false;

				TypeDeclaration t = Utils.getType(target, type.getName());
				MethodDeclaration m = null;

				found = t!=null;
				if(found){
					found = false;
					Iterator<BodyDeclaration> mit = t.getMembers().iterator();
					while (!found && mit.hasNext()) {
						BodyDeclaration b = mit.next();
						if (b instanceof MethodDeclaration) {
							m = (MethodDeclaration) b;
							if(met.getParameters().size() > 0){
								throw new RuntimeException(String.format(
										"откуда в этом методе %s.%s параметры??", type, met.getName(),
										met.getName()));
							}
							found = methodsAreEquals(m,met);
						}
					}
					if(found){
						m.getAnnotations().add(ae);
					}
				}

				if(!found){
					throw new RuntimeException(String.format(
							"какая-то хуйня, не нашли %s.%s", type.getName(),
							met.getName()));
				}
			}


			private boolean methodsAreEquals(MethodDeclaration m1,
					MethodDeclaration m2) {
				String t1 = m1.getType().toString().toLowerCase();
				String t2 = m2.getType().toString().toLowerCase();
				if(t1.contains(".")){
					t1 = t1.substring(t1.lastIndexOf('.')+1);
				}
				if(t2.contains(".")){
					t2 = t2.substring(t2.lastIndexOf('.')+1);
				}

				boolean result = m1.getName().equals(m2.getName());
				if(!result && m1.getModifiers() == m2.getModifiers() && t1.equals(t2)){
					String ms1 = m1.getName().replaceFirst("^is", "getIs");
					String ms2 = m2.getName().replaceFirst("^is", "getIs");
					result = ms1.equals(ms2);

					if(!result){
						ms1 = m1.getName().replaceFirst("^is", "get");
						ms2 = m2.getName().replaceFirst("^is", "get");
						result = ms1.equals(ms2);
					}
					if(!result){
						ms1 = m1.getName().replaceFirst("^has", "getHas");
						ms2 = m2.getName().replaceFirst("^has", "getHas");
						result = ms1.equals(ms2);
					}
				}

				return result;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
				return CONTINUE;
			}
		});

		System.out.println("counter = "+ counter);
	}
}
