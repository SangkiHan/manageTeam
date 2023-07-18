plugins {
	id 'java'
	id 'org.springframework.boot' version '2.7.12'
	id 'io.spring.dependency-management' version '1.0.15.RELEASE'
	id 'com.ewerk.gradle.plugins.querydsl' version '1.0.10'	
	id 'war'
}

group = 'com.manageTeam'
version = '0.0.1-SNAPSHOT' 
apply plugin: 'war'

java {
	sourceCompatibility = 11
	targetCompatibility = 11
}

repositories {
	mavenCentral()
}


dependencies {
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
	implementation 'javax.servlet:jstl'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation "com.querydsl:querydsl-jpa"
    implementation "com.querydsl:querydsl-apt:5.0.0"
	implementation 'org.springframework.boot:spring-boot-starter-web'
	implementation group: 'mysql', name: 'mysql-connector-java', version: '8.0.32'		//mysql
	implementation 'io.springfox:springfox-boot-starter:3.0.0'
	implementation group: 'com.fasterxml.jackson.datatype', name: 'jackson-datatype-hibernate5'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	compileOnly 'org.projectlombok:lombok:1.18.20'
    annotationProcessor 'org.projectlombok:lombok'
    implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0'
    implementation 'org.springframework.boot:spring-boot-starter-log4j2'
}

tasks.named('test') {
	useJUnitPlatform()
}

def querydslDir = "$buildDir/generated/querydsl"

querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}

sourceSets {
    main.java.srcDir querydslDir
}
configurations {
	querydsl.extendsFrom compileClasspath
	all {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
    }
}

compileQuerydsl{
    options.annotationProcessorPath = configurations.querydsl
}
