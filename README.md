# star-field
Starting by playing around with thousands of particles I made a star-field. 
Then I animated it and gave it a 3D perspective effect. Well, then I needed a star ship.
What good is a star ship that can't shoot lasers? So I made a laser by generating single pixels
using pseudo random coordinates by controlling the boundary conditions.
then I added some sounds... the TODOs never end.

**To play use arrow keys and 'F' to fire laser**

_the goal has become to create a never ending randomly generated space game_
_The parameter is that the game needs to retain a balance of playability and increasing difficulty_
_the strategy is to keep the enemies from passing you to your Mothership and avoid self destruction_

##TODO:
* create randomly generated enemy behaviour once generated consistent for that enemy
* create randomly generated enemy formations, cooperative behaviour
* create weapons. 3 modules of 10 components gives 1000 possible weapon types
* create super weapons for Bosses and player.
* Add explosions. 
* Use Particles? 
* or randomly splice exploding ship image
* Use size of image splice to adjust velocity away from explosion and life of particle
* perhaps redivide and explode splices above certain size
* or Both particles and fragments.
* Add ship thrusters
	* thrusters only on when player moves forward?
	* use particles with life to leave trail of exhaust
* create randomly generated yet consistent once generated enemy behaviour
  * formations
  * how long they fly in screen, move around, deploy weapons
  * how often they deploy weapons
  * Drone, Commander and Boss level ships and behaviour
* add Mothership health bar(status of how many ships have passed you)
* display extra lives (show ships left)
* add defense shield. player presses 'D'
* add super weapon.
  * needs to recharge
  * should have way to switch super weapon type
  * have charge status bar on screen
  * Player presses 'S'
* add teleportation.
  * player presses 'A'
  * the ship teleports to random place on screen and has chance of landing on enemy
  * a device of last resort used when "anywhere is better than here only"
* add score
* add Splash Screen
* add credits to graphic designers and sound engineers.
* add high score