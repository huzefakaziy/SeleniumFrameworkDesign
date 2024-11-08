package selenium.testData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<Object, Object>> getJSONData() throws IOException
	{
		//Step1: Convert JSON file to string
		String jsonContent = FileUtils.readFileToString(new File("src\\test\\java\\selenium\\testData\\PurchaseOrder.json"), StandardCharsets.UTF_8);
		 
		//Step2: Convert String to List of Hashmaps using Jackson Databind ObjectMapper Class		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<Object, Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<Object, Object>>>() {
		});
		
		//Return List of HashMaps converted from JSON file
		return data;
	}

}
