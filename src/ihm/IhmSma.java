/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import iRobotSMA.EcoProxy;
import iRobotSMA.EcoProxyAndRobot;
import iRobotSMA.EcoProxyAndRobot.ProxyAndRobot;
import iRobotSMA.EcoRobot;
import iRobotSMA.Ihm;
import implementations.BoiteImpl;
import implementations.RobotImpl;
import interfaces.IControl;
import interfaces.ICreationEcosystem;
import interfaces.IInfos;
import interfaces.IRobot;

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
				
				
				
				if ((x-1 >= 0) && (x-1 < 49) && (y-1 >= 0) && (y-1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x-1][y-1]);
				}
				if ((x-1 >= 0) && (x-1 < 49) && (y >= 0) && (y < 49)) {
					tourCase.add((frame.getGrille().getCasePanelTable()[x-1][y]));
				}
				if ((x-1 >= 0) && (x-1 < 49) && (y+1 >= 0) && (y+1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x-1][y+1]);
				}
				if ((x >= 0) && (x < 49) && (y-1 >= 0) && (y-1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x][y-1]);
				}
				if ((x >= 0) && (x < 49) && (y+1 >= 0) && (y+1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x][y+1]);
				}
				if ((x+1 >= 0) && (x+1 < 49) && (y-1 >= 0) && (y-1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y-1]);
				}
				if ((x+1 >= 0) && (x+1 < 49) && (y >= 0) && (y < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y]);
				}
				if ((x+1 >= 0) && (x+1 < 49) && (y+1 >= 0) && (y+1 < 49)) {
					tourCase.add(frame.getGrille().getCasePanelTable()[x+1][y+1]);
				}
				return tourCase;
			}

			@Override
			public Grille getGrille() {
				return frame.getGrille();
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
				List<RobotImpl> listRobot = requires().listeRobotFromEcoProxyAndRobot().getRobots();
		        for (RobotImpl c : listRobot) {
					/*try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					c.lancer();
					frame.pack();
				}
				
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
					provides().creationToEspece().createEspece(pos, Type.ROBOT, c);
				}
				
				for (int i = 0; i < nbBoite; i++) {
					pos = randomPosition();
					Random rand = new Random();
					Couleur c = Couleur.values()[rand.nextInt(Couleur.values().length)];
					provides().creationToEspece().createEspece(pos, Type.BOITE, c);
				}

				List<RobotImpl> listRobot = requires().listeRobotFromEcoProxyAndRobot().getRobots();
				for (RobotImpl c : listRobot) {
					grille.addComposant(grille.getCasePanelTable()[c.position.getX()][c.position.getY()], c.id, c.type, c.couleur);
				}
				
				
				List<BoiteImpl> listBoite = requires().listeBoiteFromEcoBoite().getBoites();
				for(BoiteImpl b : listBoite) {
					grille.addComposant(grille.getCasePanelTable()[b.position.getX()][b.position.getY()], b.id, b.type, b.couleur);
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
			public boolean createEspece(Position pos, Type type, Couleur couleur) {
				// TODO Auto-generated method stub
				Integer idToCreate;
				boolean b = false;
				
				if (Type.ROBOT == type){
					idToCreate = getNextId(type);
					requires().creerEcoSysFromEcoProxyAndRobot().create(idToCreate, pos, couleur, type);
					b = true;
				} else if (Type.BOITE == type){
					idToCreate = getNextId(type);
					BoiteImpl boite = new BoiteImpl(idToCreate, pos, couleur, type);	
					requires().listeBoiteFromEcoBoite().getBoites().add(boite);
					b = true;
				}
				return b;
			}
		};
	}

	
    
}
