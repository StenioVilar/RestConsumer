package main;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestTemplateConsumer {

	private RestTemplate restTemplate;

	//MODELO EXEMPLO GET
	public void getMethod() throws Exception {

		initRestTemplate();
		HashMap<String, String> parametrosRequisicao = new HashMap<String, String>();
		parametrosRequisicao.put("parameter", "*********");

		String url = addParamentrosURLGET(System.getProperty(SwaggerConstantes.URL_SWAGGER_VARIABLE_NAME)
						+ "/service", parametrosRequisicao);

		List<VoResponse> returnResponse = new ArrayList<VoResponse>();

		ResponseEntity<String> response = restTemplate.getForEntity(
				new URI(url), String.class);

		if (response.getStatusCode().is2xxSuccessful()) {
			Type listType = new TypeToken<ArrayList<VoResponse>>() {}.getType();
			Gson gson = new Gson();
			returnResponse = gson.fromJson(response.getBody(),listType);
		} else {
			throw new Exception("Serviço temporariamente indisponível.");
		}

	}
	
	
	//SERVIÇO PARA ADICIONAR PARAMETROS A URL - GET METHOD
	protected String addParamentrosURLGET(String url,Map<String, String> uriVariables) {
		
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
