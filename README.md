# Expense Tracker Application

## Overview
Expense Tracker is a simple Java command-line application that helps you manage and track your expenses. You can add, update, delete, list, and summarize your expenses. The application also supports importing and exporting data in CSV and JSON formats.

## Features
- Add new expenses with description, amount, and category.
- Update existing expenses by ID.
- Delete expenses by ID or category.
- List expenses with various filters (year, month, day, category).
- Summarize total expenses with various filters.
- Import expenses from CSV or JSON files.
- Export expenses to CSV or JSON files.
- Set a monthly budget and get notified if exceeded.

## My workset and Dependencies used
- Build System: IntelliJ
- Java version: 23.0.2
- Jackson Core Databind 2.18.3
- Jackson Datatype Jsr310 2.18.2

## Installation

1. Clone the repository:
```sh
  git clone https://github.com/Abdulrahim-M/expense-tracker.git
  expense-tracker/Windows
```
2. Execute the program:
```sh
  cd ET <Command> <Option>
```

## Usage
### Add an Expense
```sh
ET add --description "Electricity" --amount 500 --category Utilities
```
### Update an Expense
```sh
ET update --id 1 --description "Electricity Bill" --amount 550
```
### Delete an Expense
```sh
ET delete --id 1
```
### List Expenses
```sh
ET list --year 2021 --month 9
```
### Summarize Expenses
```sh
ET summarize --year 2023 --month 12
```
### Import Expenses
```sh
ET import --json expenses.json
```
### Export Expenses
```sh
ET export --csv expenses.csv
```
### Set Monthly Budget
```sh
ET budget --amount 1000
```
these are just examples, you can use the application with different options and values.
### And don't forget the most important:
```sh
ET help <Command>
```

## Roadmap test Project
This project was inspired from the [Expense Tracker](https://roadmap.sh/projects/expense-tracker) project in the [Java roadmap](https://roadmap.sh/java) projects section at [roadmap.sh](https://roadmap.sh/).

## License
This project is licensed under the MIT License.
