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

## Dependencies used
- Jackson Core Databind 2.18.3
- Jackson Datatype Jsr310 2.18.2

## Usage
### Add an Expense
```sh
java Application add --description "Electricity" --amount 500 --category Utilities
```
### Update an Expense
```sh
java Application update --id 1 --description "Electricity Bill" --amount 550
```
### Delete an Expense
```sh
java Application delete --id 1
```
### List Expenses
```sh
java Application list --year 2021 --month 9
```
### Summarize Expenses
```sh
java Application summarize --year 2023 --month 12
```
### Import Expenses
```sh
java Application import --json expenses.json
```
### Export Expenses
```sh
java Application export --csv expenses.csv
```
### Set Monthly Budget
```sh
java Application budget --amount 1000
```
these are just examples, you can use the application with different options and values.

## Project Roadmap
For more details on the project roadmap, visit [Expense Tracker Roadmap](https://roadmap.sh/projects/expense-tracker).

## License
This project is licensed under the MIT License.