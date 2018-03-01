main
-> new "GameMap"

	new "Maps"
		extends CurrentLevel
			// N/A

	GameMap->getRandomGuard()
		// 隨機選取一種守衛
		// Randomly pick a guard

		new "Rookie"     (LV 1)
		new "Drunken"    (LV 2)
		new "Suspicious" (LV 3)
			extends Guard
				extends Character
					// N/A

	GameMap->updateMap()
		// N/A

	GameMap->markPositions()
		// Mark Init Position
		// of Hero & Guards

while(){
	GameMap->moveHeroTo()

	->	Rookie||Drunken||Suspicious
		->guardNextPosition()

	->	//Club->clubNextPosition()
		= Ogre->guardNextPosition()
}