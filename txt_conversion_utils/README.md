# AsciiDoc Conversion Manual  
  
  
  
## 1. Required tools  
  
You will need to download two things:  
  
* Pandoc - https://pandoc.org/, Recommended ZIP distribution.footnote:[It is recommended to download ZIP distribution, In my case it was https://github.com/jgm/pandoc/releases/tag/3.1.8.]  
* Ascidoctor (Recommended java binding asciidoctorj.footnote:[https://github.com/asciidoctor/asciidoctorj])  


### asciidoctorj  
  
Distribution ZIP can be downloaded from http://search.maven.org/remotecontent?filepath=org/asciidoctor/asciidoctorj/2.5.7/asciidoctorj-2.5.7-bin.zip. It does not work inside CoBa network. I downloaded it on private computer put it to AWS S3 storage and downloaded from there.  
  
 
## Conversion from markdown to asciidoc 

```
pandoc -s MANUAL.txt.md -t asciidoc -o example28.txt.adoc
```
 
## Conversion to PDF  
  
The conversion from asciidoc file to PDF is very simple:  
  
``` 
asciidoctorj.bat -b pdf input-file.adoc  
```
  
Examples:  
```
cd C:\docs\AsciiDocMarkdownDocs  
  
C:\dev\install\asciidoctorj-2.5.8\bin\asciidoctorj.bat -b pdf HowToConvertAsciiDoc.adoc  
  


```  
  
   
## Conversion to DOCX  
  
The conversion from asciidoc file to DOCX requires to use both tools:  
  
```
asciidoctorj.bat -b docbook input-file.adoc  
# this generates docbook file: input-file.xml  
  
pandoc.exe -s -r docbook -t docx -o my-DOXC-document.docx input-file.xml  

```


----

## Other notes


## native ascidoc to PDF converter 


https://docs.asciidoctor.org/pdf-converter/latest/



## Ruby gems ascidoctor 

https://rubyinstaller.org/

https://docs.asciidoctor.org/pdf-converter/latest/install/

## Asciidoc toools 

https://gitlab.eclipse.org/eclipse-wg/asciidoc-wg/asciidoc.org/-/blob/main/awesome-asciidoc.adoc#convert

### Go library 

https://github.com/bytesparadise/libasciidoc

### java library

https://github.com/asciidoctor/asciidoctorj

http://search.maven.org/#artifactdetails%7Corg.asciidoctor%7Casciidoctorj%7C2.5.7%7Cjar


