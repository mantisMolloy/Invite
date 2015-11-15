# Invite

Java app which reads text file of encoded json and outputs how many people are within 100km.

## Usage

Json encoded string is expected to be fully formed with no empyy or null arguments. Jsons strings missing properties will be ignored. Furthermore, json strings with extra properties will be ignored.

This behaviour is handled by the Jackson object mapper on json deserialization and object creation.

## Distance Strategies

A helper utility is used to deserialize json and calculate distance. The location of the party and the method used to calculate distance can be changed dynamically at runtime.

Currently a method using the law of cosines is supplied. Distances are calculated accurate to within ~100m using this strategy.

Users can easliy add their own formulas by implementing the `DistanceFormula` interface.

