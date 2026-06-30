# CV Dashboard

A clean standalone Java Swing desktop application for creating a simple Curriculum Vitae entry. The app opens a dashboard window where users can launch an internal CV form, enter personal details, select spoken languages, preview the CV, and export it as a text file.

## Features

- Java Swing desktop interface
- Dashboard window with menu bar and toolbar
- Multiple internal CV windows using `JDesktopPane` and `JInternalFrame`
- CV form fields for:
  - Last name
  - First name
  - Spoken languages
  - Sex
- CV preview dialog
- Export to a UTF-8 `.txt` file

## Project Structure

```text
cvdashboard/
├── Main.java
├── DashboardFrame.java
├── CvInternalFrame.java
└── README.md
```

## Requirements

- Java Development Kit (JDK) 11 or newer

The project uses only standard Java libraries, so no external dependencies are required.

## How to Run

Because the source files use the `cvdashboard` package, compile and run the project from the parent directory that contains the `cvdashboard` folder.

```bash
javac cvdashboard/*.java
java cvdashboard.Main
```

If your project is inside a `src` folder, run the commands from inside `src`:

```bash
cd src
javac cvdashboard/*.java
java cvdashboard.Main
```

## Usage

1. Start the application.
2. Click **Open CV** from the toolbar, or choose **CV > Open CV Window** from the menu.
3. Fill in the CV form.
4. Select one or more languages from the list.
5. Choose the sex option.
6. Click **Valider** to preview the CV.
7. Click **Exporter** to save the CV as a text file.
8. Click **Fermer** to close the CV window.

## Main Classes

- `Main` starts the application on the Swing event dispatch thread.
- `DashboardFrame` creates the main dashboard window, menu, toolbar, and desktop area.
- `CvInternalFrame` contains the CV form, preview logic, and export logic.

## License

This project is open-source and available under the MIT License.

![image](https://github.com/khaoukhaou4869-jpg/CV-Dashboard/blob/990b5f474255ada98fbc5b83366c790af3cc5d84/2026-06-30%20041042.png)
