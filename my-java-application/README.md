# Java application with gradle

This is a very short intro how to create a starting template of java application with gradle 

## SDKMan

* https://sdkman.io/


```
curl -s "https://get.sdkman.io" | bash
```


## Gradle

* https://gradle.org/install/

```

sudo apt install gradle

# or
sdk install gradle

# 
which gradle
/home/jacek/.sdkman/candidates/gradle/current/bin/gradle

# 
gradle --version 

Welcome to Gradle 6.8.3!



```

## Java application with gradle


* https://docs.gradle.org/current/samples/sample_building_java_applications.html

```
mkdir my-java-application
cd my-java-application
gradle init

```

Here is the output of the gradlew init with my example choices: 

```

Starting a Gradle Daemon (subsequent builds will be faster)
                                        
Select type of project to generate:
  1: basic
  2: application  
  3: library                                                                    
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2    
                                                                                
Select implementation language:
  1: C++    
  2: Groovy                                                                                                                                                     
  3: Java
  4: Kotlin               
  5: Scala                    
  6: Swift                                                                      
Enter selection (default: Java) [1..6] 3                                        
                                                                                
Split functionality across multiple subprojects?: 
  1: no - only one application project                                                                                                                          
  2: yes - application and library projects           
Enter selection (default: no - only one application project) [1..2] 1           
                                        
Select build script DSL:                                                        
  1: Groovy                                                                     
  2: Kotlin                                                                     
Enter selection (default: Groovy) [1..2] 1                                      
                                                                                                                                                                
Select test framework:                                                          
  1: JUnit 4                            
  2: TestNG                        
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit 4) [1..4]                                       
                                        
Project name (default: my-java-application): 
Source package (default: my.java.application):                                  
                                        
> Task :init
Get more help with your project: https://docs.gradle.org/6.8.3/samples/sample_building_java_applications.html                                                   
                                        
BUILD SUCCESSFUL in 2m 21s
2 actionable tasks: 2 executed
 
```

## Building and running 

```
# you can use the default system gradle
gradlew clean build 

# or use the gradlew wrapper delivered in the repository 
./gradlew clean build 

# running directly by gradle
gradle run 

# installing application and shell/cmd scripts to run app with thees scripts
./gradlew installDist

# running application with the generated script
bash app/build/install/my-java-application/bin/my-java-application

```

