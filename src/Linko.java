public class Linko extends AbstractLanguage {

	public Linko(int memorySize, String code) {
		super(memorySize, code);
	}

	public Linko(String code) {
		super(code);
	}

	@Override
	public void parse() {
		while (interpret());
	}

	protected boolean interpret() {
		char instruction;

		try {
			instruction = getCode().charAt(getCodePointer());
		} catch (Exception e) {
			return false;
		}

		switch (instruction) {
		case '<':
			setMemoryPointer(getMemoryPointer() > 0 ? getMemoryPointer() - 1 : 0);
			break;
		case '>':
			setMemoryPointer(getMemoryPointer() < getMemory().length ? getMemoryPointer() + 1 : getMemoryPointer());
			break;
		case '+':
			getMemory()[getMemoryPointer()]++;
			break;
		case '-':
			getMemory()[getMemoryPointer()]--;
			break;
		case '[':
			if (getMemory()[getMemoryPointer()] <= 0) {
				return endWhile();
			}
			break;
		case ']':
			return beginWhile();
		case '\\':
			try {
				getMemory()[getMemoryPointer()] = getCode().charAt(getCodePointer() + 1);
				setCodePointer(getCodePointer() + 1);
			} catch (Exception e) {
				return false;
			}
			break;
		case '.':
			getOutputStream().print((char) getMemory()[getMemoryPointer()]);
			break;
		case ',':
			try {
				getMemory()[getMemoryPointer()] = getInputStream().read();
				if (getMemory()[getMemoryPointer()] == '\n') {
					getMemory()[getMemoryPointer()] = 0;
				}
			} catch (Exception e) {
				return false;
			}
			break;
		}

		setCodePointer(getCodePointer() + 1);

		return true;
	}

	protected boolean beginWhile() {
		int level = 0;

		for (int i = getCodePointer() - 1; i >= 0; i--) {
			switch (getCode().charAt(i)) {
			case '[':
				if (level > 0) {
					level--;
				} else {
					setCodePointer(i);
					return true;
				}
				break;
			case ']':
				level++;
				break;
			}
		}
		return false;
	}

	protected boolean endWhile() {
		int level = 0;

		for (int i = getCodePointer() + 1; i < getCode().length(); i++) {
			switch (getCode().charAt(i)) {
			case '[':
				level++;
				break;
			case ']':
				if (level <= 0) {
					setCodePointer(i + 1);
					return true;
				} else {
					level--;
				}
				break;
			}
		}
		return false;
	}

}