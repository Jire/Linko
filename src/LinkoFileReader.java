import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class LinkoFileReader {

	private static final String VALID_CHARACTERS = "<>+-[].,";

	private final File file;
	private final int memorySize;

	public LinkoFileReader(File file, int memorySize) {
		this.file = file;
		this.memorySize = memorySize;
	}

	public LinkoFileReader(String file, int memorySize) {
		this(new File(file), memorySize);
	}

	public Language toLanguage() throws IOException {
		StringBuilder codeBuilder = new StringBuilder();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while ((line = reader.readLine()) != null) {
			codeBuilder.append(line);
		}
		reader.close();

		String code = "";
		boolean remove = false;
		for (int i = 0; i < codeBuilder.length(); i++) {
			if (remove) {
				code += codeBuilder.charAt(i);
				remove = false;
			} else {
				if (VALID_CHARACTERS.indexOf(codeBuilder.charAt(i)) != -1) {
					code += codeBuilder.charAt(i);
				} else if (codeBuilder.charAt(i) == '\\') {
					code += "\\";
					remove = true;
				}
			}
		}
		
		System.out.println(code);

		return new Linko(memorySize, code);
	}

	public File getFile() {
		return file;
	}

	public static void main(String[] args) throws IOException {
		if (args.length < 1) {
			System.err.println("Usage: <file> [mem-size]");
			return;
		}

		int memorySize = 1024;
		if (args.length > 1) {
			memorySize = Integer.parseInt(args[1]);
		}

		Language language = new LinkoFileReader(args[0], memorySize).toLanguage();
		language.parse();
	}

}