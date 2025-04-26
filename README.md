# ✈️ TIA Flight Tracker Console App

## Overview
This Java console application connects to the [AviationStack API](https://aviationstack.com/) to retrieve **live flight information** for flights **departing from** or **arriving at** Tirana International Airport (**TIA**).  
Flight data is **persisted** into a local database using **Hibernate ORM**.

---

## Features
- Fetch **live arrivals** and **departures** for TIA Airport.
- Save flight data (flight number, airline, departure, arrival, status, etc.) into a database.
- Console menu:
    - Fetch new data from API.
    - View stored flights from the database.
    - List previous flights.
    - Exit the application.

---

## Requirements

### Functional
- Connect to AviationStack API to get **live flight data** for TIA (ICAO Code: LATI, IATA Code: TIA).
- Persist the following flight details into a relational database:
    - Flight Number
    - Airline Name
    - Departure Airport
    - Departure Time
    - Arrival Airport
    - Arrival Time
    - Flight Status (scheduled, active, landed, canceled, etc.)
- Avoid storing duplicate flight records (based on flight number and date).

### Technical
- Java 17+
- Hibernate ORM (use **annotations** based Entity-Relationship model)
- MySQL
- Maven for dependency management
---

## Tech Stack
- Java 17
- Hibernate 6.x
- Maven
- MySQL
- Jackson (for parsing JSON API responses)
- OkHttp or Apache HttpClient (for making HTTP calls)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/SDA-JAVA-49/flyspy.git
