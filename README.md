# Gym-Management-Website

[![GitHub license](https://img.shields.io/github/license/Nazim-25/Gym-Management-Website)](https://github.com/Nazim-25/Gym-Management-Website/blob/main/LICENSE)
[![GitHub issues](https://img.shields.io/github/issues/Nazim-25/Gym-Management-Website)](https://github.com/Nazim-25/Gym-Management-Website/issues)
[![GitHub stars](https://img.shields.io/github/stars/Nazim-25/Gym-Management-Website)](https://github.com/Nazim-25/Gym-Management-Website/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/Nazim-25/Gym-Management-Website)](https://github.com/Nazim-25/Gym-Management-Website/network)

## Overview

Welcome to the Gym Management Website repository! This project aims to streamline gym operations and enhance member experiences by providing a user-friendly interface for managing various aspects of a gym, from member registration to workout tracking.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Issues](#issues)

## Features

- **Member Management**: Easily add, edit, and remove member details.
- **Workout Tracking**: Monitor member workouts and progress over time.
- **Membership Information**: The website provides information about the gym’s membership plans and pricing.
- **Class Scheduling**: The website displays the gym’s class schedules and allows users to sign up for classes.
- **Payment Tracking**: Keep track of member payments and due dates.
- **User Authentication**: Secure login system for both members and administrators.
- **Responsive Design**: The website is designed to be accessible and usable on various devices.

## Installation

To run this project locally, follow these steps:

1. **Clone the repository** to your local machine:

    ```bash
    git clone https://github.com/Nazim-25/Gym-Management-Website.git
    ```

2. **Install MySQL**: Download and install MySQL from the [official website](https://dev.mysql.com/downloads/).

3. **Create Database**: Open a MySQL client (e.g., MySQL Workbench) and create a database called "sportsworld."

4. **Update Configuration**:
   - Open the `application.properties` file in the project directory.
   - Change the `username` and `password` in the file to match your MySQL credentials.

5. **Update Connection Credentials**:
   - In the `ConnexionBase.java` class, located in the project source code, update the database connection details (username, password, etc.) to match your MySQL setup.

6. **Run the Application**:
   - Find and run the `DemoApplication.java` class as a Spring Boot application. You can typically do this in your integrated development environment (IDE) by right-clicking on the file and selecting "Run As" -> "Spring Boot App."

7. Open your browser and visit [http://localhost:8080](http://localhost:8080).

Now, your Gym Management Website should be connected to your MySQL database, and you can access it locally. If you encounter any issues during this process, please refer to the [Issues](#issues) section or [open an issue](https://github.com/Nazim-25/Gym-Management-Website/issues). Your feedback is highly appreciated.

Thank you for choosing the Gym Management Website! We appreciate your support.


## Usage

After installation, you can use the website to manage gym-related tasks. Key functionalities include:

- Visit the homepage to log in as an administrator or a member.
- Administrators can access member management, workout tracking, class scheduling, and payment tracking features.
- Members can log workouts, view class schedules, and check payment status.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Make changes and submit a pull request.

## License

This project is licensed under the [MIT License](https://github.com/Nazim-25/Gym-Management-Website/blob/main/LICENSE).

## Issues

If you encounter any issues or have suggestions, please [open an issue](https://github.com/Nazim-25/Gym-Management-Website/issues). Your feedback is highly appreciated.

Thank you for choosing the Gym Management Website! We appreciate your support.