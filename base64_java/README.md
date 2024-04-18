## Java Base64 

```java
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// https://www.baeldung.com/java-base64-encode-and-decode

public class MyBase64 {


    public static void encodeBase64(String originalInput) {
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
        System.out.println(originalInput + " encoded to Base64: " + encodedString);
    }

    public static void decodeBase64(String base64String) {
        byte [] decodedStringBytes = Base64.getDecoder().decode(base64String);
        String decodedString = new String(decodedStringBytes);
        System.out.println("Base64: " + base64String + " Decoded: " + decodedString);
    }

    public static void main(String[] args) {
        encodeBase64("trudnehaslo");

        
        decodeBase64("dHJ1ZG5laGFzbG8="); 

    }
}
```

