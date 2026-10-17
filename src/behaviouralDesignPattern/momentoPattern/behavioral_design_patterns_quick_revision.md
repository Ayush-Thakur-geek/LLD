# Behavioral Design Patterns --- Quick Revision

> **Scope:** All 11 Gang of Four (GoF) behavioral design patterns.
> Examples use Java-oriented pseudocode where helpful. This is a
> revision sheet: focus on intent, structure, when to use each pattern,
> and how to distinguish similar patterns.

## At a glance

  -----------------------------------------------------------------------
  Pattern                 Core idea               Typical signal
  ----------------------- ----------------------- -----------------------
  **Chain of              Pass a request through  Several handlers may
  Responsibility**        potential handlers      process a request;
                                                  sender should not know
                                                  which one handles it

  **Command**             Encapsulate a request   Queue, log, retry,
                          as an object            undo, or schedule an
                                                  operation

  **Interpreter**         Represent and evaluate  Need to evaluate
                          a small language        expressions governed by
                                                  a simple grammar

  **Iterator**            Traverse a collection   Need a standard way to
                          without exposing its    visit elements
                          representation          

  **Mediator**            Centralize              Many objects
                          communication among     communicate in tangled
                          collaborating objects   ways

  **Memento**             Capture and restore an  Undo, checkpoints,
                          object's state without  snapshots
                          exposing its internals  

  **Observer**            Notify subscribers when One-to-many event
                          an object's state       notification
                          changes                 

  **State**               Change behavior when an Large state-dependent
                          object's internal state conditional logic
                          changes                 

  **Strategy**            Make an algorithm       Choose an
                          interchangeable         algorithm/policy at
                                                  runtime

  **Template Method**     Define an algorithm's   Same overall workflow,
                          skeleton; let           different individual
                          subclasses fill         steps
                          selected steps          

  **Visitor**             Add operations over a   Many operations over a
                          stable object structure hierarchy of element
                          without putting each    types
                          operation in the        
                          element classes         
  -----------------------------------------------------------------------

------------------------------------------------------------------------

## 1. Chain of Responsibility

**Intent:** Give multiple objects a chance to handle a request by
passing it along a chain.

**Structure** - `Handler`: declares handling behavior and often a
reference to the next handler. - `ConcreteHandler`: handles the request,
passes it on, or does both. - `Client`: starts the chain.

``` java
abstract class Handler {
    private Handler next;

    Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    void handle(Request request) {
        if (canHandle(request)) {
            process(request);
        } else if (next != null) {
            next.handle(request);
        }
    }

    abstract boolean canHandle(Request request);
    abstract void process(Request request);
}
```

**Use it for:** HTTP middleware, logging pipelines, approval workflows,
exception handling, support escalation.

**Benefits:** Decouples sender from receiver; chain can be rearranged or
extended.

**Trade-offs:** A request might go unhandled; execution order can be
hard to trace; a long chain adds overhead.

**Remember:** The request moves through possible handlers. A handler may
stop the chain, forward it, or sometimes perform work and then forward
it.

------------------------------------------------------------------------

## 2. Command

**Intent:** Encapsulate a request/action as an object.

**Structure** - `Command`: common `execute()` interface. -
`ConcreteCommand`: implements an action, usually by calling a
receiver. - `Receiver`: performs the actual work. - `Invoker`: triggers
the command. - `Client`: assembles the objects.

``` java
interface Command {
    void execute();
}

class TurnOnCommand implements Command {
    private final Light light;

    TurnOnCommand(Light light) { this.light = light; }

    public void execute() { light.turnOn(); }
}

class Remote {
    private Command command;
    void setCommand(Command command) { this.command = command; }
    void pressButton() { command.execute(); }
}
```

**Use it for:** Undo/redo, job queues, task scheduling, transaction-like
workflows, macro recording, UI buttons.

**Benefits:** Separates the invoker from the work; commands can be
stored, queued, logged, retried, or composed.

**Trade-offs:** More classes/objects; undo requires enough information
or a compensating action.

**Remember:** A command represents *what to do* as an object. It is not
the same as a method call alone.

------------------------------------------------------------------------

## 3. Interpreter

