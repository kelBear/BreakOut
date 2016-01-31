Breakout - Yunrong Guo

Command Line Arguments:
1. The first command line argument will be the fps value. Simply enter an int type fps value desired (ex. 40).
2. The second command line argument will be the speed level. There are 5 levels in total.
	- 1 represents a very slow speed
	- 2 represents a slow speed
	- 3 represents a reasonable speed
	- 4 represents a faster speed
	- 5 represents a very fast speed
	- All other entries will exit the application
3. If less than 2 arguments are provided, the default values given are fps = 30, speed = 3. 
4. The program cannot process invalid inputs (not integer values).

Splash Screen:
1. Instructions on how to play the game are displayed on the initial splash screen. 
2. There are three coloured buttons that allow you to make a selection:
	- select "Normal Mode" to start a classic game of Breakout
	- select "Feature Mode" to start a feature mode game of Breakout
	- select "Quit Game" to exit the application
3. Keyboard key 'Q' can be used throughout the game to exit the application.
4. Only the splash screen window cannot be resized.

Keys to use during the game:
1. The paddle can be moved left and right with the left and right arrow keys on the .
2. The ball will move with the paddle while it's on the paddle at the beginning of every game. To release the ball, use the 'Space Bar'.
3. The speed of the ball is calculated in respect to the fps rate - the speed will not change if fps changes.
4. You have three lives in total (life counter an be found in upper left corner) for each game. You lose one life if you fail to catch the ball with the paddle.
5. The game will end if you reach 0 lives. You can use 'N' on the keyboard to begin a new game.

Feature Mode:
1. The feature mode has randomly coloured blocks.
2. To make the game more interesting, the blocks will move left and right on the screen once the game starts. This may create an illusion that your ball is travelling in a curved path.
3. Every time the ball makes contact with the paddle, the blocks will move down slightly on the screen.
4. For every 10 blocks cleared, you will trigger Fire Ball mode. The ball will turn red and can go straight through any block it makes contact with.
5. Each fire ball mode will last for approximately 3 seconds. Then, the ball will go back to its original state.
6. Once the most bottom visible row of blocks moves all the way down to the height of the top of the paddle, you will lose the game even if you still have lives available.
7. You can use 'N' on the keyboard to begin a new game.

Motion of the Ball:
In respect to the point of contact between the ball and paddle, the angle of reflection of the ball is calculated based on the location it hits on the paddle.
	- ex: if the ball hits at the left-most point on the paddle, it will bounce in the upper left direction with a large angle.
	- if the ball hits in the middle of the paddle, it will go straight up at 90 degrees with respect to the paddle.
	- if the ball hits at the right-most point on the paddle, it will bounce in the upper-right direction with a large angle.
	- the angle is adjusted based on point of contact.