# Car Rental Search App

This is a simple Android application that allows users to search for car rentals through Kayak. Users can input their pickup and drop-off locations, as well as the pickup and drop-off dates. The app generates a deep link to Kayak with the provided details and opens Kayak in a web browser to show the available car rental options.

## Features

- **Pickup Location**: Users can enter a required pickup location (city, state/country).
- **Drop-off Location**: Optional, users can input a drop-off location.
- **Pickup Date**: Users must provide the pickup date in `YYYY-MM-DD` format.
- **Drop-off Date**: Required only if a drop-off location is provided. The date must be in `YYYY-MM-DD` format.
- **Search on Kayak**: Clicking a button generates a deep link to Kayak with the search parameters and opens it in a web browser.
- **Basic Validation**: Displays warnings if required fields are empty (pickup location and pickup date).
- **Deep Linking**: Constructs a Kayak deep link using the provided Affiliate Deep Link URL structure.

## UI Design

The app's UI is built using **Jetpack Compose** and follows a clean and simple design:

- **SearchInputBox Composable**: This contains input fields for pickup location, drop-off location, pickup date, and drop-off date. It validates the inputs and displays warnings if required fields are missing.
- **DatePickerDialog Composable**: This displays a Material3 Date Picker dialog for selecting dates in `YYYY-MM-DD` format.
- **Kayak Search Button**: Generates a Kayak deep link and opens the Kayak website in a web browser to show the available car rentals.

## Code Structure

### MainActivity

- Sets up the UI using **Jetpack Compose** and **Scaffold** for a clean layout.
- Handles logic to manage the user's input and validations.

### SearchInputBox Composable

- Contains input fields for:
    - **Pickup Location** (Required)
    - **Drop-off Location** (Optional)
    - **Pickup Date** (Required, via a Date Picker dialog)
    - **Drop-off Date** (Required if a drop-off location is provided, via a Date Picker dialog)
- Validates the inputs and displays warnings for missing fields.
- Generates the Kayak deep link based on the inputs and opens it in a web browser.

### DatePickerDialog Composable

- Displays a **Material3 Date Picker dialog** for selecting the pickup and drop-off dates.
- Returns the selected date in the required `YYYY-MM-DD` format.

## APK Download

You can download the APK for the app [here](https://drive.google.com/file/d/1GfrfIiWmMRzWdUrVhV9pkp115It6O0g5/view?usp=sharing).

## App Workflow

You can view the app workflow video [here](https://drive.google.com/file/d/1s5EudtmDJijZ5Lx0w-1NVFbMY_yCiHad/view?usp=sharing)
