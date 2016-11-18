# HackDuke Backend Workshop
HackDuke workshop on Gradle, Java Micro Web Frameworks, Redis, IntelliJ Idea. 
Let's you get started building your first web API. 

## Topics Covered

* Installing IntelliJ
* Installing Gradle
* What is Gradle?
* What are Web Frameworks?
* What is Redis?
* What is a web API?
* Hit-counter API
* (Advanced) Other web frameworks
* (Advanced) Relational Databases
* (Advanced) Deploying your app

## Knowledge Prerequisites

* Basic understanding of Java
* Basic understanding of Git - understand the following commands:
** git clone https://repo_url (clone new repo)
** git pull origin remote-branch (pull from remote branch)
** git push origin remote-branch (pull from remote branch)
** git add . (add untracked files to staging area)
** git commit (commits staged files)
** git checkout branch-name (checkout branch or commit)
** git checkout -b branch-name (creates new branch)
** git stash (moves staged changes to top of stash)
** git clear -fd (removes untracked files)

## Software Prerequisites

* Java JDK 8+
** OSX: https://java.com/en/download/help/mac_install.xml
** Windows: https://www.java.com/en/download/help/download_options.xml#windows
* Git
** OSX: https://git-scm.com/downloads
** Windows: https://git-scm.com/downloads
* IntelliJ 2016 (Community Edition and Ultimate Edition are both fine)
** OSX: https://www.jetbrains.com/idea/download/
** Windows: https://www.jetbrains.com/idea/download/
* Gradle 2.14.1 (Do not use version 3.X)
** OSX: 

```
brew tap homebrew/versions
brew search gradle
brew install homebrew/versions/gradleXXX
gradle -version
brew link --overwrite gradleXXX
gradle -version
```

** Windows: http://www.bryanlor.com/blog/gradle-tutorial-how-install-gradle-windows
* Redis
** OSX:

```
brew install redis
```

** Windows: http://redis.io/download