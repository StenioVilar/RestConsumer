
public class RestTemplateConsumer{

  private RestTemplate restTemplate;
  
  public void buscarBloqueios(Long contaCartao, Integer componente, String banCar) {
    
    try {

      initRestTemplate();   
      HashMap<String, String> parametrosRequisicao = new HashMap<String, String>();
		  parametrosRequisicao.put("accountNumber", "*********");
		  parametrosRequisicao.put("component", "1");
		  parametrosRequisicao.put("type", "0");
		  parametrosRequisicao.put("cardBrand", "MA");
			
		  String url = addParamentrosURLGET(System.getProperty(SwaggerConstantes.URL_SWAGGER_FUNCTIONALITY_STATE)+ "/functionalityState/actives", parametrosRequisicao);
        	
		  List<VoFunctionalityState> retornoFunctionalityState = new ArrayList<VoFunctionalityState>();
			
      ResponseEntity<String> response
         = restTemplate.getForEntity(new URI(url), String.class);
        	
      if(response.getStatusCode().is2xxSuccessful()){
        Type listType = new TypeToken<ArrayList<VoFunctionalityState>>(){}.getType();
    	  Gson gson = new Gson();
    	  retornoFunctionalityState = gson.fromJson(response.getBody(), listType);
      }else{
        throw new Exception("Serviço temporariamente indisponível.");
    }
       
  }

  protected void initRestTemplate() throws Exception {
      if(restTemplate == null){
        restTemplate = new RestTemplate();
      }
  }
  
}
