import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("42d2283d-b489-439f-811b-12944cc207d3")
public abstract class StockDisp implements Stock {
    @objid ("5ea0d377-3765-446f-8d96-2892fe6cb1dc")
    public void DeccreseStock() {
    }

    @objid ("b6bcdd50-dd39-441b-b52d-3a36eaf2cebd")
    public void IncreaseStock() {
    }

    @objid ("a787ead0-f9e5-4358-b567-0fed457798ad")
    public void OrderStock() {
    }

    @objid ("8c6d9ca2-39ab-4015-a2b9-dbfe69f99d15")
    public void LowStock(String Notify) {
    }

    @objid ("f5a529c2-09b2-4276-9009-16dfef693a8e")
    public void ShowStock() {
    }

}
