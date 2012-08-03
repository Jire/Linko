import java.io.InputStream;
import java.io.PrintStream;

public interface Language {

	public void parse();

	public PrintStream getOutputStream();
	public InputStream getInputStream();

	public int[] getMemory();
	public int getMemoryPointer();
	public void setMemoryPointer(int memoryPointer);

	public String getCode();
	public int getCodePointer();
	public void setCodePointer(int codePointer);

}