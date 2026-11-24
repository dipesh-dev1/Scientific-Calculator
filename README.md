# Scientific Calculator (Java)

A calculator project written in Java with two versions: a graphical scientific calculator built with Swing, and a simple menu-based console calculator.

## Features

### Scientific Calculator (GUI) - `ScientificCalculator.java`
- Number buttons 0-9 and a decimal point
- Basic operations: addition, subtraction, multiplication, division
- Power (`^`), square root and percentage (`%`)
- Scientific functions: `sin`, `cos`, `tan` (angles in degrees), `log` (base 10) and `ln` (natural log)
- Clear button (`C`)
- History panel that records every calculation
- Clear error messages for empty input, division by zero, square root of a negative number and invalid log/ln/tan input

### Console Calculator - `Calculator.java`
- Menu-driven program that runs until you choose Exit
- Addition, subtraction, multiplication, division
- Power (x^y), square root and percentage
- Error messages for invalid input, division by zero and the square root of a negative number

## Project Structure

```
.
├── ScientificCalculator.java   # Swing GUI calculator
├── Calculator.java             # Console calculator
└── README.md
```

## Requirements

- Java Development Kit (JDK) 8 or newer

## How to Run

Open a terminal in the project folder and compile both files:

```bash
javac *.java
```

Run the GUI calculator:

```bash
java ScientificCalculator
```

Run the console calculator:

```bash
java Calculator
```

You can also open the folder in VS Code, Eclipse or IntelliJ IDEA and run either file directly.

## How to Use

**GUI:** Type a number, press an operator (`+`, `-`, `*`, `/`, `^`), type the second number and press `=`. For square root, `%`, `sin`, `cos`, `tan`, `log` and `ln`, type a number and press the function button. The result appears in the display and in the history panel on the right.

**Console:** Choose an option from the menu, then enter the numbers when asked.

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
