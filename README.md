[![Build Status](https://travis-ci.org/mantisMolloy/Invite.svg?branch=master)](https://travis-ci.org/mantisMolloy/Invite)
# Invite

Java app which reads text file of encoded json and outputs how many people are within 100km.

## Behavior

Json encoded string is expected to be fully formed with no empty or null arguments. Json strings missing properties will be ignored. Furthermore, json strings with extra properties will be ignored.

This behaviour is handled by the Jackson object mapper on json deserialization and object creation.

## Distance Strategies

A helper utility is used to deserialize json and calculate distance. The location of the party and the method used to calculate distance can be changed dynamically at runtime.

Currently a method using the law of cosines is supplied. Distances are calculated accurate to within ~100m using this strategy.

Users can easliy add their own formulas by implementing the `DistanceFormula` interface.

## Usage

Manipulating one json object


```java
InviteHelper helper = new InviteHelper(); 
final Boolean[] within100Km = {false};

Optional<Person> OptPerson = helper.json2Person(someJsonString);
OptPerson.ifPresent(person -> within100Km = helper.isWithin(person));
```

Or use the supplied method `printInvites` which will print all people invited based on default criteria to stout

```java
InviteHelper helper = new InviteHelper();

File input = new File("src/main/resources/people");
long count = helper.printInvites(input);
```

Futhermore, we can change the location of the party, required distance, and which formula to use. Then we can do something else besides from print the people to stout.


```java
InviteHelper helper = new InviteHelper();
helper.setDistanceStrategy(new LawOfCosines());
helper.setLocation(new Location(43,90));
helper.setRequiredDistance(200D);

File input = new File("src/main/resources/people");
Files.lines(input.toPath())
        .map(helper::json2Person)
        .filter(Optional::isPresent)   // in JDK 9 we these 2 lines
        .map(Optional::get)            // as '.flatMap(Optional::stream)'
        .filter(helper::isWithin)
        .forEach(//do something with these people);
```
