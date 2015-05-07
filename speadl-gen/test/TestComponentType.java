package test;

import interfaces.ITypeDeServiceTest;

@SuppressWarnings("all")
public abstract class TestComponentType {
  public interface Requires {
  }
  
  public interface Component extends TestComponentType.Provides {
  }
  
  public interface Provides {
    /**
     * This can be called to access the provided port.
     * 
     */
    public ITypeDeServiceTest unServiceTest();
  }
  
  public interface Parts {
  }
  
  public static class ComponentImpl implements TestComponentType.Component, TestComponentType.Parts {
    private final TestComponentType.Requires bridge;
    
    private final TestComponentType implementation;
    
    public void start() {
      this.implementation.start();
      this.implementation.started = true;
    }
    
    protected void initParts() {
      
    }
    
    private void init_unServiceTest() {
      assert this.unServiceTest == null: "This is a bug.";
      this.unServiceTest = this.implementation.make_unServiceTest();
      if (this.unServiceTest == null) {
      	throw new RuntimeException("make_unServiceTest() in test.TestComponentType should not return null.");
      }
    }
    
    protected void initProvidedPorts() {
      init_unServiceTest();
    }
    
    public ComponentImpl(final TestComponentType implem, final TestComponentType.Requires b, final boolean doInits) {
      this.bridge = b;
      this.implementation = implem;
      
      assert implem.selfComponent == null: "This is a bug.";
      implem.selfComponent = this;
      
      // prevent them to be called twice if we are in
      // a specialized component: only the last of the
      // hierarchy will call them after everything is initialised
      if (doInits) {
      	initParts();
      	initProvidedPorts();
      }
    }
    
    private ITypeDeServiceTest unServiceTest;
    
    public ITypeDeServiceTest unServiceTest() {
      return this.unServiceTest;
    }
  }
  
  /**
   * Used to check that two components are not created from the same implementation,
   * that the component has been started to call requires(), provides() and parts()
   * and that the component is not started by hand.
   * 
   */
  private boolean init = false;;
  
  /**
   * Used to check that the component is not started by hand.
   * 
   */
  private boolean started = false;;
  
  private TestComponentType.ComponentImpl selfComponent;
  
  /**
   * Can be overridden by the implementation.
   * It will be called automatically after the component has been instantiated.
   * 
   */
  protected void start() {
    if (!this.init || this.started) {
    	throw new RuntimeException("start() should not be called by hand: to create a new component, use newComponent().");
    }
  }
  
  /**
   * This can be called by the implementation to access the provided ports.
   * 
   */
  protected TestComponentType.Provides provides() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("provides() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if provides() is needed to initialise the component.");
    }
    return this.selfComponent;
  }
  
  /**
   * This should be overridden by the implementation to define the provided port.
   * This will be called once during the construction of the component to initialize the port.
   * 
   */
  protected abstract ITypeDeServiceTest make_unServiceTest();
  
  /**
   * This can be called by the implementation to access the required ports.
   * 
   */
  protected TestComponentType.Requires requires() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("requires() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if requires() is needed to initialise the component.");
    }
    return this.selfComponent.bridge;
  }
  
  /**
   * This can be called by the implementation to access the parts and their provided ports.
   * 
   */
  protected TestComponentType.Parts parts() {
    assert this.selfComponent != null: "This is a bug.";
    if (!this.init) {
    	throw new RuntimeException("parts() can't be accessed until a component has been created from this implementation, use start() instead of the constructor if parts() is needed to initialise the component.");
    }
    return this.selfComponent;
  }
  
  /**
   * Not meant to be used to manually instantiate components (except for testing).
   * 
   */
  public synchronized TestComponentType.Component _newComponent(final TestComponentType.Requires b, final boolean start) {
    if (this.init) {
    	throw new RuntimeException("This instance of TestComponentType has already been used to create a component, use another one.");
    }
    this.init = true;
    TestComponentType.ComponentImpl  _comp = new TestComponentType.ComponentImpl(this, b, true);
    if (start) {
    	_comp.start();
    }
    return _comp;
  }
  
  /**
   * Use to instantiate a component from this implementation.
   * 
   */
  public TestComponentType.Component newComponent() {
    return this._newComponent(new TestComponentType.Requires() {}, true);
  }
}
