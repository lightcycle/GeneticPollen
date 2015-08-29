# Genetic Pollen
A Java artificial life demonstration with evolving behavior.

## Screenshot

![Alt text](http://lightcycle.github.io/screenshots/GeneticPollen.png "Genetic Pollen Screenshot")

## Quickstart

[Gradle](https://gradle.org/) and [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html) are required.  Run with `gradle run`.

## Details

### Overview

Cells are updated in random order, performing a single action selected by a list of rules that examine the cell's neighborhood and the cell's own state.  Cells start with a certain amount of energy and are removed when energy reaches zero.  Cells lose a set amount of energy on each step and when performing actions.  Cells gain energy when they are the topmost cell.

### Actions

On each turn a cell may move, reproduce, share energy, steal energy, or change which rule will be evaluated on the next turn.

### Genetic System

Each cell's genome is a simple array of integers.  This array may be modified during reproduction to simulate mutation.

Each cell's phenotype is a list of rules, and each rule is composed of an action and an expression tree that evaluates to true or false.  The expression tree is composed of objects that can return the cell state, neighboring cells' states, and perform logical and mathematical operations that combine the results of other function calls.  On each turn the cell's current rule is checked, and performs the action returned.

The `PhenotypeProvider` class is responsible for producing the phenotype from the genome.  It uses a dependency injection approach.  When an instance of a particular Java interface is requested from `PhenotypeProvider`, it uses the next integer from the genome to select from among the known classes that implement the interface.  If the selected class's constructor takes arguments, the same approach is used again to create instances of those types.  Enumerations and constants are also instantiated from genome input.  In this manner, a tree of objects is created from a simple genome that is easy to copy and manipulate.

### Environment

The topmost cells receive a set amount of energy on each turn, as if they were receiving solar energy from directly above.

Cells not touching the ground directly or indirectly through other neighboring cells will fall one unit on each turn, as if they were falling due to gravity.