**Intent:** Define a representation for a grammar and an interpreter
that evaluates sentences in that grammar.

**Structure** - Expression interface, e.g. `interpret(context)`. -
Terminal expressions represent basic symbols/values. - Nonterminal
expressions combine other expressions according to grammar rules. -
Context stores information needed for interpretation.

``` java
interface Expression {
    boolean interpret(Context context);
}

class AndExpression implements Expression {
    private final Expression left, right;

    AndExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public boolean interpret(Context context) {
        return left.interpret(context) && right.interpret(context);
    }
}
```

**Use it for:** Small rule languages, simple filters, boolean
expressions, configuration DSLs, basic query languages.

**Benefits:** Grammar rules can be represented compositionally; new
expressions can be added.

**Trade-offs:** One class per grammar rule can proliferate; unsuitable
for complex grammars where parser generators or dedicated parsers are
better.

**Remember:** Interpreter models a *language/grammar*. It is not a
general-purpose way to execute arbitrary business logic.

------------------------------------------------------------------------

## 4. Iterator

**Intent:** Provide a way to access collection elements sequentially
without exposing the collection's internal representation.

**Structure** - `Iterator`: operations such as `hasNext()` and
`next()`. - `Aggregate`/collection: supplies an iterator. - Concrete
iterator tracks traversal state.

``` java
List<String> names = List.of("Asha", "Ravi", "Mina");

Iterator<String> it = names.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}
```

**Use it for:** Traversing collections, trees, graphs, custom data
structures, lazy sequences.

**Benefits:** Separates traversal from storage; multiple traversal
strategies can be offered.

**Trade-offs:** Iterator behavior during collection mutation needs care;
custom iterators add implementation work.

**Remember:** Java's `Iterable` and `Iterator` are everyday examples. An
iterator hides *how traversal works*, not necessarily the data itself.

------------------------------------------------------------------------

## 5. Mediator

**Intent:** Encapsulate how a set of objects interact so they do not
refer to one another directly.

**Structure** - `Mediator`: defines communication/coordination. -
`ConcreteMediator`: knows collaborating colleagues and coordinates
them. - `Colleague`: communicates through the mediator rather than
directly with every other colleague.

``` java
interface ChatMediator {
    void send(String message, User sender);
}

class User {
    private final ChatMediator mediator;

    User(ChatMediator mediator) { this.mediator = mediator; }

    void send(String message) { mediator.send(message, this); }
}
```

**Use it for:** Chat rooms, UI dialog coordination, air-traffic
coordination models, workflow orchestration.

**Benefits:** Reduces many-to-many dependencies; coordination logic is
centralized.

**Trade-offs:** The mediator can become a large, overly powerful "god
object."

**Remember:** Colleagues communicate *through a central coordinator*.
The mediator is more than a broadcast subscription list when it actively
coordinates interactions.

------------------------------------------------------------------------

## 6. Memento

**Intent:** Capture and externalize an object's internal state so it can
later be restored, without exposing the object's implementation details.

**Structure** - `Originator`: owns the state and creates/restores
mementos. - `Memento`: stores a snapshot. - `Caretaker`: keeps snapshots
but should not inspect or modify their internals.

``` java
class Editor {
    private String text = "";

    void write(String value) { text += value; }

    Snapshot save() { return new Snapshot(text); }

    void restore(Snapshot snapshot) { text = snapshot.text(); }

    record Snapshot(String text) {}
}
```

**Use it for:** Undo, save points, editor history, game checkpoints,
rollback to previous application state.

**Benefits:** Preserves encapsulation while supporting restoration.

**Trade-offs:** Snapshots can consume substantial memory; nested/mutable
state requires careful copying; history policy is a separate concern.

**Remember:** Memento stores *a previous state*. It does not
automatically implement undo history---you need a caretaker/history
mechanism to manage snapshots.

------------------------------------------------------------------------

## 7. Observer

**Intent:** Define a one-to-many dependency so that when one object
changes state, its dependents are notified.

**Structure** - `Subject`/publisher: maintains subscribers and emits
notifications. - `Observer`/subscriber: defines an update callback. -
Concrete observers react to updates.

