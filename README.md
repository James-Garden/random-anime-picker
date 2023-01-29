# Random Anime Picker

A simple web app, which allows a user to pick a random anime from their planned-to-watch anime on MyAnimeList.

## System requirements
- Docker
- Java 17

## Setup

### 1. Start backed services
- Start the docker containers defined in `devtools/docker-compose.yml`, via or IntelliJ or the command line

### 2. Set the application profile

- In your IntelliJ run configuration for the app, include either `development` or `production` in your active profiles

#### Production profile

| Environment Variable | Description                                              |
|----------------------|----------------------------------------------------------|
| RAP_DB_URL           | The full JDBC URL for the database service to connect to |
| RAP_DB_USERNAME      | The username the app will use to connect to the database |
| RAP_DB_PASSWORD      | The password the app will use to connect to the database |

