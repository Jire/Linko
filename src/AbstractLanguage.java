import java.io.InputStream;
import java.io.PrintStream;

public abstract class AbstractLanguage implements Language {

	private final PrintStream outputStream;
	private final InputStream inputStream;

	private final int[] memory;
	private int memoryPointer;

	private final String code;
	private int codePointer;

	public AbstractLanguage(PrintStream outputStream, InputStream inputStream, int[] memory, int memoryPointer, String code, int codePointer) {
		this.outputStream = outputStream;
		this.inputStream = inputStream;
		this.memory = memory;
		this.memoryPointer = memoryPointer;
		this.code = code;
		this.codePointer = codePointer;
	}

	public AbstractLanguage(int memorySize, String code) {
		this(System.out, System.in, new int[memorySize], 0, code, 0);
	}

	public AbstractLanguage(String code) {
		this(1024, code);
	}

	@Override
	public PrintStream getOutputStream() {
		return outputStream;
	}

	@Override
	public InputStream getInputStream() {
		return inputStream;
	}

	@Override
	public int[] getMemory() {
		return memory;
	}

	@Override
	public int getMemoryPointer() {
		return memoryPointer;
	}

	@Override
	public void setMemoryPointer(int memoryPointer) {
		this.memoryPointer = memoryPointer;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public int getCodePointer() {
		return codePointer;
	}

	@Override
	public void setCodePointer(int codePointer) {
		this.codePointer = codePointer;
	}

}