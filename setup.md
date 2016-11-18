# Setup

To follow along during the workshop, please install the following. If you run into trouble, get here 15 minutes early and I will help you.

* Git
* Java 8
* Gradle 2.14.1 (not version 3)
* PostgreSQL (version 9.4+)
* IntelliJ Idea (Community or Ultimate are both fine)

## Git

* Windows: https://git-scm.com/downloads
* OS X:

```
Open terminal
Type in `git`
Cry when it prompts you to install xcode command line tools
Accept that you have bought into the ecosystem and are stuck; use the installer that was clearly never changed since OS X Lion
```

## Java 8

* Just google it

## Gradle 2.14.1

* Windows: https://docs.gradle.org/current/userguide/installation.html
* OS X:

```
Install brew
brew search gradle
brew install homebrew/versions/gradleXXX
```

## PostgreSQL

* Windows: https://www.postgresql.org/download/windows/
* OS X: https://www.postgresql.org/download/macosx/

Leave username as `postgres`
Set password as `password`
Create database called `workshop`
```
# Windows
# within psql use:
create database workshop;

# OS X
# within terminal
sudo su postgres
createdb workshop
```

## IntelliJ

* https://www.jetbrains.com/idea/download/