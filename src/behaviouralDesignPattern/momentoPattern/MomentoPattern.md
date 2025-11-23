# Memento Design Pattern: Text Editor Example

![](Memento_Pattern.png)

## Overview
- The diagram visualizes the **Memento Design Pattern** using a text editor scenario.
- The pattern allows saving and restoring the internal state of an object (here, a text editor) without breaking encapsulation.

---

## Participants

- **TextEditor (Originator)**
    - Holds the current content.
    - Provides methods to write text, save current state to a memento, restore state from a given memento, and retrieve content.
    - Responsible for creating and restoring mementos of its own state.

- **EditorMemento (Memento)**
    - Stores a snapshot of the editor's content at a specific time.
    - Only exposes content retrieval; modification is restricted to the originator.

- **Caretaker**
    - Maintains a history (stack) of mementos.
    - Manages state-saving and undoing operations by interacting with the originator and the stored mementos.
    - Doesn't access or modify the stored content directly; only keeps track of state objects.

---

## Relationships

- **TextEditor → EditorMemento**
    - The TextEditor creates and uses mementos to save/restore its state.

- **Caretaker ⇆ EditorMemento**
    - Caretaker stores and manages EditorMementos in a stack-like history for undo functionality.

- **Caretaker ⇆ TextEditor**
    - Caretaker sends save/restore requests to the TextEditor to manage states.

---

## Flow Summary

1. **Writing**: The TextEditor updates its content.
2. **Saving**: The Caretaker tells the TextEditor to save its current state, which produces an EditorMemento and stores it in the Caretaker's history.
3. **Undo**: The Caretaker restores the last saved state by passing the stored EditorMemento back to the TextEditor.

---

## Key Points

- **Encapsulation Preserved**: The internal state is never exposed except via memento objects, which only the originator manipulates.
- **Undo/Redo Feature**: The pattern enables undo/redo functionality by tracking historical states.
- **Single Responsibility**: Memento only stores state; Caretaker only manages mementos; Originator handles actual content logic.

---

## Applicability

- Particularly suited for applications needing undo/redo, checkpointing, or rollback—like text editors, games, or transactional systems.
- Enhances maintainability by separating state storage from logic and UI code.

