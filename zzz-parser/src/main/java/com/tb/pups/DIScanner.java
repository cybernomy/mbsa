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
import java.util.Iterator;
import java.util.List;

import com.github.javaparser.JavaParser;
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
						if(process(file, extractClass(file))){
							counter ++;
							System.out.println(counter+"..."+file);
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

			private boolean process(Path file, CompilationUnit cu) throws IOException, ParseException {
				boolean found = false;

				final ImportDeclaration imp = new ImportDeclaration(new NameExpr("com.mg.framework.api.annotations.DataItemName"), false, false);

				Path java = file.subpath(4, file.getNameCount());
				Path result = Paths.get("/home/valentin/Dev/Projects/mbsamvn/modules/data/model/src/main/java", java.toString());

				List<TypeDeclaration> types = cu.getTypes();
				for (TypeDeclaration type : types) {
					if (type instanceof EnumDeclaration) {
						//Path target = Paths.get("/home/valentin/Dev/Projects/mbsamvn/modules/data/premodel/src/main/java", file.subpath(4, file.getNameCount()).toString());
						//Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);
					} else {

						if(!Files.isReadable(result)){
							System.out.format("missed file %s, copy it\n", result);
							//throw new RuntimeException(String.format());
							Files.copy(file, result, StandardCopyOption.REPLACE_EXISTING);
						}

						boolean addImport = false;
						CompilationUnit target = extractClass(result);
						if (type.getAnnotations().size() > 0) {
							TypeDeclaration tp = getType(target, type.getName());
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
										/*try(PrintWriter out = new PrintWriter(result.toString())){
											out.println(target.toString());
										}*/
									}

								}
							}
						}
						if(addImport){
							target.getImports().add(imp);
						}
					}
				}
				return found;
			}

			private TypeDeclaration getType(CompilationUnit target, String name) {
				boolean found = false;
				TypeDeclaration t = null;

				Iterator<TypeDeclaration> it = target.getTypes().iterator();
				while (!found && it.hasNext()) {
					t = it.next();
					found = name.equals(t.getName());
				}

				return found?t:null;
			}

			private void addAnnotation(CompilationUnit target,
					TypeDeclaration type, MethodDeclaration met,
					SingleMemberAnnotationExpr ae) {
				boolean found = false;

				TypeDeclaration t = getType(target, type.getName());
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
							"какая-то хуйня, не нашли %s.%s", type,
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
					String ms1 = m1.getName().replaceFirst("^is", "get");
					String ms2 = m2.getName().replaceFirst("^is", "get");
					result = ms1.equals(ms2);
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
