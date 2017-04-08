# Snake

This game will be a multiplayer snake game.

AIs will compete to grow and destroy the other players.
The API can also be used to create clients which human players can control.

Food will be placed randomly by the server and snakes can eat it to grow longer.
Snakes can cut off other snakes, and they will be killed. When a snake dies it turns into food.
The larger the snake, the larger the turning circle of the snake.
Snakes may cross themselves but not other snakes, unless they are in a team.
Snakes will be made up of many segments. Snakes have segment size, segment separation, and segment count.

#### Network Protocol:
The network protocol will consist of 2 stages. Connection and The Game Loop.
During the connection phase, all the data required for setup of the game will be sent to the client.
During the game loop phase, every tick the client will interact with the game.

###### Connection

1. Request ID - The client should request an id from the server it is trying to connect to.
2. Request Map - The client should request the map. This will remain fixed for the game.
3. Request Snakes - The client should request the initial state of all the snakes.

###### Game Loop

Data will be sent from the server to the client about all food, snakes and objects that are nearby.
The Snake will have a radius of vision inside which data will be sent.

* Snake Locations - Update fragments will be sent each tick.
* Food Locations - New food will be sent each tick. Old food will be sent every few ticks.

- Snake Request - Requests information about a snake.
  - This should be used when there are update fragments for a snake that doesn't exist locally.
