<img src="https://img.shields.io/github/repo-size/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/downloads/Jachou-yt/JavaVersionManager/total" alt="Downloads"> <img src="https://img.shields.io/github/issues/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/sponsors/Jachou-yt" alt=""> <img src="https://img.shields.io/github/license/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/v/release/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/last-commit/Jachou-yt/JavaVersionManager" alt=""><img src="https://img.shields.io/github/contributors/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/languages/code-size/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/languages/top/Jachou-yt/JavaVersionManager" alt="">

# JavaVersionManager
JavaVersionManager is a Java library that allows you to download Java version and manage it. It is available in API and executable version.
<br>
*Warning: This library is still in development, so it is not yet stable. See the branch [Features]("https://github.com/Jachou-yt/JavaVersionManager/tree/features") for the updates*

# JavaDoc
You can see javadoc [here](https://jvm.chiss.fr/) !

# How to use it
## Executable Version
1. Download the latest release from [here](https://github.com/Jachou-yt/JavaVersionManager/releases/)
2. Open the file
3. Follow the instructions
4. Enjoy!
## API Version
### Download manually
1. Download the latest release from [here](https://github.com/Jachou-yt/JavaVersionManager/releases/)
2. Add the file to your project
3. Read the documentation
4. Enjoy!
### Maven/Gradle method
```xml
<dependency>
  <groupId>fr.jachou</groupId>
  <artifactId>javaversionmanager</artifactId>
  <version>VERSION</version>
</dependency>
```
See more infomartions [here](https://github.com/Jachou-yt/JavaVersionManager/packages)

# Example

## Download Method
In this example we will download the __Java 8__ version in the folder *"C:\\Program Files\\Java\\jdk1.8.0_271"*.<br>
After that we will unzip the file.
```java
JavaVersionManager jvm = new JavaVersionManager();
jvm.downloadVersion(JavaVersionList.JAVA_8, "C:\\Program Files\\Java\\jdk1.8.0_271");
jvm.unzipJavaVersion() 
```

## Unzip Method
In this exemple we will unzip the __Java 16__ version in the folder *"path"*.
```java
JavaVersionManager jvm = new JavaVersionManager();
jvm.unzipJavaVersion(path, JavaVersionList.JAVA_16); 
```


# Documentation
You can find the documentation [here](https://)

# License
This project is under the [MIT](https://github.com/Jachou-yt/JavaVersionManager/blob/master/LICENSE)

# Contributors
Owner : [__@Jachou-yt__](https://github.com/Jachou-yt)<br>
