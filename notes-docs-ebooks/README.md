# Here are some notes regarding topics related to making notes in markdown, asccidoc and writing online books or ebooks

## E-books and online books

* https://github.com/DahlitzFlorian/ebook-template
* https://flaviocopes.com/how-to-create-ebooks-markdown/
* https://leanpub.com/
* [gitbook](https://app.gitbook.com/)
* https://github.com/k2052/markdown-to-ebook
* pandoc 
* https://github.com/hongtaoh/onlinebook
* https://github.com/hongtaoh/onlinebook?tab=readme-ov-file#13-create-your-own-content
* 

### pandoc

https://pandoc.org/epub.html

pandoc -o progit.epub title.txt \
  01-introduction/01-chapter1.markdown \
  02-git-basics/01-chapter2.markdown \
  03-git-branching/01-chapter3.markdown \
  04-git-server/01-chapter4.markdown \
  05-distributed-git/01-chapter5.markdown \
  06-git-tools/01-chapter6.markdown \
  07-customizing-git/01-chapter7.markdown \
  08-git-and-other-scms/01-chapter8.markdown \
  09-git-internals/01-chapter9.markdown

