# category-service

## ๐๏ธ ์ค์น/๋น๋ ๋ฐฉ๋ฒ

### Centos ๊ธฐ์ค

### Jdk ์ค์น

> ์ค์น ๊ฐ๋ฅํ jdk ์กฐํ <br>
> `yum list java*jdk-devel` <br><br>
![img_1.png](images/img_1.png)

> jdk 11 ์ค์น <br>
> `yum install java-11-openjdk-devel.x86_64`

> ์ ์ ์ค์น ํ์ธ <br>
> `java --version` <br> <br>
![img.png](images/img.png)

### Java home ์ค์ 

> java home ๊ฒฝ๋ก ์ถ๊ฐ <br>
> `vi /etc/profile`
> 
> `readlink -f /usr/bin/javac`
> 
> `export JAVA_HOME=/lib/jvm/java-11-openjdk-11.0.10.0.9-1.el7_9.x86_64`
> 
> `source /etc/profile`
> 
> `echo $JAVA_HOME`

### Grade ์ค์น

> unzip ์ค์น <br>
> `yum install unzip` <br>
> `yum install wget`
> 
> wget์ ์ด์ฉํด์ gradle.zip ๊ฐ์ ธ์ค๊ธฐ  <br>
> `wget https://services.gradle.org/distributions/gradle-7.4.1-bin.zip` <br>
> 
> /opt/gradle ๋๋ ํ ๋ฆฌ ์์ฑ <br>
> `mkdir /opt/gradle` <br>
> 
> ์์ถํผ ํ์ผ /opt/gradle ๊ฒฝ๋ก๋ก ์ด๋ <br>
> `unzip -d /opt/gradle gradle-7.4.1-bin.zip`
> 
> ์คํฌ๋ฆฝํธ ์คํ ๊ฐ๋ฅํ๋๋ก ์ค์  <br>
> `chmod +x /etc/profile.d/gradle.sh` <br>
> 
> ์ค์  ํ ๋ช๋ น์ด ์๋ ฅ <br>
> `source /etc/profile.d/gradle.sh`

### Git ํ๋ก์ ํธ ๋ฐ๊ธฐ 

> Git ์ค์น <br>
> `yum install git` <br>
> 
> Git clone ๋ฐ์ ๋๋ ํ ๋ฆฌ ์์ฑ <br>
> `mkdir app` <br>
> `mkdir app/git` <br>
>
> Git ํ๋ก์ ํธ ๋ฐ๊ธฐ <br> 
> `cd app/git` <br>
> `git clone https://github.com/parkhanbeen/category-service.git` <br>

### ์คํ

> ํ๋ก์ ํธ ๋๋ ํ ๋ฆฌ๋ก ์ด๋ <br>
> `cd /category-service`  
> 
> ํ๋ก์ ํธ ๋น๋ <br>
> `./gradlew clean build` <br>
> 
> ํ๋ก์ ํธ ์คํ <br>
> `./gradlew bootRun ๋๋ java -jar build/libs/category-service.jar` <br> <br>
![img_2.png](images/img_2.png)


## ๐  ์ฌ์ฉ ๊ธฐ์ 

๊ธฐ์ |์คํ
---|---|
Language| java 11
Framework|Spring Boot
ORM|JPA, Querydsl
Build Tool|Gradle
API ๋ฌธ์|Spring RestDocs
TEST|Junit5, Mockito
Database|H2 db

