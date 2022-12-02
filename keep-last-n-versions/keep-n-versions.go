package main

import (
	"fmt"
	"io/fs"
	"io/ioutil"
	"log"
	"os"
	"path/filepath"
	"regexp"
	"sort"
	"strconv"
)

/*
Usage:

  keep-n-versions directory_path file_names_regex number_of_versions
  //to keep only the given number of versionsof files matching pattern in the given directory path, older files will be deleted

  keep-n-versions directory_path file_names_regex number_of_versions [archive_directory]
  //to keep only the given number of versionsof files matching pattern in the given directory path, older files will be moved to the archive directory

*/

func GetFilesInDir(dirPath string) []fs.FileInfo {
	files, err := ioutil.ReadDir(dirPath)
	if err != nil {
		log.Fatal(err)
	}

	for _, f := range files {
		fmt.Println(f.Name())
	}
	return files
}

func reverse(strings []string) []string {
	for i, j := 0, len(strings)-1; i < j; i, j = i+1, j-1 {
		strings[i], strings[j] = strings[j], strings[i]
	}
	return strings
}

func showUsage() {
	fmt.Println("\n")
	fmt.Println("keep-last-n-versions - Application keeps n last versions of files " +
		"matching given file pattern in the given directory")
	fmt.Println("                       Versioned archive files should have the " +
		"filenames containing timestamp in format " +
		"yyyy-MM-dd__HH-mm-SS,\n                       for example 2022-12-02__11-39-05.\n")
	fmt.Println("Usage:")
	fmt.Println("    keep-n-versions directory_path file_names_regex number_of_versions\n" +
		"      to keep only the given number of versionsof files matching pattern in the given directory path, older files will be deleted")
	fmt.Println("    keep-n-versions directory_path file_names_regex number_of_versions [archive_directory]\n" +
		"      to keep only the given number of versionsof files matching pattern in the given directory path, older files will be moved to the archive directory")

	fmt.Println("\nExamples:")
	fmt.Println("  ./keep-last-n-versions  ~/temp/test-versioned-files/ \"notes_version_.*.tar.gz\" 5  ~/temp/test-dir-archived/")
	fmt.Println("  ./keep-last-n-versions  ~/temp/test-versioned-files/ \"keep-last-n-versions_version_[-_0-9]*.tar.gz\" 5  ~/temp/test-dir-archived/	")

	fmt.Println("\nAll aguments you provided: ")
	fmt.Println(os.Args)

}

func removeFiles(dirPath string, fileNames []string) {
	for _, file := range fileNames {
		fullFilePath := dirPath + string(filepath.Separator) + file
		fmt.Println("Removing file " + fullFilePath)
		e := os.Remove(fullFilePath)
		if e != nil {
			log.Fatal(e)
		}
	}
}

func archiveFiles(dirPath string, fileNames []string, archiveDirPath string) {
	for _, file := range fileNames {
		fullFilePath := dirPath + string(filepath.Separator) + file
		archivedFilePath := archiveDirPath + string(filepath.Separator) + file
		fmt.Println("Moving file " + fullFilePath + " ==> " + archivedFilePath)
		err := os.Rename(fullFilePath, archivedFilePath)
		if err != nil {
			log.Fatal(err)
		}
	}
}

//keep-last-n-versions - keeps n last versions of files matching given file pattern in the given directory
func main() {

	//argsWithProg := os.Args
	//argsWithoutProg := os.Args[1:]

	//arg := os.Args[3]

	//fmt.Println(argsWithProg)
	//fmt.Println(argsWithoutProg)
	//fmt.Println(arg)

	if len(os.Args) > 3 {
		versionedFilesDirectory := os.Args[1]
		fileNamesRegex := os.Args[2]
		numberOfVersions := os.Args[3]
		archiveOlderFiles := false
		archivedFilesDirectory := ""

		fmt.Println("\nApliction will keep " + numberOfVersions + " of files matching: " + fileNamesRegex + " in directory: " + versionedFilesDirectory)
		if len(os.Args) > 4 {
			archivedFilesDirectory = os.Args[4]
			archiveOlderFiles = true
			fmt.Println("Older files will be moved to archive directory: " + archivedFilesDirectory)
		} else {
			fmt.Println("Older files will be deleted!!!")
		}

		if archiveOlderFiles == true {
			fmt.Println("Older files will be archived!!!")
		}

		fmt.Println("\nAll aguments you provided: ")
		fmt.Println(os.Args)

		fmt.Println("\n\n===========================================")

		allFiles := GetFilesInDir(versionedFilesDirectory)

		regexForVersionedFile, _ := regexp.Compile(fileNamesRegex)

		//get only file names
		fileNames := make([]string, 0)
		for _, f := range allFiles {
			//if maching to the regex
			if regexForVersionedFile.MatchString(f.Name()) {
				fileNames = append(fileNames, f.Name())
			} else {
				fmt.Println("Not matching file: " + f.Name())
			}

		}

		fmt.Println("\nBefore sorting ")

		///debug test code
		// fileNames = append(fileNames, "keep-last-n-versions_version_2000-01-01__12-05-00.tar.gz")
		// for _, f := range fileNames {
		// 	fmt.Println(f)
		// }
		//debug test code end

		sort.Strings(fileNames)
		fmt.Println("\nAfter sorting ")
		for _, f := range fileNames {
			fmt.Println(f)
		}

		reversedFileNames := reverse(fileNames)

		fmt.Println("\nAfter Reversing  ")
		for _, f := range reversedFileNames {
			fmt.Println(f)
		}

		filesToKeep := make([]string, 0)
		filesToRemoveOrArchive := make([]string, 0)
		howManyFilesToKeep, err := strconv.Atoi(numberOfVersions)
		//handle erros if any
		if err != nil {
			log.Fatal(err)
		}

		if len(reversedFileNames) < howManyFilesToKeep {
			howManyFilesToKeep = len(reversedFileNames)
		}

		fmt.Println("DEBUG::numberOfVersions: ", numberOfVersions)
		fmt.Println("DEBUG::len(reversedFileNames): ", len(reversedFileNames))
		fmt.Println("DEBUG::howManyFilesToKeep: ", howManyFilesToKeep)

		for i := 0; i < howManyFilesToKeep; i++ {
			filesToKeep = append(filesToKeep, reversedFileNames[i])
		}

		fmt.Println("\nFiles to keep:")
		for index, f := range filesToKeep {
			fmt.Println(index+1, f)
		}

		fmt.Println("\nFiles to remove/archive:")
		for i := howManyFilesToKeep; i < len(reversedFileNames); i++ {
			fmt.Println(reversedFileNames[i])
			filesToRemoveOrArchive = append(filesToRemoveOrArchive, reversedFileNames[i])
		}

		if archiveOlderFiles {
			archiveFiles(versionedFilesDirectory, filesToRemoveOrArchive, archivedFilesDirectory)
		} else {
			removeFiles(versionedFilesDirectory, filesToRemoveOrArchive)
		}
	} else {
		fmt.Println("\nERROR:Too less parametsrs provided")
		showUsage()
	}

	fmt.Println("\n\n===========================================")

}