``` java
interface Observer {
    void update(String event);
}

class Publisher {
    private final List<Observer> observers = new ArrayList<>();

    void subscribe(Observer observer) { observers.add(observer); }

    void publish(String event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
```

**Use it for:** Event listeners, UI updates, domain events, notification
systems, reactive/event-driven designs.

**Benefits:** Publisher and subscribers are loosely coupled; subscribers
can be added or removed independently.

**Trade-offs:** Notification order and failures can be tricky; careless
subscriptions can cause leaks; synchronous observers can slow the
publisher.

**Remember:** One subject notifies many observers. In a push model, the
subject sends data; in a pull model, observers query the subject.

------------------------------------------------------------------------

## 8. State

**Intent:** Allow an object to alter its behavior when its internal
state changes, making it appear to change its class.

**Structure** - `Context`: public-facing object whose behavior varies. -
`State`: interface for state-dependent operations. - `ConcreteState`:
implements behavior for a particular state and may trigger transitions.

``` java
interface PlayerState {
    void pressPlay(Player player);
}

class PlayingState implements PlayerState {
    public void pressPlay(Player player) {
        player.setState(new PausedState());
    }
}

class PausedState implements PlayerState {
    public void pressPlay(Player player) {
        player.setState(new PlayingState());
    }
}
```

**Use it for:** Media players, order lifecycles, connection states,
vending machines, workflow transitions.

**Benefits:** Replaces sprawling state-dependent conditionals; localizes
behavior for each state.

**Trade-offs:** More classes; transitions can become hard to understand
if scattered across states.

**Remember:** Behavior depends on the object's *current state*. State
objects may control transitions; Strategy objects normally represent
interchangeable algorithms rather than lifecycle states.

------------------------------------------------------------------------

## 9. Strategy

**Intent:** Define a family of algorithms, encapsulate each one, and
make them interchangeable.

**Structure** - `Strategy`: common algorithm interface. -
`ConcreteStrategy`: implements one algorithm. - `Context`: uses a
selected strategy without depending on its concrete implementation.

``` java
interface PaymentStrategy {
    void pay(int amount);
}

class CardPayment implements PaymentStrategy {
    public void pay(int amount) { /* charge card */ }
}

class Checkout {
    private final PaymentStrategy strategy;

    Checkout(PaymentStrategy strategy) { this.strategy = strategy; }

    void pay(int amount) { strategy.pay(amount); }
}
```

**Use it for:** Payment methods, sorting/compression algorithms, pricing
rules, route planning, authentication policies.

**Benefits:** Algorithms are independently testable and replaceable;
avoids conditional selection logic inside the context.

**Trade-offs:** More types; client or configuration code must choose a
strategy.

**Remember:** Strategy answers *which algorithm/policy should be used?*
It is especially useful when algorithms share a contract but vary in
implementation.

------------------------------------------------------------------------

## 10. Template Method

**Intent:** Define the skeleton of an algorithm in a base class,
deferring selected steps to subclasses while preserving the overall
sequence.

**Structure** - Abstract/base class: implements the template method and
common steps. - Subclasses: override designated primitive/optional
steps.

``` java
abstract class DataProcessor {
    // Template method: fixed workflow
    public final void process() {
        read();
        transform();
        save();
    }

    protected abstract void read();
    protected abstract void transform();

    protected void save() { /* common save behavior */ }
}
```

**Use it for:** Data import pipelines, report generation, test
workflows, framework lifecycle hooks.

**Benefits:** Reuses common workflow; keeps step order consistent.

**Trade-offs:** Inheritance couples subclasses to the base class; too
many hooks make the template difficult to maintain.

**Remember:** Template Method uses *inheritance* to vary selected steps
while the base class controls the algorithm's order.

------------------------------------------------------------------------

## 11. Visitor

**Intent:** Represent an operation to be performed on elements of an
object structure, allowing new operations to be added without changing
the element classes (provided the element types are known to the visitor
interface).

**Structure** - `Visitor`: declares a visit method for each concrete
element type. - `ConcreteVisitor`: implements an operation across those
types. - `Element`: declares `accept(visitor)`. - `ConcreteElement`:
calls the visitor's matching overload. - `ObjectStructure`: holds or
traverses elements.

