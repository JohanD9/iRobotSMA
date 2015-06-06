package implementations;

import iRobotSMA.Action;
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
				if (ch == null) {
					return pos;
				}
				Grille g = requires().grilleFromIhm().getGrille();
				
				Case currentCase = g.getCasePanelTable()[pos.getX()][pos.getY()];
				
				ArrayList<Composant> tmpList = currentCase.listComposants;
				
				for (int i = 0; i < tmpList.size(); i++) {
					Composant comp = tmpList.get(i);
					g.move(comp.id, comp.type, comp.couleur, pos.getX(), pos.getY(), ch.position.getX(), ch.position.getY());
					g.validate();
				}
				return ch.position;
			}
		};
	}

}
