# AsciiDoc Conversion Manual  
  
  
  
## 1. Required tools to install  
  
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
  
 
  
   
## Conversion to DOCX  
  
The conversion from asciidoc file to DOCX requires to use both tools:  
  
```
asciidoctorj.bat -b docbook input-file.adoc  
# this generates docbook file: input-file.xml  
  
pandoc.exe -s -r docbook -t docx -o my-DOXC-document.docx input-file.xml  

```


----


## Solid table borders solution 1 

```
# https://github.com/jgm/pandoc/issues/3275
pandoc.exe --print-default-data-file example.docx > myref-SOLID_TABLE_BORDERS.docx

# edit Table style and set table borders

pandoc.exe -s  -r docbook -t docx --reference-doc=myref-SOLID_TABLE_BORDERS.docx -o example-solid-table-borders.docx example.xml
```

## Solid table borders solution 2

https://github.com/jacekkowalczyk82/add-solid-table-borders

```


pandoc.exe -s  -r docbook -t docx  -o example.docx example.xml


solidtableborders-0.1-20240521\bin\solidtableborders.bat example.docx

```

## Set docx landscape layout 

```

# and to set landscape layout use:
landscapelayout-0.1-20240522\bin\landscapelayout.bat example.docx_fixed_solidborders.docx

```


## AsciiDoc to Markdown

```
asciidoctorj.bat -b docbook example.adoc

# this generates example.xml docbook


pandoc.exe -s -f docbook  -t markdown -o example.md example.xml

# markdown_strict
pandoc.exe -s -f docbook  -t markdown_strict -o example_strict.md example.xml


```


## Markdown to docx


```
pandoc.exe -s -t docx -o example.md.docx example.md

pandoc.exe -s -t docx -o pandoc-manual.docx pandoc-manual.md


```


## Asciidoc to reST


```

pandoc -f asciidoc -t rst -o example.rst example.adoc

```


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


