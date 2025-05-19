# java-calcite
This repo contains example of Apache Calcite dealing with java object and CSV files
# Project Overview: java-calcite

This project demonstrates the use of Apache Calcite for querying different data sources, including Java objects and CSV files.

## File Descriptions:

* **`README.md`**: Provides a high-level description of the project and its purpose.

* **`pom.xml`**: A Maven project configuration file that manages project dependencies, including Apache Calcite (version 1.36.0), and build settings.

* **`src/main/java/com/siddhu/calcite/model/`**: Contains the Java source code defining the data model and example usage:
    * `CompanySchema.java`: Defines a Calcite schema for company-related data.
    * `Department.java`: Represents the `Department` object.
    * `Employee.java`: Represents the `Employee` object.
    * `TestSiddhuCalcite.java`: Demonstrates querying Java objects using Calcite.
    * `TestSiddhuCalciteCSV.java`: Demonstrates querying data from CSV files using Calcite.

* **`src/main/resources/`**: Contains resource files for the application:
    * `logback.xml`: Configuration for the Logback logging framework, set to log information to the console.
    * `model.json`: Defines a Calcite schema named "INDUSTRIES" that connects to CSV files located in the `industries` subdirectory using the `FileSchemaFactory`. The default schema is set to "INDUSTRIES".
    * **`src/main/resources/industries/INDUSTRY.csv`**: A CSV file containing a list of industry names under the "Industry" column. This file is the data source for the "INDUSTRIES" schema defined in `model.json`.

In summary, this project provides examples of how to use Apache Calcite to query in-memory Java objects and external data sources like CSV files by defining schemas and using SQL. The `model.json` file configures Calcite to treat the `INDUSTRY.csv` file as a table named (likely) `INDUSTRY` within the `INDUSTRIES` schema, allowing SQL queries to be executed against it. The Java files in the `model` package define the structure of the in-memory data being queried in the other test classes.
