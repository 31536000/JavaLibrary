package autoSubmit;

import java.io.IOException;

public class AutoImport {
	public static boolean autoImport(java.io.File source, java.io.File dest) {
		System.err.println(source.getAbsolutePath());
		String firstPath;
		{
			String str = source.getAbsolutePath();
			int len = str.indexOf("src");
			if (len == -1) return false;
			firstPath = str.substring(0, len + 4);
		}
		java.util.HashSet<String> set = new java.util.HashSet<>();
		java.util.Queue<java.io.File> check = new java.util.ArrayDeque<>();

		java.util.ArrayDeque<String> print = new java.util.ArrayDeque<>();
		check.add(source);
		java.util.regex.Pattern packagePatturn = java.util.regex.Pattern.compile("^package ");
		java.util.regex.Pattern importPatturn = java.util.regex.Pattern.compile("^import ");
		java.util.regex.Pattern className = java.util.regex.Pattern.compile("[A-Z$_][a-zA-Z0-9$_]{0,}");
		String[] classPrefix = {"class", "enum", "interface", "abstract", "final", "static", "strictfp"};
		java.util.HashMap<String, java.util.regex.Pattern> classPattern = new java.util.HashMap<>();
		for (String i : classPrefix) classPattern.put(i, java.util.regex.Pattern.compile("public +" + i));
		java.util.TreeSet<String> importSet = new java.util.TreeSet<>(java.util.Comparator.reverseOrder());

		while(!check.isEmpty()) {
			java.io.File file = check.poll();
			String nowPath = file.getAbsolutePath();
			if (set.contains(nowPath)) continue;
			set.add(nowPath);
			System.err.println(nowPath);
			nowPath = nowPath.substring(0, nowPath.lastIndexOf("\\") + 1);
			try (java.util.Scanner scanner = new java.util.Scanner(file)) {
				boolean includeClass = set.size() != 1;
				while(scanner.hasNext()) {
					String str = scanner.nextLine();
					if (packagePatturn.matcher(str).find()) continue;
					if (importPatturn.matcher(str).find()) {
						String path = str.substring(7, str.length() - 1);
						path = firstPath + path.replace('.', '\\') + ".java";
						java.io.File next = new java.io.File(path);
						if (next.exists()) check.add(next);
						else importSet.add(str);
						continue;
					}
					for (java.util.Map.Entry<String, java.util.regex.Pattern> pattern : classPattern.entrySet()) {
						if (includeClass && pattern.getValue().matcher(str).find()) {
							includeClass = false;
							str = str.substring(str.indexOf(pattern.getKey()));
						}
					}
					print.addLast(str);
					java.util.regex.Matcher match = className.matcher(str);
					while (match.find()) {
						String path = nowPath + match.group() + ".java";
						java.io.File next = new java.io.File(path);
						if (next.exists()) {
							check.add(next);
						}
					}
				}
			} catch (java.io.FileNotFoundException e) {
				System.err.println("unread: " + file);
				e.printStackTrace();
			}
		}
		for (String i : importSet) print.addFirst(i);

		try {
			dest.createNewFile();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		try (java.io.BufferedWriter writer=new java.io.BufferedWriter(new java.io.FileWriter(dest))) {
			for (String s : print) {
				writer.write(s);
				writer.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		String file1 = scanner.next(), file2 = scanner.next();
		autoImport(new java.io.File(file1), new java.io.File(file2));
	}
}
