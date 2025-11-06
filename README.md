# 2D Game Java

A 2D game project built with Java.

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## Building the Project

To compile the project:
```bash
mvn compile
```

To run the application:
```bash
mvn exec:java -Dexec.mainClass="com.game.Main"
```

Or compile and run manually:
```bash
mvn compile
java -cp target/classes com.game.Main
```

## Running Tests

```bash
mvn test
```

## Project Structure

```
.
├── pom.xml                 # Maven configuration
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── game/
│   │               └── Main.java
│   └── test/
│       └── java/          # Test files go here
└── target/                # Compiled classes (generated)
```

## Development

The main entry point is `com.game.Main`. Start developing your game logic from there.

