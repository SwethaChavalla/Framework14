package genericCommands;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CsvData {
	private Map<String, String> fileHeader = Collections.emptyMap();

	private Map<String, String> values = new LinkedHashMap<String, String>();

	public CsvData(Map<String, String> fileHeader, Map<String, String> values) {
		this.fileHeader = fileHeader;
		this.values = values;
	}
	public Map<String, String> getValues() {
		return values;
	}
}
