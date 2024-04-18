# How to read file in java line by line  - old way 

https://www.baeldung.com/reading-file-in-java

```java
package fileReaderBase;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileReaderDemo {

//	https://www.baeldung.com/reading-file-in-java
		
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BufferedReader bufReader = null;
		FileReader fr = null;
		List<String> listOfLines = new ArrayList<>();
		
		try {
//			fr = new FileReader("c:\\temp\\meeting-INFO.txt");
			fr = new FileReader("C:\\dev\\workspaces\\java-202209\\fileReaderBase\\testfile.txt");
			bufReader = new BufferedReader(fr);
			

			String line = null;
			while ((line = bufReader.readLine()) != null) {
				listOfLines.add(line);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		finally {
			try {
				if (bufReader != null) {
					bufReader.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
//			Stream.of(listOfLines).map(s -> "FILE_LINE:: " + s).forEach(System.out::println);
			
			listOfLines.stream().map(s -> "FILE_LINE:: " + s).forEach(System.out::println);
		}

	}

}

```

## JSON READING 

```java
import com.fasterxml.jackson.databind.ObjectMapper;  
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;  
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

private static ObjectMapper jsonMapper;  

  
static {  
    jsonMapper = new ObjectMapper();  
    JavaTimeModule javaTimeModule = new JavaTimeModule();  
    javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));  
    jsonMapper.registerModule(javaTimeModule);  
}



InputStream is = testClass.getClassLoader().getResourceAsStream(resourceName);  
  
Map<String, Object> testData;  
try {  
    testData = jsonMapper.readValue(is, HashMap.class);  
   
    return testData;  
  
} catch (Exception e) {  
    String errMessage = "Failed to read data from resource: " + resourceName ;  
    logger.error(errMessage, e);  
    throw new RuntimeException(errMessage);  
}







private static List<String> readListFile(String filePath) {  
    FileInputStream fis = null;  
    BufferedReader br = null;  
    List<String> result = new ArrayList<>();  
  
    try {  
        fis = new FileInputStream(new File(filePath));  
        br = new BufferedReader(new InputStreamReader(fis));  
  
        String line;  
        while ((line = br.readLine()) != null) {  
            result.add(line);  
        }  
  
  
    } catch (FileNotFoundException e) {  
        throw new RuntimeException(e);  
    } catch (IOException e) {  
        throw new RuntimeException(e);  
    } finally {  
       try {  
          if (br != null) {  
              br.close();  
          }  
          if (fis != null) {  
              fis.close();  
          }  
       } catch (IOException e) {  
          logger.error(e.getMessage(), e);  
       }  
    }  
    return result;  
  
}




```


> Written with [StackEdit](https://stackedit.io/).
