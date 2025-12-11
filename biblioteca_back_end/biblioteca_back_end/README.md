## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## BD da biblioteca
CREATE DATABASE biblioteca;
USE biblioteca;
 CREATE TABLE Clientes (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nome VARCHAR(45) NOT NULL,
         cpf VARCHAR(11) NOT NULL UNIQUE,
         telefone INT
     );
     CREATE TABLE Livros (
         id INT AUTO_INCREMENT PRIMARY KEY,
         nome VARCHAR(45) NOT NULL,
         autor VARCHAR(45) NOT NULL
    );
    CREATE TABLE Emprestimo (
         id INT AUTO_INCREMENT PRIMARY KEY,
         id_Cliente INT NOT NULL,
         id_Livros INT NOT NULL,
         localDate_emprestimo DATETIME NOT NULL,
         localDate_devolucao DATETIME,
    
         FOREIGN KEY (id_Cliente) REFERENCES Clientes(id),
         FOREIGN KEY (id_Livros) REFERENCES Livros(id)
    );
    SHOW TABLES;