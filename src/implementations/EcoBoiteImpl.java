package implementations;

import iRobotSMA.EcoBoite;
import interfaces.IBoite;

import java.util.ArrayList;
import java.util.List;

public class EcoBoiteImpl extends EcoBoite{
	
	public List<BoiteImpl> boites = new ArrayList<BoiteImpl>();
	public Integer id = 0;

	@Override
	protected IBoite make_listBoites() {
		return new IBoite() {
			
			@Override
			public List<BoiteImpl> getBoites() {
				return boites;
			}
		};
	}
}
