# Invite

Java app which reads text file of encoded json and outputs how many people are within 100km.

## Usage

Json encoded string is ecpected to be fully formed with no empy or null arguments. Such jsons string will be ignored.
Furthermore, json string with extra properties will be ignored.

## Distance Strategies

A helper utility is used to deserialize json and calculate distance. The location of the party and the method used to calculate distance can be changed dynamically at runtime.

Currently a method using the law of cosines is used to calculate distances. Distances are calculated accurate to within ~100m using this strategy.

