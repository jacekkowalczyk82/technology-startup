# java file writing 

[Java - Write to File | Baeldung](https://www.baeldung.com/java-write-to-file)


```java

public  void  whenWriteStringUsingBufferedWritter_thenCorrect()  throws IOException { 
	String  str  =  "Hello"; 
	BufferedWriter  writer  =  new  BufferedWriter(new  FileWriter(fileName)); 
	writer.write(str); 
	writer.close(); 
}


public  void  whenAppendStringUsingBufferedWritter_thenOldContentShouldExistToo()  throws IOException { 
	String  str  =  "World"; 
	BufferedWriter  writer  =  new  BufferedWriter(new  FileWriter(fileName, true)); 
	writer.append(' '); 
	writer.append(str); 
	writer.close(); 
}

public  void  givenWritingStringToFile_whenUsingPrintWriter_thenCorrect()  throws IOException { 
	FileWriter  fileWriter  =  new  FileWriter(fileName);
	PrintWriter  printWriter  =  new  PrintWriter(fileWriter); 
	printWriter.print("Some String"); 

	printWriter.printf("Product name is %s and its price is %d $", "iPhone", 1000); 
	printWriter.close(); 
}



public  void  givenWritingStringToFile_whenUsingFileOutputStream_thenCorrect()  throws IOException { 
String  str  =  "Hello"; 
FileOutputStream  outputStream  =  new  FileOutputStream(fileName); 
byte[] strToBytes = str.getBytes(); 
outputStream.write(strToBytes); 
outputStream.close(); 
}



public  void  givenWritingToFile_whenUsingDataOutputStream_thenCorrect()  throws IOException { 
String  value  =  "Hello"; 
FileOutputStream  fos  =  new  FileOutputStream(fileName); 
DataOutputStream  outStream  =  new  DataOutputStream(new  
BufferedOutputStream(fos)); 
outStream.writeUTF(value); 
outStream.close(); // verify the results String result; 
FileInputStream  fis  =  new  FileInputStream(fileName); 
DataInputStream  reader  =  new  DataInputStream(fis); 
result = reader.readUTF(); 
reader.close(); 
assertEquals(value, result); 
}

```


```java
private void writeToPosition(String filename, int data, long position) 
  throws IOException {
    RandomAccessFile writer = new RandomAccessFile(filename, "rw");
    writer.seek(position);
    writer.writeInt(data);
    writer.close();
}
```

If we want to  **read the  _int_  stored at a specific location**, we can use this method:

```java
private int readFromPosition(String filename, long position) 
  throws IOException {
    int result = 0;
    RandomAccessFile reader = new RandomAccessFile(filename, "r");
    reader.seek(position);
    result = reader.readInt();
    reader.close();
    return result;
}
```

To test our functions, let’s write an integer, edit it, and finally read it back:

```java
@Test
public void whenWritingToSpecificPositionInFile_thenCorrect() 
  throws IOException {
    int data1 = 2014;
    int data2 = 1500;
    
    writeToPosition(fileName, data1, 4);
    assertEquals(data1, readFromPosition(fileName, 4));
    
    writeToPosition(fileName2, data2, 4);
    assertEquals(data2, readFromPosition(fileName, 4));
}
```



## Apache commons 

org.apache.commons.io.FileUtils
```
FileUtils.writeStringToFile(new File(debugFilePath), prettyJson, Charset.forName("UTF-8"));

```

## Other 

```java
public static void writeToFile(String content, String fileName) {  
    try {  
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));  
        writer.write(content);  
        writer.close();  
    } catch (IOException e) {  
        throw new RuntimeException(e);  
    }  
}


public static void writeToFile(String text, String fileName) throws IOException {  
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));  
    try {  
        writer.write(text+ "\n");  
    } catch (IOException e) {  
        throw new RuntimeException(e);  
    } finally {  
        writer.flush();  
        writer.close();  
    }  
}  
  
public static void appendToFile(String text, String fileName) throws IOException {  
    BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));  
    try {  
        writer.append(text);  
        writer.append("\n");  
    } catch (IOException e) {  
        throw new RuntimeException(e);  
    } finally {  
        writer.flush();  
        writer.close();  
    }  
}


 private void saveToJsonDebugFile(Object objToSave, String fileName) {  
        ObjectMapper mapper2 = new ObjectMapper();  
        JavaTimeModule javaTimeModule = new JavaTimeModule();  
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));  
        mapper2.registerModule(javaTimeModule);  
  
  try {  
            ObjectWriter ow = mapper2.writer().withDefaultPrettyPrinter();  
            String prettyJson = ow.writeValueAsString(objToSave);  
            
            FileUtils.writeStringToFile(new File(fileName), prettyJson, Charset.forName("UTF-8"));  
  
        } catch (Exception e) {  
            String failMsg = "Failed to create json or save to file";  
            logger.error(failMsg, e);  
        }  
    }
    
```



> Written with [StackEdit](https://stackedit.io/).

