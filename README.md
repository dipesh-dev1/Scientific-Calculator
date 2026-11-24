# Scientific Calculator (Java)

A calculator project written in Java with two versions: a graphical scientific calculator built with Swing, and a simple menu-based console calculator.

## Features

### Scientific Calculator (GUI) – `ScientificCalculator.java`
- Number buttons 0–9 and a decimal point
- Basic operations: addition, subtraction, multiplication, division
- Power (`^`), square root (`√`) and percentage (`%`)
- Scientific functions: `sin`, `cos`, `tan` (angles in degrees), `log` (base 10) and `ln` (natural log)
- Clear button (`C`)
- History panel that records every calculation
- Divide-by-zero warning dialog

### Console Calculator – `Calculator.java`
- Menu-driven program that runs until you choose Exit
- Addition, subtraction, multiplication, division
- Power (x^y), square root and percentage
- Error messages for division by zero and the square root of a negative number

## Project Structure

```
Project/
├── ScientificCalculator.java   # Swing GUI calculator
└── Calculator.java             # Console calculator
```

Both files use `package Project;`, so keep them inside a folder named `Project`.

## Requirements

- Java Development Kit (JDK) 8 or newer

## How to Run

Open a terminal in the folder that contains the `Project` folder.

**GUI calculator**
```bash
javac Project/ScientificCalculator.java
java Project.ScientificCalculator
```

**Console calculator**
```bash
javac Project/Calculator.java
java Project.Calculator
```

You can also open the project in Eclipse, IntelliJ IDEA or VS Code and run either class directly.

## How to Use

**GUI:** Type a number, press an operator (`+`, `-`, `*`, `/`, `^`), type the second number and press `=`. For `√`, `%`, `sin`, `cos`, `tan`, `log` and `ln`, type a number and press the function button. The result appears in the display and in the history panel on the right.

**Console:** Choose an option from the menu, then enter the numbers when asked. Enter `5` to exit.

| Option | Operation |
|--------|-----------|
| 1 | Addition |
| 2 | Subtraction |
| 3 | Multiplication |
| 4 | Division |
| 5 | Exit |
| 6 | Power (x^y) |
| 7 | Square root |
| 8 | Percentage |

## Technologies Used

- Java
- Java Swing and AWT (GUI)
- `java.util.Scanner` (console input)
