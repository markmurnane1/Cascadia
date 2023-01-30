# Cascadia Project

## Todo
* Replace "tempgroup" in code with our group name when we get it
* Change name of github repo to our group name also

## Development

#### Dependencies
You need to install [maven](https://maven.apache.org/download.cgi)

On mac, if you have [homebrew](https://brew.sh/) installed, you can install maven with
```brew install maven```

#### Commands

To compile and run the program you can run the following command:
```
mvn package -Dmaven.test.skip.exec=true && java -jar ./target/cascadia-0.0.1.jar
```