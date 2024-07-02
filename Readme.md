# Translation App

## About the Project
A Translation App using Java, which seamlessly integrates with the WorldLingo API to provide accurate translations. The app accepts user input in the form of source text, source language, and destination language parameters, and then leverages the API to retrieve the translated output. The resulting translation is then returned to the user with minimal delay, making it an intuitive tool for effortless language conversion.

## Tech Stack
- Java
- Maven
- JUnit

## Application Usage

    java -jar TranslateTXML.jar –f [path to source txml file] –o [output path]  -s [source language] –t [target language] [-e]

### Example 1

    java -jar TranslateTXML.jar -f "source.txml" -o "output.txml" -s "en" -t "es" -e

### Example 2

    java -jar TranslateTXML.jar -f "source.txml" -o "output.txml" -s "en" -t "es"

### Notes
config.properties needs to be in the same directory as TranslateTXML.jar before TranslateTXML.jar can be ran.