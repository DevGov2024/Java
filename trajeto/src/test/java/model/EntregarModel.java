package model;
import com.google.gson.annotations.Expose;
import lombok.Data;
@Data
public class EntregarModel {
    @Expose(serialize = false)
    private String nomeRua ;
    @Expose
    private String nomeMotorista ;
    @Expose
    private String statusTrajeto;
    @Expose
    private String dataTrajeto;
}