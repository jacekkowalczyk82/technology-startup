/**
 * 
 */
package jacekkowalczyk82;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jacekkowalczyk82
 *
 */
public class KeepLast5 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.asList(args));
		//https://www.delftstack.com/howto/java/convert-java-program-to-executable-in-java/#:~:text=In%20Java%2C%20to%20create%20an%20executable%20JAR%20file%2C,javapackager%2C%20WinRun4J%2C%20packr%2C%20JSmooth%2C%20JexePack%2C%20InstallAnywhere%2C%20Launch4j%2C%20etc.
		//https://www.geeksforgeeks.org/regular-expressions-in-java/
		//Pattern.matches("geeksforge*ks", "geeksforgeeks")
		//https://www.baeldung.com/java-copy-list-to-another
		//Usage:
		// java -jar dir file_regex how_many_copies
//		keepLastNfiles("C:\\backups\\versioned_JoplinProfileTests", "c_dev_notes_JoplinProfile_version_\\S+.tar.gz", 5);
		
		keepLastNfiles(args[0], args[1], Integer.parseInt(args[2]));
		
		

	}

	public Set<String> listFilesUsingJavaIO(String dir) {
	    return Stream.of(new File(dir).listFiles())
	      .filter(file -> !file.isDirectory())
	      .map(File::getName)
	      .collect(Collectors.toSet());
	}
	
	public static void keepLastNfiles(String dirPath, String filesRegex, int howManyCopies) {
		
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			List<String> matchedFiles = Stream.of(dir.listFiles())
				      .filter(file -> {
				    	  System.out.println(filesRegex);
				    	  System.out.println(file.getName());
				    	  return Pattern.matches(filesRegex, file.getName());
				    	  
				      })
				      .map(File::getName)
				      .collect(Collectors.toList());
			System.out.println(matchedFiles);
			
			Collections.sort(matchedFiles, Collections.reverseOrder());
			
			matchedFiles.stream()
			.forEach(System.out::println);
			
			
			List<String> keepList = new ArrayList<String>();
			List<String> removeList = new ArrayList<String>();
			int index = 0;
			for (String fileName: matchedFiles) {
				if (index < howManyCopies) {
					keepList.add(fileName);
				} else {
					removeList.add(fileName);
				}
				index++;
				
			}
			
			System.out.println("Keep list: " + keepList);
			System.out.println("Remove list: " + removeList);
			
			removeList.stream()
				.map(name -> new String(dirPath + File.separator + name))
				.forEach(KeepLast5::deleteFile);
		
		} else {
			System.out.println("Directory: " + dirPath + " does not exist!!!");
		}
		
	}
	
	private static boolean deleteFile(String fileName) {
		System.out.println("Removing file: " + fileName);
		File myObj = new File(fileName); 
	    return myObj.delete();
	}
	
}
