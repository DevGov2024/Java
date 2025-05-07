package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import services.CadastroEntregaService;
import java.util.List;
import java.util.Map;

public class TrajetoSteps {
    CadastroEntregaService cadastroEntregaService = new CadastroEntregaService();

    @Dado("que eu tenha os seguintes dados da entrega:")
    public void queEuTenhaOsSeguintesDadosDaEntrega(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            cadastroEntregaService.setFieldsDelivery(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de entregas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeEntregas(String endPoint) {
        cadastroEntregaService.createDelivery(endPoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, cadastroEntregaService.response.statusCode());
    }

    @Então("a resposta deve estar no formato correto")
    public void aRespostaDeveEstarNoFormatoCorreto() {
        cadastroEntregaService.validateResponseSchema();
    }

    @Então("a resposta deve conter a mensagem {string}")
    public void aRespostaDeveConterAMensagem(String mensagem) {
        Assert.assertTrue(cadastroEntregaService.response.asString().contains(mensagem));
    }
}

