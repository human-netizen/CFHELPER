# GabFosol - Codeforces Problem Manager

A JavaFX desktop application that helps users manage and track Codeforces programming problems. The application provides an intuitive interface for browsing problems by rating and managing your problem-solving progress.

## Features

- **Problem Browser**: Browse Codeforces problems filtered by rating
- **Interactive UI**: Each problem entry includes:
  - Direct link to the problem
  - Custom notes field
  - Action buttons for problem management
- **Authentication System**: Secure login and signup functionality
- **API Integration**: Real-time problem fetching from Codeforces API

## Technologies Used

- Java 21
- JavaFX 21 for the GUI
- Maven for dependency management
- JSON libraries for API integration:
  - org.json
  - android-json
  - jetty-util-ajax

## Requirements

- Java Development Kit (JDK) 21 or higher
- Maven 3.x

## Installation

1. Clone the repository
2. Navigate to the project directory
3. Build the project using Maven:
```bash
mvn clean install
```

## Running the Application

You can run the application using Maven:

```bash
mvn javafx:run
```

Or run the generated JAR file:

```bash
java -jar target/gabFosol-1.0-SNAPSHOT.jar
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/gabfosol/
│   │       ├── HelloApplication.java    # Main application entry
│   │       ├── UnsolvedList.java        # Problem list management
│   │       ├── Login.java               # Authentication
│   │       ├── Signup.java             # User registration
│   │       └── JsonParse.java          # API integration
│   └── resources/
│       └── com/example/gabfosol/
│           ├── UnsolvedList.fxml       # UI layout files
│           ├── Login.fxml
│           └── Signup.fxml
```

## Features in Detail

### Problem Management
- Filter problems by difficulty rating
- View problem details and direct links to Codeforces
- Add personal notes for each problem
- Custom buttons for tracking problem status

### Authentication
- Secure user authentication system
- User registration functionality
- Profile management

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is open source and available under the MIT License.