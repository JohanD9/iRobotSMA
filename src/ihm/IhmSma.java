/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import iRobotSMA.Ihm;
import interfaces.IControl;
import interfaces.IInfos;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author jdebat
 */
public class IhmSma extends Ihm {

	public MainFraime frame;
	ArrayList<Position> posNid;
	
	

	public IhmSma() {
		super();
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		frame = new MainFraime(this);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        posNid = new ArrayList<Position>();
	}

	@Override
	protected IInfos make_infosFromIhm() {
		// TODO Auto-generated method stub
		return new IInfos() {
			
			@Override
			public ArrayList<Case> sendData(Position pos) {
				int x = pos.getX();
				int y = pos.getY();
				
				Case c = frame.getGrille().getCasePanelTable()[x][y];
				ArrayList<Case> tourCase = new ArrayList<Case>();
				
				if (frame.getGrille().getCasePanelTable()[x-1][y-1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x-1][y-1]);
				}
				if ((frame.getGrille().getCasePanelTable()[x-1][y]) != null) {
					tourCase.add((frame.getGrille().getCasePanelTable()[x-1][y]));
				}
				if (frame.getGrille().getCasePanelTable()[x-1][y+1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x-1][y+1]);
				}
				if (frame.getGrille().getCasePanelTable()[x][y-1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x][y-1]);
				}
				if (frame.getGrille().getCasePanelTable()[x][y+1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x][y+1]);
				}
				if (frame.getGrille().getCasePanelTable()[x+1][y-1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y-1]);
				}
				if (frame.getGrille().getCasePanelTable()[x+1][y] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y]);
				}
				if (frame.getGrille().getCasePanelTable()[x+1][y+1] != null) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y+1]);
				}
				return tourCase;
			}
		};
	}

	@Override
	protected IControl make_controlToEnv() {
		// TODO Auto-generated method stub
		return new IControl() {
			
			@Override
			public void sauvegarder() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reprendre() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void pause() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void lancerSystem() {
				System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");

		        frame.setLocationRelativeTo(null);
		        frame.setVisible(true);       
				
			}
			
			@Override
			public void init() {
				// Création des nids
				Grille grille = frame.getGrille();
				grille.addComposant(grille.getCasePanelTable()[6][7], 1, Type.NID, Couleur.BLUE);
				posNid.add(new Position(6,  7));
				grille.addComposant(grille.getCasePanelTable()[6][14], 2, Type.NID, Couleur.RED);
				posNid.add(new Position(6,  14));
				grille.addComposant(grille.getCasePanelTable()[6][21], 3, Type.NID, Couleur.GREEN);
				posNid.add(new Position(6,  21));
				
				int nbRobot = getNbRobot();
				int nbBoite = getNbBoite();
				
				Position pos;
				for (int i = 0; i < nbRobot; i++) {
					pos = randomPosition();
					Random rand = new Random();
					Couleur c = Couleur.values()[rand.nextInt(Couleur.values().length)];
					requires().creationEcosystemFromEcoProxyAndRobot().createEspece(pos, Type.ROBOT, c);
				}

				for (Composant c : requires().creationEcosystemFromEcoProxyAndRobot().listerEspece()) {
					grille.addComposant(grille.getCasePanelTable()[c.pos.getX()][c.pos.getY()], c.id, c.type, c.couleur);
				}
				
			}
			
			@Override
			public void chargerEtat() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public int getNbRobot() {
				return frame.robot;
			}

			@Override
			public int getNbBoite() {
				return frame.boite;
			}

			@Override
			public ArrayList<Position> getPosNid() {
				return posNid;
			}

			@Override
			public Position randomPosition() {
				Grille grille = frame.getGrille();
				Random rand = new Random();
				int x = rand.nextInt(frame.ligne);
				int y = rand.nextInt(frame.colonne);
				Position p = new Position(x, y);
				System.out.println(x + " : " + y);
				System.out.println(frame.ligne + " : " + frame.colonne);
				while (!grille.getCasePanelTable()[x][y].listComposants.isEmpty()) {
					x = rand.nextInt(frame.ligne);
					y = rand.nextInt(frame.colonne);
					
					p = new Position(x, y);
				}
				return p;
			}
		};
	}
    
}
