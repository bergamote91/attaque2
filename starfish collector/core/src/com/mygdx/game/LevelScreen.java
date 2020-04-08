package com.mygdx.game;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class LevelScreen extends BaseScreen {   
	private Spaceship spaceship;
	private boolean gameOver;
	private Rock rock;
	private Boolean tir;
	private BaseActor laser;


	public void initialize()    {     
		BaseActor space = new BaseActor(0,0, mainStage);    
		space.loadTexture( "space.png" );     
		space.setSize(800,600);     
		BaseActor.setWorldBounds(space);

		spaceship = new Spaceship(300,50, mainStage);   


		rock = new Rock(400,400, mainStage); 
		System.out.println(rock.shieldPower);
		
		tir = false;


	}
	
	

	public void update(float dt)  {
		System.out.println(rock.shieldPower);
		

		for ( BaseActor laserActor : BaseActor.getList(mainStage, "com.mygdx.game.Laser") )
		{
			
			if (laserActor.overlaps(rock) )
				
			{
				tir=true;
				if (rock.shieldPower > 0 && tir == true)
				{
					tir=false;
					laserActor.remove();
					rock.shieldPower -= 34;
					

					
				}
				else if (rock.shieldPower <= 0)
					{
						Explosion boom = new Explosion(0,0, mainStage);
	                    boom.centerAtActor(rock);
	                    laserActor.remove();
	                    rock.remove();
	                    
	                    BaseActor messageLose = new BaseActor(0,0, uiStage);
	                    messageLose.loadTexture("message-win.png");
	                    messageLose.centerAtPosition(400,300);
	                    messageLose.setOpacity(0);
	                    messageLose.addAction( Actions.fadeIn(1) );
	                    
					}



			}
		}
		
	}






	//pour tirer laser
	public boolean keyDown(int keycode)
	{
		if ( keycode == Keys.SPACE )
			spaceship.shoot();

		return false;


	}
}