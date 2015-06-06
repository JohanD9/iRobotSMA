/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import iRobotSMA.Ihm;
import implementations.BoiteImpl;
import implementations.RobotImpl;
import interfaces.IControl;
import interfaces.ICreationEcosystem;
import interfaces.IInfos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author jdebat
 */
public class IhmSma extends Ihm {

	public MainFraime frame;
	ArrayList<Position> posNid;

	Integer idRobot = 0;
	Integer idBoite = 0;

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
				
				Case tmp = null;
				if ((x-1 >= 0) && (x-1 < 50) && (y-1 >= 0) && (y-1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x-1][y-1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x-1 >= 0) && (x-1 < 50) && (y >= 0) && (y < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x-1][y];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x-1 >= 0) && (x-1 < 50) && (y+1 >= 0) && (y+1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x-1][y+1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x >= 0) && (x < 50) && (y-1 >= 0) && (y-1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x][y-1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x >= 0) && (x < 50) && (y+1 >= 0) && (y+1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x][y+1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x+1 >= 0) && (x+1 < 50) && (y-1 >= 0) && (y-1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x+1][y-1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x+1 >= 0) && (x+1 < 50) && (y >= 0) && (y < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x+1][y];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				if ((x+1 >= 0) && (x+1 < 50) && (y+1 >= 0) && (y+1 < 50)) {
					tmp = frame.getGrille().getCasePanelTable()[x+1][y+1];
					if (isEmptyOrOnlyBoite(tmp)) {
						tourCase.add(tmp);
					}
				}
				return tourCase;
			}

			@Override
			public Grille getGrille() {
				return frame.getGrille();
			}
		};
	}
	
	public boolean isEmptyOrOnlyBoite(Case c) {
		if (c.listComposants.size() == 0) {
			return true;
		}
		if (c.listComposants.size() == 1) {
			if (c.listComposants.get(0).type == Type.BOITE) {
				return true;
			}
		}
		return false;
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
				List<RobotImpl> listRobot = requires().listeRobotFromEcoProxyAndRobot().getRobots();
				System.out.println(listRobot);
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
				Integer idToCreate;
				for (int i = 0; i < nbRobot; i++) {
					idToCreate = provides().creationToEspece().getNextId(Type.ROBOT);
					pos = randomPosition();
					Random rand = new Random();
					Couleur c = Couleur.values()[rand.nextInt(Couleur.values().length)];
					//D'abord créer le composant dans la grille
					grille.addComposant(grille.getCasePanelTable()[pos.getX()][pos.getY()], idToCreate, Type.ROBOT, c, pos);
					provides().creationToEspece().createEspece(idToCreate, pos, Type.ROBOT, c);
				}
				
				for (int i = 0; i < nbBoite; i++) {
					idToCreate = provides().creationToEspece().getNextId(Type.BOITE);
					pos = randomPosition();
					Random rand = new Random();
					Couleur c = Couleur.values()[rand.nextInt(Couleur.values().length)];
					grille.addComposant(grille.getCasePanelTable()[pos.getX()][pos.getY()], idToCreate, Type.BOITE, c, pos);
					provides().creationToEspece().createEspece(idToCreate, pos, Type.BOITE, c);
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
				while (!grille.getCasePanelTable()[x][y].listComposants.isEmpty()) {
					x = rand.nextInt(frame.ligne);
					y = rand.nextInt(frame.colonne);
					
					p = new Position(x, y);
				}
				return p;
			}

			@Override
			public int charger(Position pos, Type type, Couleur couleur) {
				Grille grille = frame.getGrille();
				Integer idToCreate = provides().creationToEspece().getNextId(type);
				
				//D'abord créer le composant dans la grille
				provides().creationToEspece().createEspece(idToCreate, pos, type, couleur);
				return idToCreate;
			}

			@Override
			public void viderSystem() {
				provides().creationToEspece().removeEspece();
				
			}
		};
	}

	@Override
	protected ICreationEcosystem make_creationToEspece() {
		// TODO Auto-generated method stub
		return new ICreationEcosystem() {
			
			@Override
			public List<Composant> listerEspece(List<?> listEspece) {
				// TODO Auto-generated method stub
				List<Composant> listEsp = new ArrayList<Composant>();
				Composant c;
				
				System.out.println(listEspece.getClass());
				return listEsp;
			}
			
			@Override
			public Integer getNextId(Type type) {
				// TODO Auto-generated method stub
				Integer idToReturn = 0;
				
				if (Type.ROBOT == type){
					synchronized (idRobot) {
						idToReturn = idRobot;
						idRobot ++;
					}
				} else if (Type.BOITE == type) {
					synchronized (idBoite) {
						idToReturn = idBoite;
						idBoite ++;
					}
				}
				
				return idToReturn;
			}
			
			@Override
			public boolean createEspece(Integer id, Position pos, Type type, Couleur couleur) {
				// TODO Auto-generated method stub
				boolean b = false;
				
				if (Type.ROBOT == type){
					requires().creerEcoSysFromEcoProxyAndRobot().create(id, pos, couleur, type);
					b = true;
				} else if (Type.BOITE == type){
					BoiteImpl boite = new BoiteImpl(id, pos, couleur, type);	
					requires().listeBoiteFromEcoBoite().getBoites().add(boite);
					b = true;
				}
				return b;
			}

			@Override
			public void removeEspece() {
				requires().listeBoiteFromEcoBoite().getBoites().clear();
				requires().creerEcoSysFromEcoProxyAndRobot().removeAll();
				
			}
		};
	}

	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		frame = new MainFraime(provides().controlToEnv());
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        posNid = new ArrayList<Position>();
		System.out.println("FRAME START");
	}
    
}
