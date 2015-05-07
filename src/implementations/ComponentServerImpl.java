package implementations;
import test.ComponentServerType;
import interfaces.ITypeDeServiceServer;

public class ComponentServerImpl extends ComponentServerType{

	@Override
	protected ITypeDeServiceServer make_nomPortServiceContraire() {
		// TODO Auto-generated method stub
		return new ITypeDeServiceServer() {
			
			@Override
			public boolean serviceContraire(boolean b) {
				// TODO Auto-generated method stub
				return (!b);
			}
		};
	}
	
}
