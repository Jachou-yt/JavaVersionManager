<img src="https://img.shields.io/github/repo-size/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/downloads/Jachou-yt/JavaVersionManager/total" alt="Downloads"> <img src="https://img.shields.io/github/issues/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/sponsors/Jachou-yt" alt=""><img src="https://img.shields.io/github/license/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/v/release/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/last-commit/Jachou-yt/JavaVersionManager" alt=""><img src="https://img.shields.io/github/contributors/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/languages/code-size/Jachou-yt/JavaVersionManager" alt=""> <img src="https://img.shields.io/github/languages/top/Jachou-yt/JavaVersionManager" alt="">

# JavaVersionManager
JavaVersionManager is a Java library that allows you to download Java version and manage it. It is available in API and executable version.
<br>
*Warning: This library is still in development, so it is not yet stable.*

# Features branch Before V1.0.0
- [ ] Ajouter une fonctionnalité de recherche automatique des dernières versions de Java disponibles. Vous pouvez interroger un service en ligne ou une API pour obtenir les informations sur les dernières versions publiées.


- [x] Permettre à l'utilisateur de choisir le répertoire de destination pour le téléchargement et l'installation des versions de Java. Actuellement, le chemin est fixé sur System.getProperty("user.home") + "/.jdks", mais il serait utile de laisser l'utilisateur spécifier un chemin personnalisé.


- [ ] Ajouter une fonctionnalité de gestion des versions installées. Vous pouvez afficher la liste des versions téléchargées et installées dans le répertoire choisi par l'utilisateur. Permettez également à l'utilisateur de choisir quelle version de Java utiliser par défaut.


- [ ] Implémenter une fonctionnalité de mise à jour automatique. Vous pouvez vérifier régulièrement les nouvelles versions de Java disponibles et proposer à l'utilisateur de mettre à jour sa version actuelle si une nouvelle version est disponible.


- [ ] Ajouter des options avancées pour la gestion des versions de Java, telles que la possibilité de supprimer une version spécifique, de restaurer une version précédente, ou de désinstaller complètement une version.


- [ ] Améliorer l'interface utilisateur en utilisant des bibliothèques graphiques telles que JavaFX ou Swing pour créer une interface graphique conviviale et intuitive.


- [ ] Gérer les erreurs et les exceptions de manière plus robuste. Actuellement, certaines erreurs sont simplement affichées en console, mais vous pouvez ajouter des mécanismes de gestion des erreurs plus sophistiqués, tels que l'enregistrement des erreurs dans un fichier de journal ou l'affichage de messages d'erreur plus explicites à l'utilisateur.


- [ ] Ajouter une fonctionnalité de vérification de l'intégrité des fichiers téléchargés en comparant les sommes de contrôle (checksums). Cela permet de s'assurer que les fichiers téléchargés ne sont pas corrompus ou altérés.


- [ ] Implémenter une fonctionnalité de recherche dans les versions téléchargées. Permettez à l'utilisateur de rechercher une version spécifique en utilisant des filtres tels que le numéro de version, la date de publication, etc.


- [ ] Ajouter des tests unitaires pour valider le bon fonctionnement de votre code et éviter les régressions.

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
