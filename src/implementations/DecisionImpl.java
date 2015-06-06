package implementations;

import iRobotSMA.Decision;
import ihm.ActionType;
import ihm.Case;
import ihm.Choix;
import ihm.Couleur;
import ihm.Grille;
import ihm.Position;
import interfaces.IDecisionRobot;

import java.util.ArrayList;
import java.util.Random;

public class DecisionImpl extends Decision{
	
	public DecisionImpl() {
		
	}

	@Override
	protected IDecisionRobot make_decisionRobotToAction() {
		// TODO Auto-generated method stub
		return new IDecisionRobot() {
			
			@Override
			public Choix decider(Position pos) {
				ArrayList<Case> listCase = requires().perceptionRobotFromPerception().percevoir(pos);
				Grille g = requires().grilleFromIhm().getGrille();
				Case currentCase = g.getCasePanelTable()[pos.getX()][pos.getY()];
				if (listCase.size() > 0) {
					Choix c = null;
					if (currentCase.listComposants.size() == 2) {
						for(Case chercheNid : getAllAroundCase(currentCase)) {;
							if (chercheNid.getNid(currentCase.getCouleurBoite())) {
								System.out.println(chercheNid.getNid(currentCase.getCouleurBoite()));
								c = new Choix(ActionType.DEPOSER_BOITE, new Position(currentCase.abscisseCase, currentCase.ordonneeCase));
								return c;
							}
						}
						c = new Choix(ActionType.VERS_BOITE, getToNidPos(listCase, currentCase));
					} else {
						Random rand = new Random();
						int tmp = rand.nextInt(listCase.size());
						Case toGo = listCase.get(tmp);
						c = new Choix(ActionType.SE_DEPLACER, new Position(toGo.abscisseCase, toGo.ordonneeCase));
					}

					return c;
				}
				return null;
			}
			
			public ArrayList<Case> getAllAroundCase(Case c) {
				Grille g = requires().grilleFromIhm().getGrille();
				ArrayList<Case> allCases = new ArrayList<Case>();
				int x = c.abscisseCase;
				int y = c.ordonneeCase;
				
				allCases.add(g.getCasePanelTable()[x-1][y-1]);
				allCases.add(g.getCasePanelTable()[x-1][y]);
				allCases.add(g.getCasePanelTable()[x-1][y+1]);
				allCases.add(g.getCasePanelTable()[x][y-1]);
				allCases.add(g.getCasePanelTable()[x][y+1]);
				allCases.add(g.getCasePanelTable()[x+1][y-1]);
				allCases.add(g.getCasePanelTable()[x+1][y]);
				allCases.add(g.getCasePanelTable()[x+1][y+1]);
				
				
				return allCases;
			}
			
			public Position getToNidPos(ArrayList<Case> listCase, Case current) {
				Position p = new Position(0, 0);
				ArrayList<Position> posNid = requires().grilleFromIhm().getPosNid();
				
				if (current.getCouleurBoite() == Couleur.BLUE) {
					Position posNidBlue = posNid.get(0);
					int diffX = 50;
					ArrayList<Case> tmpListX = new ArrayList<Case>();
					for (Case cur : listCase) {
						int tmpDiffX = Math.abs(posNidBlue.getX() - cur.abscisseCase);
						if (tmpDiffX == diffX){
							tmpListX.add(cur);
						}
						if (tmpDiffX < diffX) {
							diffX = tmpDiffX;
							tmpListX.clear();
							tmpListX.add(cur);
						}
					}
					if (tmpListX.size() == 1){
						p.setX(tmpListX.get(0).abscisseCase);
						p.setY(tmpListX.get(0).ordonneeCase);
						return p ;
					} else {
						int diffY = 50;
						ArrayList<Case> tmpListY = new ArrayList<Case>();
						for (Case curAfterX : tmpListX) {
							int tmpDiffY = Math.abs(posNidBlue.getY() - curAfterX.ordonneeCase);
							if (tmpDiffY == diffY){
								tmpListY.add(curAfterX);
							}
							if (tmpDiffY < diffY) {
								diffY = tmpDiffY;
								tmpListY.clear();
								tmpListY.add(curAfterX);
							}
						}
						Random rand = new Random();
						int tmp = rand.nextInt(tmpListY.size());
						p.setX(tmpListY.get(tmp).abscisseCase);
						p.setY(tmpListY.get(tmp).ordonneeCase);
						return p;
					}
				}
				if (current.getCouleurBoite() == Couleur.RED) {
					Position posNidRed = posNid.get(1);
					int diffX = 50;
					ArrayList<Case> tmpListX = new ArrayList<Case>();
					for (Case cur : listCase) {
						int tmpDiffX = Math.abs(posNidRed.getX() - cur.abscisseCase);
						if (tmpDiffX == diffX){
							tmpListX.add(cur);
						}
						if (tmpDiffX < diffX) {
							diffX = tmpDiffX;
							tmpListX.clear();
							tmpListX.add(cur);
						}
					}
					if (tmpListX.size() == 1){
						p.setX(tmpListX.get(0).abscisseCase);
						p.setY(tmpListX.get(0).ordonneeCase);
						return p ;
					} else {
						int diffY = 50;
						ArrayList<Case> tmpListY = new ArrayList<Case>();
						for (Case curAfterX : tmpListX) {
							int tmpDiffY = Math.abs(posNidRed.getY() - curAfterX.ordonneeCase);
							if (tmpDiffY == diffY){
								tmpListY.add(curAfterX);
							}
							if (tmpDiffY < diffY) {
								diffY = tmpDiffY;
								tmpListY.clear();
								tmpListY.add(curAfterX);
							}
						}
						Random rand = new Random();
						int tmp = rand.nextInt(tmpListY.size());
						p.setX(tmpListY.get(tmp).abscisseCase);
						p.setY(tmpListY.get(tmp).ordonneeCase);
						return p;
					}
				}
				if (current.getCouleurBoite() == Couleur.GREEN) {
					Position posNidGreen = posNid.get(2);
					int diffX = 50;
					ArrayList<Case> tmpListX = new ArrayList<Case>();
					for (Case cur : listCase) {
						int tmpDiffX = Math.abs(posNidGreen.getX() - cur.abscisseCase);
						if (tmpDiffX == diffX){
							tmpListX.add(cur);
						}
						if (tmpDiffX < diffX) {
							diffX = tmpDiffX;
							tmpListX.clear();
							tmpListX.add(cur);
						}
					}
					if (tmpListX.size() == 1){
						p.setX(tmpListX.get(0).abscisseCase);
						p.setY(tmpListX.get(0).ordonneeCase);
						return p ;
					} else {
						int diffY = 50;
						ArrayList<Case> tmpListY = new ArrayList<Case>();
						for (Case curAfterX : tmpListX) {
							int tmpDiffY = Math.abs(posNidGreen.getY() - curAfterX.ordonneeCase);
							if (tmpDiffY == diffY){
								tmpListY.add(curAfterX);
							}
							if (tmpDiffY < diffY) {
								diffY = tmpDiffY;
								tmpListY.clear();
								tmpListY.add(curAfterX);
							}
						}
						Random rand = new Random();
						int tmp = rand.nextInt(tmpListY.size());
						p.setX(tmpListY.get(tmp).abscisseCase);
						p.setY(tmpListY.get(tmp).ordonneeCase);
						return p;
					}
				}
				
				return p;
			}
		};

	}
}
