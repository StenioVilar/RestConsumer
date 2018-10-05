import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.sipag.web.sipagnet.util.SwaggerConstantes;
import br.com.sippe.web.sipagnet.vo.entidade.VoFunctionalityState;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestTemplateConsumer {

	private RestTemplate restTemplate;

	//MODELO EXEMPLO
	public void buscarBloqueios(Long contaCartao, Integer componente,
			String banCar) {

		initRestTemplate();
		HashMap<String, String> parametrosRequisicao = new HashMap<String, String>();
		parametrosRequisicao.put("accountNumber", "*********");
		parametrosRequisicao.put("component", "1");
		parametrosRequisicao.put("type", "0");
		parametrosRequisicao.put("cardBrand", "MA");

		String url = addParamentrosURLGET(
				System.getProperty(SwaggerConstantes.URL_SWAGGER_FUNCTIONALITY_STATE)
						+ "/functionalityState/actives", parametrosRequisicao);

		List<VoFunctionalityState> retornoFunctionalityState = new ArrayList<VoFunctionalityState>();

		ResponseEntity<String> response = restTemplate.getForEntity(
				new URI(url), String.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Type listType = new TypeToken<ArrayList<VoFunctionalityState>>() {
			}.getType();
			Gson gson = new Gson();
			retornoFunctionalityState = gson.fromJson(response.getBody(),
					listType);
		} else {
			throw new Exception("Serviço temporariamente indisponível.");
		}

	}
	
	
	//SERVIÇO PARA ADICIONAR PARAMETROS A URL - GET METHOD
	protected String addParamentrosURLGET(String url,
			Map<String, String> uriVariables) {
		String parametros = null;
		Set<String> chaves = uriVariables.keySet();
		for (String chave : chaves) {
			uriVariables.get(chave);
			if (parametros == null) {
				parametros = "?" + chave + "=" + uriVariables.get(chave);
			} else {
				parametros = parametros + "&" + chave + "="
						+ uriVariables.get(chave);
			}
		}
		String urlComParametros = url + parametros;
		return urlComParametros;
	}

	protected void initRestTemplate() throws Exception {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
	}

}
