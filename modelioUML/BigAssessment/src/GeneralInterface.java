import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

@objid ("5264c32f-b1f5-4245-a44d-285432bd711d")
public interface GeneralInterface {
    @objid ("345ac923-df5a-4e93-b95e-3fed5e1252a8")
    void DisplayProduct();

    @objid ("e0f4235d-fc86-438c-99c0-034085ddb1ad")
    void Login();

    @objid ("b547726a-8a76-496d-be2c-e70054e0213a")
    void ScannedItems(List<String> Items);

    @objid ("8e4b6f91-a5c0-4fae-af40-fad18bcf6c94")
    void Pay();

    @objid ("d5229985-cb45-4c07-800b-f85880b01f2c")
    void DeleteItem();

}