``` java
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

interface Shape {
    void accept(Visitor visitor);
}

class Circle implements Shape {
    public void accept(Visitor visitor) { visitor.visit(this); }
}

class AreaVisitor implements Visitor {
    public void visit(Circle circle) { /* calculate circle area */ }
    public void visit(Rectangle rectangle) { /* calculate rectangle area */ }
}
```

**Use it for:** Compiler AST operations, document/object-tree
processing, reporting over a stable hierarchy, serialization or
validation across element types.

**Benefits:** New operations can be added as new visitors without
editing every element class.

**Trade-offs:** Adding a new element type requires changing the visitor
interface and its implementations; can be awkward for frequently
changing hierarchies.

**Remember:** Visitor makes it easy to add *new operations across many
element types*. Its classic form uses double dispatch: the element calls
`accept`, which invokes the matching `visit` overload.

------------------------------------------------------------------------

## Commonly confused patterns

### Strategy vs State

-   **Strategy:** Select among interchangeable algorithms or policies;
    selection often comes from a client/configuration.
-   **State:** The object's current state drives its behavior and
    transitions; state changes as the object's lifecycle progresses.
-   **Shared shape:** Both can delegate behavior to composed objects
    that implement a common interface.

### State vs Observer

-   **State:** Changes how one context behaves.
-   **Observer:** Notifies multiple dependents about an event or state
    change.
-   They can be combined: a state transition may publish an event.

### Command vs Strategy

-   **Command:** Encapsulates a request/action, often to queue, log,
    retry, or undo it.
-   **Strategy:** Encapsulates an algorithm selected to perform a task.
-   A command may internally use a strategy.

### Chain of Responsibility vs Decorator

-   **Chain of Responsibility:** Each handler may handle or forward a
    request; handling may stop before the end.
-   **Decorator:** Wraps an object to add behavior, usually while
    preserving the same interface; calls commonly pass through the
    wrapping chain.

### Mediator vs Observer

-   **Mediator:** Coordinates interactions among colleagues.
-   **Observer:** Provides one-to-many notifications from a subject to
    subscribers.
-   A mediator may use observer-style events internally, but their
    primary intent differs.

### Template Method vs Strategy

-   **Template Method:** Varies steps through subclassing; overall
    sequence is fixed by the base class.
-   **Strategy:** Varies a whole algorithm/policy through composition
    and delegation.

### Memento vs Command

-   **Memento:** Captures state for later restoration.
-   **Command:** Encapsulates an action; undo may be implemented with a
    reverse command or a memento.

### Visitor vs Iterator

-   **Visitor:** Adds operations over different element types.
-   **Iterator:** Defines how to traverse elements sequentially.
-   They are often combined: an iterator traverses while a visitor
    performs an operation on each element.

------------------------------------------------------------------------

## Rapid selection guide

Ask what is changing or what needs to be decoupled:

-   **Who gets a chance to handle this request?** → Chain of
    Responsibility
-   **Can this action be stored, queued, logged, or undone?** → Command
-   **Am I evaluating a small grammar or DSL?** → Interpreter
-   **How can I traverse without exposing representation?** → Iterator
-   **Are many objects tightly coupled through communication?** →
    Mediator
-   **Do I need to save and restore internal state?** → Memento
-   **Should many subscribers react to an event?** → Observer
-   **Does behavior depend on the object's current state?** → State
-   **Do I need to swap algorithms or policies?** → Strategy
-   **Should subclasses fill steps in a fixed workflow?** → Template
    Method
-   **Do I need new operations across a stable hierarchy of element
    types?** → Visitor

## Final memory aid

-   **Behavior passed along:** Chain of Responsibility
-   **Action as an object:** Command
-   **Grammar as objects:** Interpreter
-   **Traversal:** Iterator
-   **Central coordination:** Mediator
-   **Snapshot/restore:** Memento
-   **Publish/subscribe:** Observer
-   **Lifecycle-dependent behavior:** State
-   **Interchangeable algorithm:** Strategy
-   **Fixed algorithm skeleton:** Template Method
-   **New operations over element types:** Visitor

**Exam/interview tip:** For each pattern, be able to explain its intent,
the roles involved, a realistic use case, one trade-off, and how it
differs from its closest lookalike.
