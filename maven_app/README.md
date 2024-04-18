
```
mvn archetype:generate -DgroupId=org.jacekkowalczyk82.tools -DartifactId=application-hello -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=true
```

## Main properties 

```
<properties>  
 <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>  
 <maven.compiler.source>17</maven.compiler.source>  
 <maven.compiler.target>17</maven.compiler.target>  
 <java.version>17</java.version>  


 <org.mapstruct.version>1.5.5.Final</org.mapstruct.version>  
 <org.projectlombok.version>1.18.30</org.projectlombok.version>  
 
</properties>

```

## Dependencies

```

<dependency>  
 <groupId>ch.qos.logback</groupId>  
 <artifactId>logback-core</artifactId>  
 <version>1.3.5</version>  
</dependency>  
  
<dependency>  
 <groupId>ch.qos.logback</groupId>  
 <artifactId>logback-classic</artifactId>  
 <version>1.3.5</version>  
</dependency>  
  
<dependency>  
 <groupId>org.slf4j</groupId>  
 <artifactId>slf4j-api</artifactId>  
 <version>2.0.4</version>  
</dependency>  
  
<dependency>  
 <groupId>org.apache.commons</groupId>  
 <artifactId>commons-text</artifactId>  
 <version>1.10.0</version>  
</dependency>  
  
  <!-- https://mvnrepository.com/artifact/commons-cli/commons-cli -->
<dependency>
    <groupId>commons-cli</groupId>
    <artifactId>commons-cli</artifactId>
    <version>1.6.0</version>
</dependency>

<dependency>  
 <groupId>org.apache.commons</groupId>  
 <artifactId>commons-lang3</artifactId>  
 <version>3.12.0</version>  
</dependency>  
  
<dependency>  
 <groupId>commons-collections</groupId>  
 <artifactId>commons-collections</artifactId>  
 <version>3.2.2</version>  
</dependency>  
  
<dependency>  
 <groupId>commons-io</groupId>  
 <artifactId>commons-io</artifactId>  
 <version>2.15.1</version>  
</dependency>  
  
<dependency>  
 <groupId>org.projectlombok</groupId>  
 <artifactId>lombok</artifactId>  
 <version>1.18.24</version>  
 <scope>provided</scope>  
</dependency>  
  
<dependency>  
 <groupId>org.junit.jupiter</groupId>  
 <artifactId>junit-jupiter-api</artifactId>  
 <version>5.9.1</version>  
  
</dependency>  
<dependency>  
 <groupId>org.junit.jupiter</groupId>  
 <artifactId>junit-jupiter-engine</artifactId>  
 <version>5.9.1</version>  
  
</dependency>  
<dependency>  
 <groupId>org.junit.jupiter</groupId>  
 <artifactId>junit-jupiter-params</artifactId>  
 <version>5.9.1</version>  
  
</dependency>  
<dependency>  
 <groupId>org.junit.platform</groupId>  
 <artifactId>junit-platform-launcher</artifactId>  
 <version>1.9.1</version>  
</dependency>  
  
<dependency>  
 <groupId>org.junit.platform</groupId>  
 <artifactId>junit-platform-console</artifactId>  
 <version>1.9.1</version>  
</dependency>


<dependency>  
 <groupId>org.apache.logging.log4j</groupId>  
 <artifactId>log4j-api</artifactId>  
 <version>2.23.1</version>  
</dependency>  
  
<dependency>  
 <groupId>org.apache.logging.log4j</groupId>  
 <artifactId>log4j-core</artifactId>  
 <version>2.23.1</version>  
</dependency>


```


## imports

```
import org.junit.jupiter.api.AfterAll;  
import org.junit.jupiter.api.AfterEach;  
import org.junit.jupiter.api.Assertions;  
import org.junit.jupiter.api.BeforeAll;  
import org.junit.jupiter.api.BeforeEach;  
import org.junit.jupiter.api.MethodOrderer;  
import org.junit.jupiter.api.Order;  
import org.junit.jupiter.api.Tag;  
import org.junit.jupiter.api.TestInfo;  
import org.junit.jupiter.api.TestMethodOrder;  
import org.junit.jupiter.params.ParameterizedTest;  
import org.junit.jupiter.params.provider.Arguments;  
import org.junit.jupiter.params.provider.MethodSource;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;

```

## Logger

```
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


private static Logger LOGGER = LogManager.getLogger(App.class);

```