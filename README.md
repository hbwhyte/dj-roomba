# DJ Roomba

Everyone wants a clean room, but no one wants to clean it. Where's the fun in that?

That's why we built **DJ Roomba**.

If you input the size of the room to be cleaned, where the dirty patches are, and how it should get there, then DJ Roomba will do it's best to make the room a (slightly) cleaner place.
 
### Tech Stack
 - Java 11
 - Spring Boot
 - JUnit, Mockito, AssertJ
 

### Getting Started
Super simple! You just need to start the application, then use the endpoint below:

```
[POST] /clean
```
POST body (all fields required):
`roomSize` : size of grid, in an array of the X and Y axis

`coords` : where the roomba should start, in an array of X and Y coordinates

`patches` : Coordinates for each dirt patch, in an array of arrays of X and Y axis

`instructions`: Uses cardinal direction letters (N = North, S = South, etc.), concatenated together as one string.


### Example JSON Body:
```json
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```
### Example JSON Response:
```json
{
  "coords" : [1, 3],
  "patches" : 1
}
```
Where `coords` in the response is the final coordinates of DJ Roomba and `patches` is the number of cleaned patches.

Happy cleaning!
