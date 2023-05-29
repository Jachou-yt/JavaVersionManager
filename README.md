# JavaVersionManager
A simple tool to manage multiple Java versions.
<br>
Available for Windows, Linux and Mac.
<br>
Download the latest release [here](https://github.com/Jachou-yt/JavaVersionManager/releases/)

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
  <version>0.0.1</version>
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
