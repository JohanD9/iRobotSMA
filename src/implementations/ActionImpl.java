package implementations;

import iRobotSMA.Action;
import ihm.ActionType;
import ihm.Case;
import ihm.Choix;
import ihm.Composant;
import ihm.Grille;
import ihm.Position;
import interfaces.IActionRobot;

import java.util.ArrayList;

public class ActionImpl extends Action{
	
	public ActionImpl() {
		
	}

	@Override
	protected IActionRobot make_actionRobotToRobot() {
		// TODO Auto-generated method stub
		return new IActionRobot() {
			
			@Override
			public Position agir(Position pos) {
				Choix ch = requires().decisionRobotFromDecision().decider(pos);
				Grille g = requires().grilleFromIhm().getGrille();
				
				
				
				Case currentCase = g.getCasePanelTable()[pos.getX()][pos.getY()];
				
				if (ch.action == ActionType.DEPOSER_BOITE) {
					System.out.println(ch.action);
					Composant comp = currentCase.removeBoite();
					g.getCasePanelTable()[ch.position.getX()][ch.position.getY()].addComposant(comp);
					return ch.position;
				}
				
				ArrayList<Composant> tmpList = currentCase.listComposants;
				int size = tmpList.size();
				for (int i = 0; i < size; i++) {

					
					Composant comp = tmpList.get(0);
					g.move(comp.id, comp.type, comp.couleur, pos.getX(), pos.getY(), ch.position.getX(), ch.position.getY());
					g.validate();
				}
				return ch.position;
			}
		};
	}

}