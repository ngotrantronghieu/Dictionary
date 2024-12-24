# DictionaryPro

DictionaryPro is a desktop dictionary application built with Java and JavaFX. It allows users to look up word definitions, translate text using an external API, and hear the pronunciation of words.

## Features

*   **Word Lookup:** Search for definitions of English words.
*   **API-Based Translation:** Translate text between English and other languages using an integrated API.
*   **Text-to-Speech:** Hear the pronunciation of words using a built-in text-to-speech engine.
*   **User-Friendly Interface:** Navigate the application easily with a clean and intuitive JavaFX-based UI.
*   **Add, Remove, Change:** Add new words to the dictionary, remove existing words, or modify definitions.

## Installation

1. **Prerequisites:**
    *   Java Development Kit (JDK) 17 or later
    *   Maven

2. **Build the Project:**
    *   Clone the repository: `git clone https://github.com/yourusername/DictionaryPro.git` (Replace with the actual repository URL if available)
    *   Navigate to the project directory: `cd DictionaryPro`
    *   Build the project using Maven: `mvn clean install`

3. **Run the Application:**
    *   Execute the following command in the project directory: `mvn javafx:run`

## Usage

1. **Launch the application.**
2. **Word Lookup:**
    *   Enter the word you want to look up in the search bar.
    *   Press Enter or click the search button.
    *   The definition will be displayed in the main window.
3. **Translation:**
    *   Click on the "Translate" tab.
    *   Enter the text you want to translate in the input field.
    *   Select the source and target languages.
    *   Click the "Translate" button.
    *   The translated text will appear in the output field.
4. **Text-to-Speech:**
    *   After looking up a word, click the "Speak" button to hear its pronunciation.

## Dependencies

The project uses the following external libraries:

*   **FreeTTS:** A speech synthesis engine for Java.
*   **JLayer:** An MP3 player for Java.
*   **JavaFX:** A set of graphics and media packages for creating desktop applications.
*   **Other libraries** as listed in the `pom.xml` file.