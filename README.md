# Invite

Java app which reads text file of encoded json and outputs how many people are within 100km.

## Behavior

Json encoded string is expected to be fully formed with no empyy or null arguments. Jsons strings missing properties will be ignored. Furthermore, json strings with extra properties will be ignored.

This behaviour is handled by the Jackson object mapper on json deserialization and object creation.

## Distance Strategies

A helper utility is used to deserialize json and calculate distance. The location of the party and the method used to calculate distance can be changed dynamically at runtime.

Currently a method using the law of cosines is supplied. Distances are calculated accurate to within ~100m using this strategy.

Users can easliy add their own formulas by implementing the `DistanceFormula` interface.

## Usage

Manipulating one json object


```
InviteHelper helper = new InviteHelper();
Boolean within100Km = false;

Optional<Person> OptPerson = helper.json2Person(someJsonString);
 
OptPerson.ifPresent(person -> within100Km = helper.isWithin(person, 100D));
```

Stream from a file

```
count = Files.lines(file.toPath())
                    .map(inviteHelper::json2Person)
                    .filter(Optional::isPresent)   // in JDK 9 we these 2 lines
                    .map(Optional::get)            // as '.flatMap(Optional::stream)'
                    .filter(person -> inviteHelper.isWithin(person, 100D))
                    .foreach(System.out::println);
```

Or use the supplied method to `printInvites` using default law of cosines method and 100km from default party location

```
File input = new File("src/main/resources/people");
long count = printInvites(input);
```