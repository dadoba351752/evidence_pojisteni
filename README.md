# Evidence pojištění

Evidence pojištění je jednoduchá webová aplikace pro správu pojištěnců a jejich pojištění. Projekt vznikl jako součást praktického procvičení po absolvování rekvalifikačního kurzu od ITnetwork.

## Funkcionalita

- Přehled pojištěnců s možností jejich přidávání, úpravy a mazání
- Možnost evidovat více pojištění ke každému pojištěnci
- Validace vstupních údajů (např. telefonní číslo, jméno, číslo popisné)
- Flashové zprávy při úspěšných nebo neúspěšných akcích
- Responzivní rozhraní postavené na Bootstrapu

## Použité technologie

| Vrstva aplikace | Použité technologie |
|-----------------|---------------------|
| Backend         | Java 17, Spring Boot |
| Šablony         | Thymeleaf |
| Frontend        | HTML, CSS, Bootstrap |
| Databáze        | Spring Data JPA (např. H2, MySQL) |
| Vývoj           | Maven, IntelliJ IDEA |

## Validace a vstupy

Aplikace ověřuje správnost dat pomocí Spring Validation a regulárních výrazů:

- Telefonní číslo: např. `+420 777 888 999`
- Jméno a příjmení: pouze písmena, mezery a pomlčky
- Číslo popisné: např. `123`, `2A`, `869/2`
- E-mail: validní e-mailová adresa
- Datum narození: povinné a musí být v minulosti

## Autor

**Jméno:** Adam Soukal  
**E-mail:** adam.soukal@seznam.cz

---

# Insurance Records (English Summary)

**Insurance Records** is a simple web application for managing clients and their insurance policies. It was created as a practical project after completing a Java/Spring retraining course.

## Features

- CRUD operations for insured clients
- Assigning multiple insurance records per client
- Input validation using Spring Validation and regular expressions
- Flash messages for actions
- Responsive Bootstrap interface

## Technology Stack

- Java 17, Spring Boot
- Thymeleaf templates
- Bootstrap for styling
- Spring Data JPA (e.g. H2, MySQL)
- Maven

## Author

Adam Soukal  
adam.soukal@seznam.cz

## Ukázky / Screenshots

![image](https://github.com/user-attachments/assets/93c6e682-25c8-4d02-b9f4-b8228a9459aa)
![image](https://github.com/user-attachments/assets/486d2f9a-70ce-4e2b-bf11-384bcdd5cb24)
![image](https://github.com/user-attachments/assets/6133fa0d-740d-493c-a0de-cdaf30dbb920)
![image](https://github.com/user-attachments/assets/67631678-4a13-4bef-a0bd-3076c03363f3)
![image](https://github.com/user-attachments/assets/5144c96b-a16d-4d55-a147-a255114dc6d1)



