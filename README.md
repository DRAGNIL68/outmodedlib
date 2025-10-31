The outmodedlib is not even close to being finished.

current features:
- custom item system
- texture pack generation system
- custom gui system
- negative offsets
- custom particle system

maven
```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
```
	<dependency>
	    <groupId>com.github.DRAGNIL68</groupId>
	    <artifactId>outmodedlib</artifactId>
	    <version>v1.4.1</version>
	    <scope>provided</scope>
	</dependency>
```
gradle
```
	dependencyResolutionManagement {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		repositories {
			mavenCentral()
			maven { url 'https://jitpack.io' }
		}
	}
```
```

	dependencies {
	        implementation 'com.github.DRAGNIL68:outmodedlib:v1.4.1'
	}
```
